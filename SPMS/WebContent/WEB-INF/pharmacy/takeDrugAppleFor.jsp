<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
<!--[if lt IE 9]>
<script type="text/javascript" src="lib/html5shiv.js"></script>
<script type="text/javascript" src="lib/respond.min.js"></script>
<![endif]-->
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
			style="width: 150px" class="input-text" readonly="readonly">&nbsp;
		<button name="F1" id="F1" class="btn btn-primary btn-sm"
			data-toggle="modal" data-target="#myModal">F1</button>
		&emsp;药品名称：<input type="text" name="drugName" id="drugName"
			placeholder="药品名称" style="width: 150px" class="input-text" disabled />
		&emsp;单位：<input type="text" name="unit" id="unit" placeholder="药品单位"
			style="width: 150px" class="input-text" disabled /> &emsp;规格：<input
			type="text" name="specific" id="specific" placeholder="药品规格"
			style="width: 150px" class="input-text" disabled /> <br>申请人：<input
			type="text" name="adminName" id="adminName" placeholder="请输入申请人"
			style="width: 150px; margin-top: 5px" class="input-text" />
		&emsp;数量：<input type="text" name="applyNum" id="applyNum"
			placeholder="请输入申请数量" style="width: 150px; margin-top: 5px"
			class="input-text" /> &emsp;申请理由：<input type="text"
			name="applyReason" id="applyReason" placeholder="请输入申请理由"
			style="width: 250px; margin-top: 5px" class="input-text" /> <a
			class="btn btn-primary radius" onclick=""><i class="Hui-iconfont">&#xe600;</i>
			增加</a> <br>
	</div>
	<div class="mt-20">
		<table
			class="table table-border table-bordered table-hover table-bg table-sort">
			<thead>

				<tr>
					<th>药品编号</th>
					<th>药品名称</th>
					<th>规格</th>
					<th>零售价</th>
					<th>是否抗生素</th>
					<th>药品类别</th>
					<th>药库库存</th>
					<th>请领数量</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
			</tbody>
		</table>
	</div>

	<!-- 模态框（Modal） -->
	<div class="modal fade bs-example-modal-lg" id="myModal" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">药品添加</h4>
				</div>
				<div class="modal-body">
					拼音码：<input type="text" name="pinyinCode" id="ajpinyinCode"
						style="width: 100px" class="input-text" /> &emsp; 药品编码：<input
						type="text" name="drugId" id="ajdrugId" style="width: 100px"
						class="input-text" /> &emsp; 药品名称：<input type="text"
						name="drugName" id="ajdrugName" style="width: 100px"
						class="input-text" /> &emsp;
					<button name="searchDrug" id="searchDrug" class="btn btn-success"
						onclick="selDrug()">
						<i class="Hui-iconfont">&#xe665;</i>查询
					</button>
				</div>

				<div class="mt-20">
					<table
						class="table table-border table-bordered table-bg table-hover table-sort">
						<thead>
							<tr class="text-c">
								<th width="100">药品编码</th>
								<th width="100">药品名称</th>
								<th width="80">规格</th>
								<th width="80">单位</th>
								<th width="100">剂型</th>
								<th width="100">生产厂商</th>
								<th width="100">药品产地</th>
							</tr>
						</thead>
						<tbody>
							<tr class="text-c va-m">
								<td>001</td>
								<td></td>
								<td class="text-l"></td>
								<td class="text-l"></td>
								<td></td>
								<td></td>
								<td></td>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭
					</button>
					<button type="button" class="btn btn-primary">确定选择</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
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
		src="../lib/jquery.contextmenu/jquery.contextmenu.r2.js"></script>
	<script type="text/javascript">
		function selDrug() {
			alert($("#ajdrugId").val());
			$.ajax({
				contentType : "application/json; charset=utf-8",
				url : "selDrug.action",
				data : "drugId=" + $("#ajdrugId").val() + "drugName="
						+ $("#ajdrugName").val() + "pinyinCode="
						+ $("#ajpinyinCode").val(),

				dataType : "json",
				type : "POST",

				success : function(data) {
					$("#drugId").html(data);
				}

			});
		}
	</script>
</body>
</html>
