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
<!--[if lt IE 9]>
<script type="text/javascript" src="lib/html5shiv.js"></script>
<script type="text/javascript" src="lib/respond.min.js"></script>
<![endif]-->
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

<title>药品采购申请</title>
<link rel="stylesheet"
	href="<%=path%>lib/zTree/v3/css/zTreeStyle/zTreeStyle.css"
	type="text/css">
</head>
<body class="pos-r">

	<div style="margin-left: 0px;">
		<nav class="breadcrumb">
			<i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span>
			药库工作 <span class="c-gray en">&gt;</span>药品采购申请记录<a
				class="btn btn-success radius r"
				style="line-height: 1.6em; margin-top: 3px"
				href="javascript:location.replace(location.href);" title="刷新"><i
				class="Hui-iconfont">&#xe68f;</i></a>
		</nav>
		<div class="page-container">
			<form id="audit" action="purchaseApplyLook.action" method="post">
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
						style="width: 250px" class="input-text"> 审核状态：<select
						name="checkId" id="checkId">
						<option value="0">请选择</option>
						<option value="23"
							<c:if test="${checkId==23}">selected="selected"</c:if>>待购买</option>
						<option value="8"
							<c:if test="${checkId==8}">selected="selected"</c:if>>审核未通过</option>
						<option value="7"
							<c:if test="${checkId==7}">selected="selected"</c:if>>未审核</option>
					</select>
					<button name="serachRecord" id="serachRecord"
						class="btn btn-success" type="submit">
						<i class="Hui-iconfont">&#xe665;</i>搜索
					</button>
				</div>
			</form>
			<div class="cl pd-5 bg-1 bk-gray mt-20">
				<a class="btn btn-success" onclick="exportExcel()"> 导出Excel </a> <span
					class="r">共有数据：<strong>${count}</strong> 条
				</span>
			</div>
			<div class="mt-20">
				<table class="table table-border table-bordered table-bg">
					<thead>
						<tr class="text-c">
							<th width="40">药品编码</th>
							<th width="60">药品名称</th>
							<th width="100">申请数量</th>
							<th width="100">申请时间</th>
							<th width="100">申请理由</th>
							<th width="60">申请人</th>
							<th width="60">审核状态</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${drugApplyList}" var="auditApply">
							<tr class="text-c">
								<td>${auditApply.stoDrugBean.drugId}</td>
								<td>${auditApply.stoDrugBean.drugName}</td>
								<td>${auditApply.applyNum}</td>
								<td>${auditApply.applyDate}</td>
								<td>${auditApply.applyReason}</td>
								<td>${auditApply.adminBean.adminName}</td>
								<td class="td-status"><span
									class="label label-success radius">${auditApply.checkName}</span></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				</br>
				<div class="text-c">
					<button type="button" class="btn btn-secondary-outline radius"
						onclick="prePage('${condiBean.pageNum}')">上一页</button>
					<label class="label label-default radius"><font size="2">当前页${condiBean.pageNum}/共${pageTotal}页</font></label>
					<button type="button" class="btn btn-secondary-outline radius"
						onclick="nextPage('${condiBean.pageNum}', '${pageTotal}')">下一页</button>
					<input type="text" style="width: 30px" class="input-text" id="page"
					  onkeyup="this.value=this.value.replace(/\D/g,'')"
						onafterpaste="this.value=this.value.replace(/\D/g,'')">
					<button type="submit" class="btn btn-secondary-outline radius"
						onclick="goPage('${pageTotal}')">
						跳转
						</button>
				</div>
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
		
	</script>
	<script type="text/javascript">
		/*上一页  */
		function prePage(pageNum) {
			var str1 = "";
			if (pageNum > 1) {
				pageNum -= 1;
				str1 = "purchaseApplyLook.action?pageNum=" + pageNum;
			} else {
				return;
			}
			$("#audit").attr("action", str1);
			$("#audit").submit();
		}
		/*下一页  */
		function nextPage(pageNum, total) {
			var str2 = "";
			if (pageNum < total) {
				pageNum = Number(pageNum) + 1;
				str2 = "purchaseApplyLook.action?pageNum=" + pageNum;

			} else {
				return;
			}
			$("#audit").attr("action", str2);

			$("#audit").submit();
		}

		/*跳转页面*/
		function goPage(total) {
			var pageNum = $("#page").val();
			var str2 = "";
			if (pageNum > total || pageNum == 0 || pageNum == "") {
				return;
			} else {
				str2 = "purchaseApplyLook.action?pageNum=" + pageNum;
			}
			$("#audit").attr("action", str2);
			$("#audit").submit();
		}

		/* 导出Excel */
		function exportExcel(afterDate, beforeDate, adminName) {
			var afterDate = $("#logmin").val();
			var beforeDate = $("#logmax").val();
			var adminName = $("#adminName").val();
			var r = confirm("确认导出Excel文件？");
			if (r) {
				location.href = "exportExcel.action?afterDate=" + afterDate
						+ "&beforeDate=" + beforeDate + "&adminName="
						+ adminName;
			}
		}
	</script>
</body>
</html>