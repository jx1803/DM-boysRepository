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
<title>库存批次浏览</title>
<link rel="stylesheet"
	href="<%=path%>lib/zTree/v3/css/zTreeStyle/zTreeStyle.css"
	type="text/css">
</head>
<body class="pos-r">
	<div class="page-container">
		<form id="con" action="<%=path%>storage/getInventoryBatchList.action"
			method="post">
			<div class="text-c">
				拼音码：<input type="text" name="pinyinCode" id="pinyinCode"
					style="width: 100px" class="input-text" /> &emsp; 药品编码：<input
					type="text" name="drugId" id="drugId" value="" style="width: 100px"
					class="input-text" /> <input type="hidden" name="hdrugId"
					id="hdrugId" value="0" style="width: 100px" class="input-text" />
				&emsp; 药品名称：<input type="text" name="drugName" id="drugName"
					style="width: 100px" class="input-text" /> &emsp; 生产批号：<input
					type="text" name="manuBatch" id="manuBatch" style="width: 100px"
					class="input-text" />
				<div align="right" style="margin-top:10px;">
					<button name="searchDrug" id="searchDrug" class="btn btn-success"
						onclick="submitCon()">
						<i class="Hui-iconfont">&#xe665;</i>查询库存批次
					</button>
				</div>
			</div>
		</form>
		<div class="mt-20">
			<table
				class="table table-border table-bordered table-bg table-hover table-sort">
				<thead>
					<tr class="text-c">
						<th width="100">药品编码</th>
						<th width="100">药品名称</th>
						<th width="80">单位</th>
						<th width="100">当前库存</th>
						<th width="100">该批次剩余数量</th>
						<th width="100">进价</th>
						<th width="100">生产厂商</th>
						<th width="150">生产日期</th>
						<th width="100">产品批号</th>
						<th width="100">操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${inBatchList}" var="inBatchBean">
						<tr id="tr" class="text-c va-m">
							<td>${inBatchBean.stoDrugBean.drugId}</td>
							<td>${inBatchBean.stoDrugBean.drugName}</td>
							<td>${inBatchBean.stoDrugBean.unit}</td>
							<td>${inBatchBean.inventoryBean.inventoryNum}</td>
							<td>${inBatchBean.handleNum}</td>
							<td>${inBatchBean.purPrice}</td>
							<td>${inBatchBean.stoDrugBean.drugmanu}</td>
							<td>${inBatchBean.proDate}</td>
							<td>${inBatchBean.manuBatch}</td>
							<td><a class="btn btn-primary"
								onclick="addDrug('${inBatchBean.stoDrugBean.drugId}','${inBatchBean.stoDrugBean.drugName}','${inBatchBean.stoDrugBean.unit}','${inBatchBean.inventoryBean.inventoryNum}','${inBatchBean.handleNum}','${inBatchBean.purPrice}','${inBatchBean.stoDrugBean.drugmanu}','${inBatchBean.proDate}','${inBatchBean.manuBatch}','${inBatchBean.putBatch}',${inBatchBean.batchDetailId})">选择</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>

	<!--_footer 作为公共模版分离出去-->
	<script type="text/javascript"
		src="<%=path%>lib/jquery/1.9.1/jquery.min.js"></script>
	<script type="text/javascript"
		src="<%=path%>lib/jquery.validation/1.14.0/jquery.validate.js"></script>
	<script type="text/javascript"
		src="<%=path%>lib/jquery.validation/1.14.0/messages_zh.js"></script>

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
		/* 选择添加药品 */
		function addDrug(drugId, drugName, unit, inventoryNum, handleNum,purPrice,
			drugmanu, proDate, manuBatch,putBatch,batchDetailId) {
			parent.$("#drugId").val(drugId);
			parent.$("#drugName").val(drugName);
			parent.$("#unit").val(unit);
			parent.$("#inventoryNum").val(inventoryNum);
			parent.$("#handleNum").val(handleNum);
			parent.$("#purPrice").val(purPrice);
			parent.$("#drugmanu").val(drugmanu);
			parent.$("#proDate").val(proDate);
			parent.$("#manuBatch").val(manuBatch);
			parent.$("#putBatch").val(putBatch);
			parent.$("#batchDetailId").val(batchDetailId);
			
			
			//赋完值后关闭窗口
			var index = parent.layer.getFrameIndex(window.name);
			parent.layer.close(index);

		}

		function submitCon() {
			//on 绑定函数
			$('#con').on('submit', function(event) {
				//event.preventDefault();//阻止事件默认动作
				$(this).validate(); //return true or false;
			});

			$("#con").validate({
				rules : {
					"drugId" : {
						digits : true,
					}
				},
				messages : {
					"drugId" : {
						digits : "请输入数字",
					}
				}
			});
			if ($("#drugId").val().length == 0) {
				var hdrugId = $("#hdrugId").val();
				$("#drugId").val(hdrugId);
			}
		}
	</script>
</body>
</html>