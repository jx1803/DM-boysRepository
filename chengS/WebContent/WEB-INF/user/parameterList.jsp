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
<link rel="stylesheet" type="text/css" href="../static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="../static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="../lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css" href="../static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="../static/h-ui.admin/css/style.css" />
<title>菜单管理</title>
</head>
<body>

<!-- //添加一级参数配置 -->
<div class="modal fade" id="addMenuLevel1" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<form action="<%=path %>user/addLevel1Parame.action" method="post">
		<div class="modal-dialog" style="width: 450px">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">添加一级参数</h4>
				</div>
				<div class="modal-body">
					<div class="pd-20">
						<div class="Huiform">
							
								<table class="table table-bg">
									<tbody>
										<tr>
											<th width="100" class="text-r"><span class="c-red">*</span>
												参数名：</th>
											<td><input type="text" style="width: 200px"
												class="input-text" value="" placeholder="" id="user-name"
												name="adminName" datatype="*2-16" nullmsg="不能为空">
												
											</td>
										</tr>
									</tbody>
								</table>
							
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭
					</button>
					<button type="submit" class="btn btn-primary">提交更改</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		</form>
	</div>
	
	<!--  //添加一级参数配置 -->
	<div class="modal fade" id="addMenuLevel2" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<form action="<%=path %>user/addLevel2Parame.action" method="post">
		<div class="modal-dialog" style="width: 450px">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">添加二级参数</h4>
				</div>
				<div class="modal-body">
					<div class="pd-20">
						<div class="Huiform">
							
								<table class="table table-bg">
									<tbody>
										<tr>
											<td width="100" class="text-r"><span class="c-red">*</span>
												一级参数名：
											<td>
											<span class="select-box" id="addShowDf"> <select
														class="select" size="1" name="roleid" id="roleid">
													 <c:forEach items="${rlist}" var="List" >
															<option value="${List.paramId}">${List.param}</option>
														</c:forEach> 
													</select>
												</span>
											<!-- <input type="text" style="width: 200px"
												class="input-text" value="" placeholder="" id="user-name"
												name="adminName" datatype="*2-16" nullmsg="不能为空"> -->
												
											</td>
											</tr>
											<tr><td class="text-r">新增二级参数：</td>
											<td><input type="text" style="width: 260px"
													class="input-text" value="" placeholder="" id="entrytime"
													name="entrytime"></td>
										</tr>
									</tbody>
								</table>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭
					</button>
					<button type="submit" class="btn btn-primary">提交更改</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		</form>
	</div>
	
	
	
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 系统管理 <span class="c-gray en">&gt;</span>参数配置 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
<form id="form1" name="form1" action="<%=path %>user/parameterList.action" method="post" >
	<div class="text-c"> 菜单搜索：
<input type="text" class="input-text" style="width:250px" placeholder="输入一级参数名" id="" name="entrytime" value="${blurred.entrytime }">
		<input type="text" class="input-text" style="width:250px" placeholder="输入二级参数名" id="" name="adminName" value="${blurred.adminName }">
		<button type="submit" class="btn btn-success radius" id="" name=""><i class="Hui-iconfont">&#xe665;</i> 搜索</button>
	</div>
	</form>
	<div class="cl pd-5 bg-1 bk-gray"> <span class="l"> 

	<a class="btn btn-primary radius" href="javascript:;" onclick="addMenu1()"><i class="Hui-iconfont">&#xe600;</i> 添加一级参数</a>
	<a class="btn btn-primary radius" href="javascript:;" onclick="addMenu2()"><i class="Hui-iconfont">&#xe600;</i> 添加二级参数</a> </span> 
	<span class="r">共有数据：<strong>${pageNum}</strong> 条</span> </div>
	<table class="table table-border table-bordered table-hover table-bg">
		<thead>
			
			<tr class="text-c">
				<th width="150">id</th>
				<th width="300">一级菜单</th>
				<th width="150">二级菜单id</th>
				<th>二级菜单</th>
				<th width="70">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${ulist }" var="list">
				<tr>
					<td>${list.pid }</td>
					<td>${list.fname }</td>
					<td>${list.paramId }</td>
					<td>${list.param }</td>
					<td>
					<a title="编辑"      href="javascript:;" onclick="admin_role_edit('角色编辑','<%=path%>user/paramUpdate.action?paramId=${list.paramId}&fname=${list.fname }&param=${list.param }&pid=${list.pid }')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6df;</i></a> 
					<a title="删除一级参数" href="javascript:;" onclick="admin_onepara_del('删除','${list.pid }')"  class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6e2;</i></a>
					<a title="删除二级参数" href="javascript:;" onclick="admin_twopara_del('删除','${list.paramId }')"  class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6e2;</i></a>
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
	
		<%-- <div class="dataTables_info" id="DataTables_Table_0_info" role="status" aria-live="polite">当前页 ${blurred.page}，共 ${pageTotal}页</div>
	<div class="dataTables_paginate paging_simple_numbers" id="DataTables_Table_0_paginate">
	
	<button  type="button" onclick="upPage('${blurred.page}')" class="btn btn-secondary-outline radius">上一页</button>

	<button id="DataTables_Table_0_next" class="btn btn-secondary-outline radius" onclick="nextPage('${blurred.page}','${pageTotal}')">下一页</button>
	</div> --%>
</div>
<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="../lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="../lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="../static/h-ui/js/H-ui.min.js"></script> 
<script type="text/javascript" src="../static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="../lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
<script type="text/javascript">

//弹出模态框
function addMenu1(){
	$('#addMenuLevel1').modal('show');
}

function addMenu2(){
	$('#addMenuLevel2').modal('show');
}


//跳转
function jumpPage(total){
			var page = $("#page").val();	//输入框的值
			var str = "";
			str = "parameterList.action?page="+page;
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
		str1 = "parameterList.action?page="+p;
		$("#form1").attr("action",str1);
		$("#form1").submit();
	}
	return ;
}

/* 下一页 */
function nextPage(p,total){
	
	var str2 = "";
	if(p - 0<total - 0){
		p = Number(p) + 1;
		str2 = "parameterList.action?page="+p;
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
function admin_onepara_del(obj,pid){
	layer.confirm('一级参数菜单删除须谨慎，确认要删除吗？',function(index){
		$.ajax({
			type: 'POST',
			url: 'oneparaDel.action',
			dataType: 'text',
			data:'paid='+pid,
			success: function(data){
				$(obj).parents("tr").remove();
				layer.msg('已删除!',{icon:1,time:1000});
				layer.close(index);
				window.reload();
			},
			error:function(data) {
				alert("失败");
				console.log(data.msg);
			},
		});		
	});
}
/*管理员-角色-删除*/
function admin_twopara_del(obj,id){
	layer.confirm('二级参数菜单删除须谨慎，确认要删除吗？',function(index){
		$.ajax({
			type: 'POST',
			url: 'twoparaDel.action',
			dataType: 'text',
			data:'paramId='+id,
			success: function(data){
				$(obj).parents("tr").remove();
				layer.msg('已删除!',{icon:1,time:1000});
				layer.close(index);
				window.reload();
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