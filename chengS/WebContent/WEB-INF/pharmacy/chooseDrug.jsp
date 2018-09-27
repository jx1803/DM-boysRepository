<%@ page language="java" import="java.util.*"
	import="org.great.bean.StoDrugBean" pageEncoding="UTF-8"%>
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
<link rel="Bookmark" href="/favicon.ico">
<link rel="Shortcut Icon" href="/favicon.ico" />
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
<title>药品选择</title>
<script type="text/javascript">
	function change() {

		if ($("#drugid").val() != null && $("#drugid").val() != '') {
			$("#drugId").val($("#drugid").val());
		}

	}
</script>
<script type="text/javascript">
	function setValue(drugId, drugName, unit, specific, inventoryNum,
			retailPrice) {
		//给父窗口赋值
		parent.$("#drugId").val(drugId);
		parent.$("#drugName").val(drugName);
		parent.$("#unit").val(unit);
		parent.$("#specific").val(specific);
		parent.$("#inventoryNum").val(inventoryNum);
		parent.$("#retailPrice").val(retailPrice);
		parent.$("#applyNum").val('');
		parent.$("#applyReason").val('');

		//赋完值后关闭窗口
		var index = parent.layer.getFrameIndex(window.name);
		parent.layer.close(index);
	}
</script>
</head>
<body>
	<article class="page-container">
		<form class="form form-horizontal" method="post"
			action="selDrug.action" onsubmit="change()">
			<div class="modal-body">
				拼音码：<input type="text" name="pinyinCode" style="width: 100px"
					class="input-text" /> &emsp; 药品编码：<input type="text" id="drugid"
					style="width: 100px" class="input-text" /><input type="hidden"
					id="drugId" name="drugId" value="-1"> &emsp; 药品名称：<input
					type="text" name="drugName" style="width: 100px" class="input-text" />
				&emsp;
				<button name="searchDrug" id="searchDrug" class="btn btn-success"
					type="submit">
					<i class="Hui-iconfont">&#xe665;</i>查询
				</button>
			</div>
		</form>
		<div class="mt-20">
			<form action="" method="post">
				<table
					class="table table-border table-bordered table-bg table-hover table-sort">
					<thead>
						<tr class="text-c">
							<th>药品编码</th>
							<th>药品名称</th>
							<th>规格</th>
							<th>零售价</th>
							<th>是否抗生素</th>
							<th>药品剂型</th>
							<th>药库库存</th>
							<th>选择</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${drugList }" var="drugList">
							<tr class="text-c">
								<td>${drugList.drugId }</td>
								<td>${drugList.drugName}</td>
								<td>${drugList.specific }</td>
								<td>${drugList.retailPrice }</td>
								<td>${drugList.antibiotic }</td>
								<td>${drugList.dfBean.dosageForm }</td>
								<td>${drugList.inventoryBean.inventoryNum}</td>
								<td><a class="btn btn-success"
									onclick="setValue('${drugList.drugId}','${drugList.drugName}','${drugList.unit}','${drugList.specific }','${drugList.inventoryBean.inventoryNum}','${drugList.retailPrice }')">确定</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</form>
		</div>
		<br>

	</article>

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

</body>
</html>