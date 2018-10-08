<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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

<title>退库记录</title>
</head>
<body>
	<nav class="breadcrumb">
		<i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span>
		日常工作 <span class="c-gray en">&gt;</span> 退库记录<a
			class="btn btn-success radius r"
			style="line-height: 1.6em; margin-top: 3px"
			href="javascript:location.replace(location.href);" title="刷新"><i
			class="Hui-iconfont">&#xe68f;</i></a>
	</nav>

	<br>
	<form action="toAllCancApply.action">
		<div class="text-c">
			日期范围： <input
				type="text"
				onfocus="WdatePicker({ minDate:'#F{$dp.$D(\'datemin\')}',maxDate:'%y-%M-%d' })"
				name="afterDate" class="input-text Wdate" id="datemax"":condiBean.afterDate}" style="width: 120px;">
				 - - <input type="text"
				onfocus="WdatePicker({ maxDate:'#F{$dp.$D(\'datemax\')||\'%y-%M-%d\'}' })"
				name="beforeDate" class="input-text Wdate" id="datemin"
				value="${condiBean.beforeDate==null?"":condiBean.beforeDate}" style="width: 120px;"> 申请人:<input
				type="text" class="input-text" style="width: 250px" placeholder=""
				id="" name="adminName" value="${condiBean.adminName==null?"":condiBean.adminName}">
		审核状态:<select name="checkId" >
		<option value="0" >请选择</option>
		<option value="6" <c:if test="${condiBean.checkId==6 }">selected="selected"</c:if>>审核通过</option>
		<option value="8" <c:if test="${condiBean.checkId==8 }">selected="selected"</c:if>>审核未通过</option>
		<option value="7" <c:if test="${condiBean.checkId==7 }">selected="selected"</c:if>>未审核</option>
		 </select>
				
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
					<th>退库药品编号</th>
					<th>退库药品名称</th>
					<th>退库数量</th>
					<th>退库产品批号</th>
					<th>退库申请人</th>
					<th>退库原因</th>
					<th>申请日期</th>
					<th>审核人</th>
					<th>审核日期</th>
					<th>审核情况</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${drugApplyList }" var="caceApply">
					<tr class="text-c">
						<td>${caceApply.stoDrugBean.drugId }</td>
						<td>${caceApply.stoDrugBean.drugName }</td>
						<td>${caceApply.applyNum }</td>
						<td>${caceApply.manuBatch }</td>
						<td>${caceApply.adminBean.adminName }</td>
						<td>${caceApply.applyReason}</td>
						<td>${caceApply.applyDate}</td>
						<td>${caceApply.auditorName}</td>
						<td>${caceApply.checkDate}</td>
						<td class="td-status"><span
							class="label label-success radius">${caceApply.checkName}</span></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<br>
		<div class="text-c">
			<a
				href="toAllCancApply.action?beforeDate=${condiBean.beforeDate}&afterDate=${condiBean.afterDate}&adminName=${condiBean.adminName}&pageNum=${condiBean.pageNum-1}&checkId=${condiBean.checkId}">
				<button type="button" class="btn btn-success">上一页</button>
			</a> ${condiBean.pageNum}/${count} <a
				href="toAllCancApply.action?beforeDate=${condiBean.beforeDate}&afterDate=${condiBean.afterDate}&adminName=${condiBean.adminName}&pageNum=${condiBean.pageNum+1}&checkId=${condiBean.checkId}"><button
					type="button" class="btn btn-success">下一页</button></a>
		</div>
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
		src="../lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
	<script type="text/javascript" src="../lib/laypage/1.2/laypage.js"></script>

</body>
</html>