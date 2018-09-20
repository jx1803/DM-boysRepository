<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link rel="Bookmark" href="/favicon.ico" >
<link rel="Shortcut Icon" href="/favicon.ico" />
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
<title>报损申请</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 药品报损列表 <span class="c-gray en">&gt;</span> 管理员列表 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
	<div class="text-c"> 申请日期范围：
		<input type="text" onfocus="WdatePicker({ maxDate:'#F{$dp.$D(\'datemax\')||\'%y-%M-%d\'}' })" id="datemin" class="input-text Wdate" style="width:120px;">
		-
		<input type="text" onfocus="WdatePicker({ minDate:'#F{$dp.$D(\'datemin\')}',maxDate:'%y-%M-%d' })" id="datemax" class="input-text Wdate" style="width:120px;">
		<br>
		申请人：<input type="text" class="input-text" style="width:250px" placeholder="请输入申请人" id="" name="">
		审核状态：<select name="checkId" >
		<option value="6" >审核通过</option>
		<option value="8" >审核未通过</option>
		<option value="7" >未审核</option>
		 </select>
		<button type="submit" class="btn btn-success" id="" name=""><i class="Hui-iconfont">&#xe665;</i> 搜索</button>
	</div>
	<div class="cl pd-5 bg-1 bk-gray mt-20">  <span class="r">共有数据：<strong>${count }</strong> 条</span> </div>
	<table class="table table-border table-bordered table-bg">
		<thead>
			<tr>
				<th scope="col" colspan="11">报损列表</th>
			</tr>
			<tr class="text-c">
				<th width="25"><input type="checkbox" name="" value=""></th>
				<th width="100">报损药品名称</th>
				<th width="90">报损药品厂家</th>
				<th width="150">报损数量</th>
				<th width="90">报损药品厂家批号</th>
				<th width="90">报损药品金额(元)</th>
				<th width="150">报损申请人</th>
				<th width="90">报损原因</th>
				<th width="150">申请日期</th>
				<th width="130">审核日期</th>
				<th width="100">审核情况</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${drugApplyList }" var="breakApply">
			<tr class="text-c">
				<td><input type="checkbox" value="1" name=""></td>
				<td>${breakApply.stoDrugBean.drugName }</td>
				<td>${breakApply.bdBean.drugmanu }</td>
				<td>${breakApply.applyNum }</td>
				<td>${breakApply.manuBatch }</td>
				<td>${breakApply.applyNum*breakApply.bdBean.purPrice }</td>
				<td>${breakApply.adminBean.adminName }</td>
				<td>${breakApply.applyReason}</td>
				<td>${breakApply.applyDate}</td>
				<td>${breakApply.checkDate}</td>
				<td class="td-status"><span class="label label-success radius">${breakApply.checkName }</span></td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
<button type="button" class="btn btn-info" id=""
					onclick="showModal()">按钮</button>

<!-- 文档上传模态框 -->
	 <div class="modal fade" id="uploadModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title" id="">我要上传</h4>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<form action="${pageContext.request.contextPath}/front/upload.action" method="post" enctype="multipart/form-data">
					<div class="modal-body" >
					<input type="hidden" name="dom.userId" value="${userInfo.userId }">
					<table border="0" style="heigth:300px">
					<tr>
						<td width="170px" align="center">文档标题：</td>
						<td><input type="text" class="form-control" name="dom.domName"></td>
					</tr>
						<tr align="center">
						<td width="170px" >文档简介：</td>
						<td><textarea name="dom.domInfo" 
							cols="50" rows="5"></textarea></td>
					</tr>
					</tr>
						<tr>
						<td width="170px" align="center">选择上传文档：</td>
						<td>	<input type="file" name="myfile">
							</td>
					</tr>
						<tr>
						<td width="170px" align="center">设置下载积分：</td>
						<td>	<input type="text" name="dom.integral">
							</td>
					</tr>
				
					 </table>
					</div>

					<div class="modal-footer">
						<button type="submit" class="btn btn-primary" id="btupload">上传</button>
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				</form>
			</div>
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
	function showModal(){
		$("#uploadModal").modal("show");
	}

/*
	参数解释：
	title	标题
	url		请求的url
	id		需要操作的数据id
	w		弹出层宽度（缺省调默认值）
	h		弹出层高度（缺省调默认值）
*/
/*管理员-增加*/
function admin_add(title,url,w,h){
	layer_show(title,url,w,h);
}
/*管理员-删除*/
function admin_del(obj,id){
	layer.confirm('确认要删除吗？',function(index){
		$.ajax({
			type: 'POST',
			url: '',
			dataType: 'json',
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

/*管理员-编辑*/
function admin_edit(title,url,id,w,h){
	layer_show(title,url,w,h);
}
/*管理员-停用*/
function admin_stop(obj,id){
	layer.confirm('确认要停用吗？',function(index){
		//此处请求后台程序，下方是成功后的前台处理……
		
		$(obj).parents("tr").find(".td-manage").prepend('<a onClick="admin_start(this,id)" href="javascript:;" title="启用" style="text-decoration:none"><i class="Hui-iconfont">&#xe615;</i></a>');
		$(obj).parents("tr").find(".td-status").html('<span class="label label-default radius">已禁用</span>');
		$(obj).remove();
		layer.msg('已停用!',{icon: 5,time:1000});
	});
}

/*管理员-启用*/
function admin_start(obj,id){
	layer.confirm('确认要启用吗？',function(index){
		//此处请求后台程序，下方是成功后的前台处理……
		
		
		$(obj).parents("tr").find(".td-manage").prepend('<a onClick="admin_stop(this,id)" href="javascript:;" title="停用" style="text-decoration:none"><i class="Hui-iconfont">&#xe631;</i></a>');
		$(obj).parents("tr").find(".td-status").html('<span class="label label-success radius">已启用</span>');
		$(obj).remove();
		layer.msg('已启用!', {icon: 6,time:1000});
	});
}
</script>
</body>
</html>