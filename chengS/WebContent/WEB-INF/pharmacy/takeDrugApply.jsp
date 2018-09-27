<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />

<link rel="stylesheet" type="text/css"
	href="../static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css"
	href="../static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css"
	href="../lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css"
	href="../static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css"
	href="../static/h-ui.admin/css/style.css" />

<title>药品请领</title>

</head>
<body>
	<nav class="breadcrumb">
		<i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span>
		日常工作 <span class="c-gray en">&gt;</span> 药品请领 <a
			class="btn btn-success radius r"
			style="line-height: 1.6em; margin-top: 3px"
			href="javascript:location.replace(location.href);" title="刷新"><i
			class="Hui-iconfont">&#xe68f;</i></a>
	</nav>
	<br>
	<div class="text-c">
		药品编码：<input type="text" name="drugId" id="drugId" placeholder="药品编码"
			style="width: 150px" class="input-text" disabled>&nbsp; <a
			href="javascript:;"
			onclick="member_add('选择药品','selDrug.action?drugId=-1','','510')"
			class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i>
			选择药品</a> &emsp;药品名称：<input type="text" name="drugName" id="drugName"
			placeholder="药品名称" style="width: 150px" class="input-text" disabled />
		&emsp;单位：<input type="text" name="unit" id="unit" placeholder="药品单位"
			style="width: 150px" class="input-text" disabled /> &emsp;规格：<input
			type="text" name="specific" id="specific" placeholder="药品规格"
			style="width: 150px" class="input-text" disabled /> <br>申请人：<input
			type="text" name="adminName" id="adminName"
			placeholder="${User.adminName}" disabled
			style="width: 130px; margin-top: 5px" class="input-text" />
		&emsp;库存数量：<input type="text" id="inventoryNum"  placeholder="库存数量"
			style="width: 100px; margin-top: 5px" class="input-text" disabled />
		&emsp;申请数量：<input type="text" name="applyNum" id="applyNum"
			placeholder="请输入申请数量" style="width: 100px; margin-top: 5px"
			class="input-text" /> &emsp;申请理由：<input type="text"
			name="applyReason" id="applyReason" placeholder="请输入申请理由"
			style="width: 250px; margin-top: 5px" class="input-text" /> <a
			class="btn btn-primary radius" onclick="addApply()"><i
			class="Hui-iconfont">&#xe600;</i> 增加</a> <input type="hidden"
			id="retailPrice"> <input type="hidden" id="adminId"
			value="${User.adminId}"><br> <br>
		<div align="right">
			<button type="button" class="btn btn-primary" id="" onclick="empty()">清空</button>
			&nbsp;&nbsp;
			<button type="button" class="btn btn-default" id=""
				onclick="subApply()">提交申请</button>
		</div>
	</div>
	<form id="takeapply" action="takeDrugAppleFor.action" method="post"></form>
	<div class="mt-20">
		<table
			class="table table-border table-bordered table-hover table-bg table-sort">
			<thead>

				<tr class="text-c">
					<th>药品编号</th>
					<th>药品名称</th>
					<th>单位</th>
					<th>规格</th>
					<th>零售价</th>
					<th>请领数量</th>
					<th>申请理由</th>
					<th>总价</th>
				</tr>
			</thead>
			<tbody id="tblDrugList">
			</tbody>
		</table>
	</div>



	<!--_footer 作为公共模版分离出去-->
	<script type="text/javascript" src="../lib/jquery/1.9.1/jquery.min.js"></script>
	<script type="text/javascript" src="../lib/layer/2.4/layer.js"></script>
	<script type="text/javascript" src="../static/h-ui/js/H-ui.min.js"></script>
	<script type="text/javascript"
		src="../static/h-ui.admin/js/H-ui.admin.js"></script>
	<!--/_footer 作为公共模版分离出去-->

	<!--请在下方写此页面业务相关的脚本-->
	<script type="text/javascript"
		src="../lib/My97DatePicker/4.8/WdatePicker.js"></script>
	<script type="text/javascript"
		src="../lib/jquery.validation/1.14.0/jquery.validate.js"></script>

	<script type="text/javascript"
		src="../lib/jquery.validation/1.14.0/messages_zh.js"></script>
	<script type="text/javascript">
		/*药品添加*/
		function member_add(title, url, w, h) {
			layer_show(title, url, w, h);
		}
	</script>
	<script type="text/javascript">
		var count = 0;
		var arr = new Array();
		var i = 100;
		var p = 100;
		for (var k = 0; k < i; k++) {
			arr[k] = new Array(k);
			for (var j = 0; j < p; j++) {
				arr[k][j] = "";
			}
		}

		/*  增加药品请领记录*/
		function addApply() {
			var adminId = $("#adminId").val();
			var drugId = $("#drugId").val();
			var drugName = $("#drugName").val();
			var unit = $("#unit").val();
			var specific = $("#specific").val();
			var retailPrice = $("#retailPrice").val();
			var inventoryNum = $("#inventoryNum").val();
			var applyNum = $("#applyNum").val();
			var applyReason = $("#applyReason").val();
			var applyMoney = retailPrice * applyNum;
			if (drugName == "" || applyNum == "" || applyReason == "") {
				alert("请完善申请信息");
				return;
			}
			if (inventoryNum*1 < applyNum*1) {
				alert("申请数量不能大于库存");
				return;
			}

			var input = "<input type='hidden' name='applyList["+count+"].drugId' value='"+drugId+
		"'><input type='hidden' name='applyList["+count+"].adminId' value='"+adminId+
		"'><input type='hidden' name='applyList["+count+"].applyNum' value='"+applyNum+
		"'><input type='hidden' name='applyList["+count+"].applyReason' value='"+applyReason+
		"'>";
			$("#takeapply").append(input);
			count += 1;

			var str = "<tr id='tr"+count+"' class='text-c'><td>" + drugId
					+ "</td><td>" + drugName + "</td><td>" + unit + "</td><td>"
					+ specific + "</td><td>" + retailPrice + "</td><td>"
					+ applyNum + "</td><td>" + applyReason + "</td><td>"
					+ applyMoney + "</td>";

			$("#tblDrugList").append(str);
		}

		/* 清空申请记录 */
		function empty() {
			$("#tblDrugList").html("");
			$("#takeapply").html("");
			count = 0;
		}

		/* 提交申请 */
		function subApply() {
			var r = confirm("确定提交申请吗?");
			if (r) {
				$("#takeapply").submit();
			}
		}
	</script>
</body>
</html>
