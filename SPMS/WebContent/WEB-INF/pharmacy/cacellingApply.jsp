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

<title>药品退库</title>

</head>
<body>
	<nav class="breadcrumb">
		<i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span>
		日常工作 <span class="c-gray en">&gt;</span> 药品退库<a
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
			onclick="member_add('选择药品','toChooseCaceDrug.action','','510')"
			class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i>
			选择药品</a> &emsp;药品名称：<input type="text" name="drugName" id="drugName"
			placeholder="药品名称" style="width: 150px" class="input-text" disabled />&emsp;
		申请人：<input type="text" name="adminName" id="adminName"
			placeholder="${admin.adminName}" disabled
			style="width: 130px; margin-top: 5px" class="input-text" />
		&emsp;当时领取数量：<input type="text"  id="handleNum"
			 disabled style="width: 130px; margin-top: 5px" class="input-text" /> <br>
		&emsp;药房库存数量：<input type="text" id=drugNum
			style="width: 100px; margin-top: 5px" class="input-text" disabled />
		&emsp;退库数量：<input type="text" name="applyNum" id="applyNum"
			placeholder="请输入申请数量" style="width: 100px; margin-top: 5px"
			class="input-text" /> &emsp;退库理由：<input type="text"
			name="applyReason" id="applyReason" placeholder="请输入申请理由"
			style="width: 250px; margin-top: 5px" class="input-text" /> <a
			class="btn btn-primary radius" onclick="addApply()"><i
			class="Hui-iconfont">&#xe600;</i> 增加</a> <input type="hidden"
			id="adminId" value="${admin.adminId}"><br> <br>
		<div align="right">
			<button type="button" class="btn btn-primary" onclick="empty()">清空</button>
			&nbsp;&nbsp;
			<button type="button" class="btn btn-default" onclick="subApply()">提交申请</button>
		</div>
	</div>
	<input type="hidden" id="drugmanu">
	<input type="hidden" id="proDate">
	<input type="hidden" id="putBatch">
	<input type="hidden" id="manuBatch">
	<form id="fapply" action="cacellingApply.action" method="post"></form>
	<div class="mt-20">
		<table
			class="table table-border table-bordered table-hover table-bg table-sort">
			<thead>

				<tr class="text-c">
					<th>药品编号</th>
					<th>药品名称</th>
					<th>入库批次</th>
					<th>生产厂家</th>
					<th>厂家批次</th>
					<th>生产日期</th>
					<th>退库数量</th>
					<th>退库理由</th>

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
			var drugNum=$("#drugNum").val();
			var adminId = $("#adminId").val();
			var drugId = $("#drugId").val();
			var drugName = $("#drugName").val();
			var drugmanu = $("#drugmanu").val();
			var handleNum = $("#handleNum").val();
			var proDate = $("#proDate").val();
			var putBatch = $("#putBatch").val();
			var manuBatch = $("#manuBatch").val();
			var applyNum = $("#applyNum").val();
			var applyReason = $("#applyReason").val();
			if (drugName == "" || applyNum == "" || applyReason == "") {
				alert("请完善退库药品信息");
				return;
			}if(applyNum>handleNum){
				alert("不同批次药品请分开填写申请");
				return;
			}if(applyNum>drugNum){
				alert("请核对退库数量");
				return;
			}

			var input = "<input type='hidden' name='applyList["+count+"].drugId' value='"+drugId+
		"'><input type='hidden' name='applyList["+count+"].adminId' value='"+adminId+
		"'><input type='hidden' name='applyList["+count+"].applyNum' value='"+applyNum+
		"'><input type='hidden' name='applyList["+count+"].applyReason' value='"+applyReason+
		"'><input type='hidden' name='applyList["+count+"].manuBatch' value='"+manuBatch+
		"'><input type='hidden' name='applyList["+count+"].putBatch' value='"+putBatch+
		"'>";
			$("#fapply").append(input);
			count += 1;

			var str = "<tr id='tr"+count+"' class='text-c'><td>" + drugId
					+ "</td><td>" + drugName + "</td><td>" + putBatch
					+ "</td><td>" + drugmanu + "</td><td>" + manuBatch
					+ "</td><td>" + proDate + "</td><td>" + applyNum
					+ "</td><td>" + applyReason + "</td>";

			$("#tblDrugList").append(str);
		}

		/* 清空申请记录 */
		function empty() {
			$("#tbodyId").html("");
			$("#fapply").html("");
			count = 0;
		}

		/* 提交申请 */
		function subApply() {
			var r = confirm("确定提交吗?");
			if (r) {
				$("#fapply").submit();
			}
		}
	</script>
</body>
</html>
