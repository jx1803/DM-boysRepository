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

<title>过期预警</title>

</head>
<body>
	<br>
	<nav class="breadcrumb">
		<i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span>
		日常工作 <span class="c-gray en">&gt;</span>过期预警<a
			class="btn btn-success radius r"
			style="line-height: 1.6em; margin-top: 3px"
			href="javascript:location.replace(location.href);" title="刷新"><i
			class="Hui-iconfont">&#xe68f;</i></a>
	</nav>
	<br>
	<form action="toOverdueWarn.action">
		<div class="text-c">
			日期范围： <input
				type="text"
				onfocus="WdatePicker({ minDate:'#F{$dp.$D(\'datemin\')}',maxDate:'%y-%M-%d' })"
				name="afterDate" class="input-text Wdate" id="datemax"
				value="${condiBean.afterDate==null?"":condiBean.afterDate}"  style="width: 120px;">
				 - - <input type="text"
				onfocus="WdatePicker({ maxDate:'#F{$dp.$D(\'datemax\')||\'%y-%M-%d\'}' })"
				name="beforeDate" class="input-text Wdate" id="datemin"
				value="${condiBean.beforeDate==null?"":condiBean.beforeDate}" style="width: 120px;">
				处理状态:<select name="checkId" >
		<option value="0" >请选择</option>
		<option value="30" <c:if test="${condiBean.checkId==30 }">selected="selected"</c:if>>已处理</option>
		<option value="31" <c:if test="${condiBean.checkId==31 }">selected="selected"</c:if>>未处理</option>
		 </select>	
			<button type="submit" class="btn btn-success">
				<i class="Hui-iconfont">&#xe665;</i> 搜索
			</button>
		</div>
	</form>

	<form id="fapply" method="post"></form>
	<div class="mt-20">
		<table class="table table-border table-bordered table-bg">
			<thead>

				<tr>
					<th scope="col" colspan="12">过期预警列表</th>
				</tr>
				<tr class="text-c">
					<th>事件详情</th>
					<th>提醒时间</th>
					<th>处理操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${warnList}" var="warnList">
					<tr class="text-c">
						<td>${warnList.warnEvent }</td>
						<td>${warnList.warnDate }</td>
							<td class="td-status"><span
							class="label label-success radius">${warnList.disposeName}</span></td>

					</tr>
				</c:forEach>
			</tbody>
		</table>
		<br>
		<div class="text-c">
			<a
				href="toOverdueWarn.action?beforeDate=${condiBean.beforeDate}&afterDate=${condiBean.afterDate}&pageNum=${condiBean.pageNum-1}&checkId=${condiBean.checkId}">
				<button type="button" class="btn btn-success">上一页</button>
			</a> ${condiBean.pageNum}/${count} <a
				href="toOverdueWarn.action?beforeDate=${condiBean.beforeDate}&afterDate=${condiBean.afterDate}&pageNum=${condiBean.pageNum+1}&checkId=${condiBean.checkId}"><button
					type="button" class="btn btn-success">下一页</button></a>
					<input type="text" style="width:30px" class="input-text"  id="page" name="page" >
			<a onclick="toPageNum('${condiBean.beforeDate}','${condiBean.afterDate}','${condiBean.checkId}','${count}')"><button type="button" class="btn btn-secondary-outline radius" >跳转</button></a>
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
		src="../lib/jquery.validation/1.14.0/jquery.validate.js"></script>

	<script type="text/javascript"
		src="../lib/jquery.validation/1.14.0/messages_zh.js"></script>
<script type="text/javascript">
		function toPageNum(beforeDate,afterDate,checkId,count){
			var pageNum=$("#page").val();
			if (pageNum==""||pageNum==0||pageNum>count){
			return;				
			}else {
				
			location.href="toOverdueWarn.action?beforeDate="+beforeDate+"&afterDate="+afterDate+"&pageNum="+pageNum+"&checkId="+checkId;	
			}
		}
	</script>
</body>
</html>
