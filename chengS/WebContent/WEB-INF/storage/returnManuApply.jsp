<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />

<link rel="stylesheet" type="text/css"
	href="<%=path%>static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css"
	href="<%=path%>static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css"
	href="<%=path%>lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css"
	href="<%=path%>static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css"
	href="<%=path%>static/h-ui.admin/css/style.css" />
<title>退还厂家申请</title>
<link rel="stylesheet"
	href="<%=path%>lib/zTree/v3/css/zTreeStyle/zTreeStyle.css"
	type="text/css">
</head>
<body class="pos-r">

	<div style="margin-left: 0px;">
		<nav class="breadcrumb">
			<i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span>
			药库工作 <span class="c-gray en">&gt;</span>退还厂家<a
				class="btn btn-success radius r"
				style="line-height: 1.6em; margin-top: 3px"
				href="javascript:location.replace(location.href);" title="刷新"><i
				class="Hui-iconfont">&#xe68f;</i></a>
		</nav>
		<div class="page-container">
			<div class="text-c">
				<table class="table table-border table-bordered table-bg">
					<tr>
						<td>药品编码：<input type="text" name="drugId" id="drugId"
							placeholder="药品编码" style="width: 150px" class="input-text"
							readonly="readonly">&nbsp;<a href="javascript:;"
							onclick="drug_select('库存批次浏览','<%=path%>storage/getInventoryBatchList.action','','510')"
							class="btn btn-primary btn-sm">F1</a></td>
						<td>药品名称：<input type="text" name="drugName" id="drugName"
							placeholder="药品名称" style="width: 150px" class="input-text"
							readonly="readonly" /></td>
						<td>单位：<input type="text" name="unit" id="unit"
							placeholder="药品单位" style="width: 150px" class="input-text"
							readonly="readonly" /></td>
						<td>该批次剩余数量：<input type="text" name="handleNum"
							id="handleNum" placeholder="原采购数量" style="width: 150px"
							class="input-text" readonly="readonly" />
						</td>
					</tr>
					<tr>
						<td>生产厂商：<input type="text" name="stoDrugBean.drugmanu"
							id="drugmanu" placeholder="生产厂商" style="width: 150px"
							class="input-text" readonly="readonly" /></td>
						<td>生产批号：<input type="text" name="manuBatch" id="manuBatch"
							placeholder="生产批号" style="width: 150px" class="input-text"
							readonly="readonly" /></td>
						<td>库存数量：<input type="text" name="inventoryBean.inventoryNum"
							id="inventoryNum" placeholder="库存数量" style="width: 150px"
							class="input-text" readonly="readonly" /></td>
						<td>进价(元)：<input type="text" name="purPrice" id="purPrice"
							style="width: 150px; margin-top: 5px" class="input-text"
							readonly="readonly" /></td>
					</tr>
					<tr>
						<td>申请人：<input type="text" name="adminName" id="adminName"
							style="width: 150px; margin-top: 5px" class="input-text"
							value="${sessionScope.User.adminName}" readonly="readonly" /></td>
						<td><span>退还数量：<input type="text" name="applyNum"
								id="applyNum" placeholder="请输入申请数量"
								style="width: 150px; margin-top: 5px" class="input-text"
								onkeyup="this.value=this.value.replace(/\D/g,''),checkInput()"
								onafterpaste="this.value=this.value.replace(/\D/g,'')"
							     /> </br> <label
								id="hint"></label></span></td>
						<td>退还理由：<input type="text" name="applyReason"
							id="applyReason" placeholder="请输入申请理由"
							style="width: 200px; margin-top: 5px" class="input-text" /> <input
							type="hidden" id="proDate" /> 
							<input type="hidden" id="adminId" name="adminId" value="${User.adminId}"></td>
							<input type="hidden" id="putBatch" name="putBatch">
							<input type="hidden" id="batchDetailId" name="batchDetailId">
						<td><button type="button" class="btn btn-primary" id=""
								onclick="addApply()">添加</button></td>
					</tr>
				</table>

			</div>

			<form id="papply" action="returnManuApply.action" method="post">
			</form>

			<div class="cl pd-5 bg-1 bk-gray mt-20">
				<div align="right">
					<button type="button" class="btn btn-primary" id=""
						onclick="empty()">清空</button>
					&nbsp;&nbsp;
					<button type="button" class="btn btn-primary" id=""
						onclick="subApply()">提交申请</button>
				</div>
			</div>
				<div class="mt-20">
					<table class="table table-border table-bordered table-bg"
						id="tblDrugList">
						<thead>
							<tr class="text-c">
								<th width="80">药品编码</th>
								<th width="100">药品名称</th>
								<th width="100">生产厂商</th>
								<th width="100">生产批号</th>
								<th width="100">生产日期</th>
								<th width="100">退还数量</th>
								<th width="100">退还理由</th>
							</tr>
						</thead>
						<tbody id="tbodyId"></tbody>
					</table>
				</div>
		</div>
	</div>

	<!--_footer 作为公共模版分离出去-->
	<script type="text/javascript"
		src="<%=path%>lib/jquery/1.9.1/jquery.min.js"></script>
	<script type="text/javascript" src="<%=path%>lib/layer/2.4/layer.js"></script>
	<script type="text/javascript"
		src="<%=path%>static/h-ui/js/H-ui.min.js"></script>
	<script type="text/javascript"
		src="<%=path%>static/h-ui.admin/js/H-ui.admin.js"></script>
	<!--/_footer 作为公共模版分离出去-->

	<!--请在下方写此页面业务相关的脚本-->
	<script type="text/javascript"
		src="<%=path%>lib/zTree/v3/js/jquery.ztree.all-3.5.min.js"></script>
	<script type="text/javascript"
		src="<%=path%>lib/My97DatePicker/4.8/WdatePicker.js"></script>
	<script type="text/javascript"
		src="<%=path%>lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
	<script type="text/javascript"
		src="<%=path%>lib/laypage/1.2/laypage.js"></script>
	<script type="text/javascript">
		/*库存批次浏览*/
		function drug_select(title, url, w, h) {
			layer_show(title, url, w, h);
		}

		/**判断申请的数量是否小于当前批次剩余数量*/
		function checkInput() {
			var handleNum = $("#handleNum").val();
			var applyNum = $("#applyNum").val();
			if ((applyNum-handleNum)>0) {
				$("#hint").html("超过当前储存量");
				$("#hint").css("color", "red");
				return;
			}else{
				$("#hint").html("");
			}
		}
		
		var count = 0;
		/*  增加药品退还申请记录*/
		function addApply() {

			var drugId = $("#drugId").val();
			var adminId = $("#adminId").val();
			var drugName = $("#drugName").val();
			var applyReason = $("#applyReason").val();
			var adminName = $("#adminName").val();
			var handleNum = $("#handleNum").val();
			var applyNum = $("#applyNum").val();
			var drugmanu = $("#drugmanu").val();
			var inventoryNum = $("#inventoryNum").val();
			var manuBatch = $("#manuBatch").val();
			var purPrice = $("#purPrice").val();
			var proDate = $("#proDate").val();
			var putBatch = $("#putBatch").val();
			var batchDetailId = $("#batchDetailId").val();
			var d = $("#" + drugId).val();
			if (applyNum == "" || applyReason == "") {
				alert("请完善退还申请信息");
				return;
			}
			if (typeof (d) == "undefined") {
				var input = "<input type='hidden'id='"+drugId+"' name='applyList["+count+"].drugId' value='"+drugId+
		"'><input type='hidden' name='applyList["+count+"].stoDrugBean.drugName' value='"+drugName+
		"'><input type='hidden' name='applyList["+count+"].adminId' value='"+adminId+
		"'><input type='hidden' name='applyList["+count+"].bdBean.handleNum' value='"+handleNum+
		"'><input type='hidden' name='applyList["+count+"].stoDrugBean.drugmanu' value='"+drugmanu+
		"'><input type='hidden' name='applyList["+count+"].manuBatch' value='"+manuBatch+
		"'><input type='hidden' name='applyList["+count+"].bdBean.proDate' value='"+proDate+
		"'><input type='hidden' name='applyList["+count+"].applyNum' value='"+applyNum+
		"'><input type='hidden' name='applyList["+count+"].applyReason' value='"+applyReason+
		"'><input type='hidden' name='applyList["+count+"].bdBean.putBatch' value='"+putBatch+
		"'><input type='hidden' name='applyList["+count+"].inventoryNum' value='"+inventoryNum+
		"'><input type='hidden' name='applyList["+count+"].bdBean.batchDetailId' value='"+batchDetailId+
		"'>";
				$("#papply").append(input);
				count += 1;

				var str = "<tr id='tr"+count+"' class='text-c'><td>" + drugId
						+ "</td><td>" + drugName + "</td><td>" + drugmanu
						+ "</td><td>" + manuBatch + "</td><td>" + proDate
						+ "</td><td>" + applyNum + "</td><td>" + applyReason
						+ "</td></tr> ";

				$("#tblDrugList").append(str);
			} else {
				alert("该药品已经添加，如需修改数量请清空重新选择");
				return;
			}
		}

		/* 清空申请记录 */
		function empty() {
			var r=confirm("确认清空列表？");
			if(r){
				
			$("#tbodyId").html("");
			$("#papply").html("");
			count = 0;
			}
		}

		/* 提交申请 */
		function subApply() {
			var d=$("#tbodyId").html();
			if(d.length==0){
				
			alert("请选择药品");
			return;
			}
			var r = confirm("确定提交吗?");
			if (r) {
				$("#papply").submit();
			}
		}
	</script>
</body>
</html>