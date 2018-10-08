<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
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
	href="<%=path%>static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css"
	href="<%=path%>static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css"
	href="<%=path%>lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css"
	href="<%=path%>static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css"
	href="<%=path%>static/h-ui.admin/css/style.css" />

<title>退厂记录</title>
</head>
<body>
	<nav class="breadcrumb">
		<i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span>
		日常工作 <span class="c-gray en">&gt;</span> 退厂记录<a
			class="btn btn-success radius r"
			style="line-height: 1.6em; margin-top: 3px"
			href="javascript:location.replace(location.href);" title="刷新"><i
			class="Hui-iconfont">&#xe68f;</i></a>
	</nav>

	<br>
	<form id="returnApply" action="toAllReturnManuApply.action"
		method="post">
		<div class="text-c">
			日期范围： <input type="text"
				onfocus="WdatePicker({ maxDate:'#F{$dp.$D(\'logmax\')||\'%y-%M-%d\'}' })"
				id="logmin" name="afterDate" class="input-text Wdate"
				value="${condiBean.afterDate==null?'':condiBean.afterDate}"
				style="width: 120px;"> - <input type="text"
				onfocus="WdatePicker({ minDate:'#F{$dp.$D(\'logmin\')}',maxDate:'%y-%M-%d' })"
				id="logmax" name="beforeDate" class="input-text Wdate"
				value="${condiBean.beforeDate==null?'':condiBean.beforeDate}"
				style="width: 120px;"> 申请人：<input type="text"
				name="adminName" id="adminName" placeholder="申请人"
				value="${condiBean.adminName==null?'':condiBean.adminName}"
				style="width: 250px" class="input-text">
			<button type="submit" class="btn btn-success">
				<i class="Hui-iconfont">&#xe665;</i> 搜索
			</button>
		</div>
	</form>
	<div class="mt-20">
		<table class="table table-border table-bordered table-bg">
			<thead>
				<tr>
					<th scope="col" colspan="12">退库列表</th>
				</tr>
				<tr class="text-c">
					<th>退厂药品编号</th>
					<th>退厂药品名称</th>
					<th>退厂数量</th>
					<th>退厂产品批号</th>
					<th>退厂药品金额(元)</th>
					<th>退厂申请人</th>
					<th>退厂原因</th>
					<th>申请日期</th>
					<th>审核人</th>
					<th>审核日期</th>
					<th>审核情况</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${drugApplyList }" var="returnApply">
					<tr class="text-c">
						<td>${returnApply.stoDrugBean.drugId }</td>
						<td>${returnApply.stoDrugBean.drugName }</td>
						<td>${returnApply.applyNum }</td>
						<td>${returnApply.manuBatch }</td>
						<td>${returnApply.applyNum*returnApply.bdBean.purPrice}</td>
						<td>${returnApply.adminBean.adminName }</td>
						<td>${returnApply.applyReason}</td>
						<td>${returnApply.applyDate}</td>
						<td>${returnApply.adminBean.auditorName}</td>
						<td>${returnApply.checkDate}</td>
						<td class="td-status"><span
							class="label label-success radius">${returnApply.checkName}</span></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<br>
		<div class="text-c">
			<button type="button" class="btn btn-success"
				onclick="prePage('${condiBean.pageNum}')">上一页</button>
			${condiBean.pageNum}/${pageTotal}
			<button type="button" class="btn btn-success"
				onclick="nextPage('${condiBean.pageNum}', '${pageTotal}')">下一页</button>
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
		src="<%=path%>lib/My97DatePicker/4.8/WdatePicker.js"></script>
	<script type="text/javascript"
		src="<%=path%>lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
	<script type="text/javascript"
		src="<%=path%>lib/laypage/1.2/laypage.js"></script>
	<script type="text/javascript">
		/*上一页  */
		function prePage(pageNum) {
			var str1 = "";
			if (pageNum > 1) {
				pageNum -= 1;
				str1 = "toAllReturnManuApply.action?pageNum=" + pageNum;
			} else {
				return;
			}
			$("#returnApply").attr("action", str1);
			$("#returnApply").submit();
		}
		/*下一页  */
		function nextPage(pageNum, total) {
			var str2 = "";
			if (pageNum < total) {
				pageNum = Number(pageNum) + 1;
				str2 = "toAllReturnManuApply.action?pageNum=" + pageNum;

			} else {
				return;
			}
			$("#returnApply").attr("action", str2);
			$("#returnApply").submit();
		}
	</script>
</body>
</html>