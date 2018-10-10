<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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

<title>提醒消息</title>

</head>
<body>

	<form id="fapply" method="post"></form>
	<div class="mt-20">
		<table class="table table-border table-bordered table-bg">
			<thead>
				<tr>
					<th scope="col" colspan="12">消息提醒列表</th>
				</tr>
				<tr class="text-c">
					<th>提醒类型</th>
					<th>事件详情</th>
					<th>提醒时间</th>
					<th>处理操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${warnList}" var="warnList">
					<tr class="text-c">
						<td>${warnList.eventName }</td>
						<td>${warnList.warnEvent }</td>
						<td>${warnList.warnDate }</td>
						<td><a href="affirmWarn.action?warnId=${warnList.warnId}"><button onclick="setCount('${warnCount}')" class="btn btn-success">确认已处理</button></a></td>

					</tr>
				</c:forEach>
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
		
	</script>
	
	<script type="text/javascript">
		function setCount(count){
			parent.$("#warnId").html(count-1);
		}
	</script>
</body>
</html>
