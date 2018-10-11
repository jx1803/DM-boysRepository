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
<!--[if lt IE 9]>
<script type="text/javascript" src="lib/html5shiv.js"></script>
<script type="text/javascript" src="lib/respond.min.js"></script>
<![endif]-->
<link rel="stylesheet" type="text/css" href="../static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="../static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="../lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css" href="../static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="../static/h-ui.admin/css/style.css" />
<!--[if IE 6]>
<script type="text/javascript" src="lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>用户管理</title>
</head>
<body>

<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 用户中心 <span class="c-gray en">&gt;</span> 用户管理 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
<form id="form1" name="form1" action="<%=path %>user/memberList.action" method="post" >
	<div class="text-c"> 就职日期范围：
		<input type="text" value="${blurred.entrytime}" name="entrytime" onfocus="WdatePicker({ maxDate:'#F{$dp.$D(\'datemax\')||\'%y-%M-%d\'}' })" id="datemin" class="input-text Wdate" style="width:120px;" />
		-
		<input type="text" value="${blurred.quitDate}" name="quitDate" onfocus="WdatePicker({ minDate:'#F{$dp.$D(\'datemin\')}',maxDate:'%y-%M-%d' })" id="datemax" class="input-text Wdate" style="width:120px;">
		<input type="text" class="input-text" style="width:250px" placeholder="输入会员名" id="" name="adminName" value="${blurred.adminName }">
		<button type="submit" class="btn btn-success radius" id="" name=""><i class="Hui-iconfont">&#xe665;</i> 搜用户</button>
	</div>
	</form>
	<div class="cl pd-5 bg-1 bk-gray mt-20"> <span class="l">
	
	<a href="javascript:;" onclick="member_add('添加用户','<%=path%>user/memberListAdd.action','','510')" class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i> 添加用户</a></span> <span class="r">共有数据：<strong>${pagenum}</strong> 条</span> </div>
	<div class="mt-20">
	<table class="table table-border table-bordered table-hover table-bg table-sort">
		<thead>
			<tr class="text-c">
				<th width="80">账号</th>
				<th width="80">用户名</th>
				<th width="40">性别</th>
				<th width="80">职位</th>
				<th width="20">部门</th>
				<th width="100">手机</th>
				<th width="150">邮箱</th>
				<th width="120">地址</th>
				<th width="130">入职时间</th>
				<th width="130">离职时间</th>
				<th width="70">状态</th>
				<th width="100">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${ulist}" var="list">
				<tr>
				<td>${list.adminAccount}</td>
				<td>${list.adminName}</td>
				<td>${list.sex }</td>
				<td>${list.rolebean.roleName} </td>
				<td>${list.deptbean.dept}</td>
				<td>${list.mobilePhone}</td>
				<td>${list.email}</td>
				<td>${list.address}</td>
				<td>${list.entryDate}</td>
				<td>${list.quitDate}</td>
				<c:if test="${list.parambean.param=='已启用' }">
				<td class="td-status"><span class="label label-success radius">${list.parambean.param }</span></td>
				</c:if>
				<c:if test="${list.parambean.param=='禁用' }">
				<td class="td-status"><span class="label label-defaunt radius">${list.parambean.param }</span></td>
				</c:if>
				<td class="td-manage">
				<c:if test="${list.parambean.param=='已启用' }">
					<a style="text-decoration:none" onClick="member_stop(this,'${list.adminId}','${list.paramId }')" href="javascript:;" title="停用"><i class="Hui-iconfont">&#xe631;</i></a> </c:if>
				<c:if test="${list.parambean.param=='禁用' }">
					<a style="text-decoration:none" onClick="member_start(this,'${list.adminId}','${list.paramId }')" href="javascript:;" title="启用"><i class="Hui-iconfont">&#xe6e1;</i></a>
				</c:if>	
					<a title="编辑" href="javascript:;" onclick="member_edit('编辑','<%=path %>user/memberListUpdate.action?adminId=${list.adminId }&adminName=${list.adminName }','4','','510')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6df;</i></a> 
					<a style="text-decoration:none" class="ml-5" onClick="change_password('修改密码','${list.adminId}')" href="javascript:;" title="修改密码"><i class="Hui-iconfont">&#xe63f;</i></a> 
					<a title="删除" href="javascript:;" onClick="member_del('修改密码','${list.adminId}')"  class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6e2;</i></a>
				<%-- 	href="<%=path %>user/delUser.action?adminId=${list.adminId}" --%>
				</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<div style="float: right; margain-top: 20px;">
			<button type="submit" class="btn btn-secondary-outline radius"  onclick="upPage('${blurred.page}')">上一页</button>
			<label class="label label-default radius"><font size="2">当前页${blurred.page}/共${pageTotal}页</font></label>
			<button type="submit" class="btn btn-secondary-outline radius" onclick="nextPage('${blurred.page}','${pageTotal }')">下一页</button>
			<input type="text" style="width:30px" class="input-text"  id="page" name="page" >
			<button type="button" class="btn btn-secondary-outline radius"  onclick="return jumpPage('${pageTotal}')">跳转</button>
	</div>
	<%-- 
	<div class="dataTables_info" id="DataTables_Table_0_info" role="status" aria-live="polite">当前页 ${blurred.page}，共 ${pageTotol}页</div>
	<div class="dataTables_paginate paging_simple_numbers" id="DataTables_Table_0_paginate">
	
	
	<button  type="button" onclick="upPage('${blurred.page}')" class="btn btn-secondary-outline radius" >上一页</button>

	<button id="DataTables_Table_0_next" class="btn btn-secondary-outline radius" onclick="nextPage('${blurred.page}','${pageTotol}')">下一页</button>
	</div> --%>

	</div>
