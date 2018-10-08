<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link rel="stylesheet" type="text/css" href="../static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="../static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="../lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css" href="../static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="../static/h-ui.admin/css/style.css" />

<title>Insert title here</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 部门管理 <span class="c-gray en">&gt;</span> 角色管理 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
<form id="form1" name="form1" action="<%=path %>user/logSelect.action" method="post" >
	<div class="text-c"> 部门搜索：
		<input type="text" class="input-text" style="width:250px" placeholder="操作人" id="" name="adminName" value="${blurred.adminName }">
		<input type="text" class="input-text" style="width:250px" placeholder="操作时间：20180101" id="" name="entrytime" value="${blurred.entrytime }">
		<input type="text" class="input-text" style="width:250px" placeholder="操作事项" id="" name="quitDate" value="${blurred.quitDate }">
		<button type="submit" class="btn btn-success radius" id="" name=""><i class="Hui-iconfont">&#xe665;</i> 搜用户</button>
	</div>
</form>
	<div class="cl pd-5 bg-1 bk-gray"> <span class="l"> 
	<a class="btn btn-primary radius" href="javascript:;" onclick="addDept('添加部门','<%=path%>user/role_list_add.action','800')"><i class="Hui-iconfont">&#xe600;</i> 添加部门</a> </span> <span class="r">共有数据：<strong>${pageNum}</strong> 条</span> </div>
	<table class="table table-border table-bordered table-hover table-bg">
		<thead>
			<tr>
				<th scope="col" colspan="6">日志查看</th>
			</tr>
			<tr class="text-c">
				<th width="25"><input type="checkbox" value="" name=""></th>
				<th width="200">操作人</th>
				<th width="200">操作时间</th>
				<th width="70">操作事项</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${log }" var="rlist">
				<tr>
					<td><input type="checkbox" value="" name=""></td>
					<td>${rlist.adminbean.adminName }</td>
					<td>${rlist.operateDate }</td>
					<td>${rlist.operateItem }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
		<div class="dataTables_info" id="DataTables_Table_0_info" role="status" aria-live="polite">当前页 ${blurred.page}，共 ${pageTotol}页</div>
	<div class="dataTables_paginate paging_simple_numbers" id="DataTables_Table_0_paginate">
	<button  type="button" onclick="upPage('${blurred.page}')" class="btn btn-secondary-outline radius" >上一页</button>

	<button id="DataTables_Table_0_next" class="btn btn-secondary-outline radius" onclick="nextPage('${blurred.page}','${pageTotol}')">下一页</button>	</div>
</div>
<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="../lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="../lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="../static/h-ui/js/H-ui.min.js"></script> 
<script type="text/javascript" src="../static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="../lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
<script type="text/javascript">

//添加部门addDept
function addDept(){
	$('#addNewDept').modal('show');
}
/* 上一页 */
function upPage(p){
	var str1 = "";
	if(p>1){
		p-=1;
		str1 = "logSelect.action?page="+p;
		$("#form1").attr("action",str1);
		$("#form1").submit();
	}
	return ;
}

/* 下一页 */
function nextPage(p,total){
	var str2 = "";
	if(p<total){
		p = Number(p) + 1;
		str2 = "logSelect.action?page="+p;
		$("#form1").attr("action",str2);
		$("#form1").submit();
	}
}

/*管理员-角色-添加*/
function admin_role_add(title,url,w,h){
	layer_show(title,url,w,h);
}
/*管理员-角色-编辑*/
function admin_role_edit(title,url,id,w,h){
	layer_show(title,url,w,h);
}
/*管理员-角色-删除*/
function admin_role_del(obj,id){
	layer.confirm('部门删除须谨慎，确认要删除吗？',function(index){
		$.ajax({
			type: 'POST',
			url: 'deptlistDel.action',
			data:'deptid='+id,
			dataType: 'text',
			success: function(data){
				$(obj).parents("tr").remove();
				layer.msg('已删除!',{icon:1,time:1000});
			},
			error:function(data) {
				console.log(data.msg);
			},
		});		
	});
}
</script>
</body>
</html>