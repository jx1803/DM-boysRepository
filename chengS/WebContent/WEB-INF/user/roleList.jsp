<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>
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
<title>角色管理</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 管理员管理 <span class="c-gray en">&gt;</span> 角色管理 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
<form id="form1" name="form1" action="<%=path %>user/roleList.action" method="post" >
	<div class="text-c"> 角色名称：
		<input type="text" class="input-text" style="width:250px" placeholder="输入角色名" id="" name="adminName" value="${blurred.adminName }">
		<button type="submit" class="btn btn-success radius" id="" name=""><i class="Hui-iconfont">&#xe665;</i> 搜索</button>
	</div>
	</form>
	<div class="cl pd-5 bg-1 bk-gray"> <span class="l"> 

	<a class="btn btn-primary radius" href="javascript:;" onclick="admin_role_add('添加角色','<%=path%>user/roleListAdd.action','800')"><i class="Hui-iconfont">&#xe600;</i> 添加角色</a> </span> <span class="r">共有数据：<strong>${pageNum }</strong> 条</span> </div>
	<table class="table table-border table-bordered table-hover table-bg">
		<thead>
			<tr>
				<th scope="col" colspan="6">角色管理</th>
			</tr>
			<tr class="text-c">
				<th width="25"><input type="checkbox" value="" name=""></th>
				
				<th width="200">角色名</th>
				<th>用户列表</th>
				<th width="300">描述</th>
				<th width="70">操作</th>
			</tr>
		</thead>
		<tbody>
	
			<c:forEach items="${rolelist }" var="rlist">
				<tr>
					<td><input type="checkbox" value="" name=""></td>
					<%-- <td>${rlist.roleId }</td> --%>
					<td>${rlist.rolebean.roleName }</td>
					<td>${rlist.name }</td>
					<td>${rlist.rolebean.rolebewrite }</td>
					<td class="f-14">
					<a title="编辑" href="javascript:;" onclick="admin_role_edit('角色编辑','<%=path %>user/roleListUpdate.action?roleId='+${rlist.roleId },'1')" style="text-decoration:none"><i class="Hui-iconfont">&#xe6df;</i></a> 
					<a title="删除" href="javascript:;" onclick="admin_role_del(this,'${rlist.roleId }')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6e2;</i></a></td>
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
</div>
<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="../lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="../lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="../static/h-ui/js/H-ui.min.js"></script> 
<script type="text/javascript" src="../static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="../lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
<script type="text/javascript">

//跳转
function jumpPage(total){
			var page = $("#page").val();	//输入框的值
			var str = "";
			str = "roleList.action?page="+page;
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
		str1 = "roleList.action?page="+p;
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
		str2 = "roleList.action?page="+p;
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
	layer.confirm('角色删除须谨慎，确认要删除吗？',function(index){
		location.href="roleDel.action?roleid="+id;
		/* $.ajax({
			type: 'POST',
			url: 'roleDel.action?roleid='+id,
			dataType: 'text',
			success: function(data){
				$(obj).parents("tr").remove();
				
			},
			error:function(data) {
				console.log(data.msg);
				layer.msg('已删除!',{icon:1,time:1000});
				layer.close(index);
			},
		});	 */	
	});
}
</script>
</body>
</html>