</div>
<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="../lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="../lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="../static/h-ui/js/H-ui.min.js"></script> 
<script type="text/javascript" src="../static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="../lib/My97DatePicker/4.8/WdatePicker.js"></script> 
<script type="text/javascript" src="../lib/datatables/1.10.0/jquery.dataTables.min.js"></script> 
<script type="text/javascript" src="../lib/laypage/1.2/laypage.js"></script>
<script type="text/javascript">
 
 //跳转
 function jumpPage(total){
			var page = $("#page").val();	//输入框的值
			var str = "";
			str = "memberList.action?page="+page;
			$("#form1").attr("action",str);
			
			if(page=='' || page>total || page==0){
				$("#page").val('');
			}else{
				//把form表单提交。
				$("#form1").submit();
			}
			
		}

/* 上一页 */
function upPage(p){
	var str1 = "";
	if(p>1){
		p-=1;
		str1 = "memberList.action?page="+p;
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
		str2 = "memberList.action?page="+p;
		$("#form1").attr("action",str2);
		$("#form1").submit();
	}
}

/*用户-添加*/
function member_add(title,url,w,h){
	layer_show(title,url,w,h);
}
/*用户-查看*/
function member_show(title,url,id,w,h){
	layer_show(title,url,w,h);
}
/*用户-停用*/
function member_stop(obj,adminId,paramId){
	layer.confirm('确认要停用吗？',function(index){
		$.ajax({
			type: 'POST',
			url: 'ChangeStateUser.action',
			data:{"adminId":adminId,"paramId":paramId},
			dataType: 'json',
			success: function(data){
			
				$(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" onClick="member_start(this,' + adminId + ',' + paramId + ')" href="javascript:;" title="启用"><i class="Hui-iconfont">&#xe6e1;</i></a>');
				$(obj).parents("tr").find(".td-status").html('<span class="label label-defaunt radius">禁用</span>');
				$(obj).remove();
				layer.msg('已停用!',{icon: 5,time:1000});
				layer.close(index);
			},
			error:function(data) {
			console.log(data.msg);
			},
		});		
	});
}

/*用户-启用*/
function member_start(obj,adminId,paramId){
	layer.confirm('确认要启用吗？',function(index){
		$.ajax({
			type: 'POST',
			url: 'ChangeStateUser.action',
			data:{"adminId":adminId,"paramId":paramId},
			dataType: 'json',
			success: function(data){
				$(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" onClick="member_stop(this,' + adminId + ',' + paramId + ')" href="javascript:;" title="停用"><i class="Hui-iconfont">&#xe631;</i></a>');
				$(obj).parents("tr").find(".td-status").html('<span class="label label-success radius">已启用</span>');
				$(obj).remove();
				layer.msg('已启用!',{icon: 6,time:1000});
				layer.close(index);
			},
			error:function(data) {
				alert("不成功");
				layer.close(index);
				console.log(data.msg);
			},
		});
	});
}
/*用户-编辑*/
function member_edit(title,url,id,w,h){
	layer_show(title,url,w,h);
}
/*密码-修改*/
function change_password(title,adminId){
	layer.confirm('密码将重置为：123456',function(index){
		$.ajax({
			type: 'POST',
			url: 'ResetPassword.action',
			data:{"adminId":adminId},
			dataType: 'json',
			success: function(data){
				layer.msg('已重置！',{icon: 6,time:1000});
				layer.close(index);
			},
	});
});
}
/*用户-删除*/
function member_del(obj,adminId,paramId){
	layer.confirm('确认要删除吗？',function(index){
		
		$.ajax({
			type: 'POST',
			url: 'delUser.action',
			dataType: 'json',
			data:{"adminId":adminId,"paramId":paramId},
			success: function(data){
				
			},
			error:function(data) {
				layer.msg('已删除！',{icon: 6,time:1000});
				layer.close(index);
				location.reload();
				
			},
		});		
	});
}
</script> 
</body>
</html>