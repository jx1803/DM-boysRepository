<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getScheme() +"://"+request.getServerName()
	+":"+request.getServerPort()+ request.getContextPath()+"/";

%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<!--[if lt IE 9]>
<script type="text/javascript" src="<%=path%>lib/html5shiv.js"></script>
<script type="text/javascript" src="<%=path%>lib/respond.min.js"></script>
<![endif]-->
<link rel="stylesheet" type="text/css" href="<%=path%>static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="<%=path%>static/h-ui.admin/css/style.css" />
<!--[if IE 6]>
<script type="text/javascript" src="<%=path%>lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>药品类型</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页
	<span class="c-gray en">&gt;</span>
	药品管理
	<span class="c-gray en">&gt;</span>
	药品类型
	<a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a>
</nav>
<div class="page-container">
	<div class="text-c"> 
		类型名称:
		<input type="text" class="input-text" id="" name="" style="width:150px">
		<input type="hidden" id="" name="">
		<button type="submit" class="btn btn-primary radius" id="" name=""> 搜索</button>
		<button type="submit" class="btn btn-primary radius" id="" name="" 
		 onclick="system_data_edit('添加类型','addDrugTypeInfo.action','0001','400','250')">
		 <i class="Hui-iconfont">&#xe600;</i> 添加
		</button>
	</div>
	
	<div class="mt-20">
		<table class="table table-border table-bordered table-bg table-hover table-sort">
			<thead>
				<tr class="text-c">
					
					<th>类型ID</th>
					<th>类型名称</th>
					<th>上级类型名称</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<tr class="text-c">
					
					<td>0001</td>
					<td>城市</td>
					<td>city</td>
					<td class="f-14"><a style="text-decoration:none" onclick="system_data_edit('修改类型','updDrugTypeInfo.action','0001','400','250')" href="javascript:;" title="编辑"><i class="Hui-iconfont">&#xe6df;</i></a>
						<a title="删除" href="javascript:;" onclick="system_data_del(this,'10001')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6e2;</i></a></td>
				</tr>
			</tbody>
		</table>
	</div>
	<br/>
	<div style="float: right;margain-top:20px;">
		<button type="submit" class="btn btn-secondary-outline radius" id="" name=""> 上一页</button>
		<button type="submit" class="btn btn-primary size-S radius"  id="" name="">1</button>
		<button type="submit" class="btn btn-secondary-outline radius" id="" name=""> 下一页</button>
	</div>
</div>
<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="<%=path%>lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="<%=path%>lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="<%=path%>static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="<%=path%>static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="<%=path%>lib/My97DatePicker/4.8/WdatePicker.js"></script>
<script type="text/javascript" src="<%=path%>lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="<%=path%>lib/laypage/1.2/laypage.js"></script>
<script type="text/javascript">
/* $('.table-sort').dataTable({
	
	"bStateSave": true,//状态保存
	"aoColumnDefs": [
	 {"bVisible": false, "aTargets": [ 3 ]} //控制列的隐藏显示
	  {"orderable":false,"aTargets":[0,5]}// 制定列不参与排序
	]
}); */
/*数据字典-编辑*/
function system_data_edit(title,url,id,w,h){
  layer_show(title,url,w,h);
}
/*数据字典-删除*/
function system_data_del(obj,id){
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
</script>
</body>
</html>