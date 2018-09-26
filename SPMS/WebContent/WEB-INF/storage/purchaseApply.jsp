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
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<!--[if lt IE 9]>
<script type="text/javascript" src="lib/html5shiv.js"></script>
<script type="text/javascript" src="lib/respond.min.js"></script>
<![endif]-->
<link rel="stylesheet" type="text/css" href="<%=path %>static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="<%=path %>static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="<%=path %>lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css" href="<%=path %>static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="<%=path %>static/h-ui.admin/css/style.css" />
<!--[if IE 6]>
<script type="text/javascript" src="lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>药品采购申请</title>
<link rel="stylesheet" href="<%=path %>lib/zTree/v3/css/zTreeStyle/zTreeStyle.css" type="text/css">
</head>
<body class="pos-r">

<div style="margin-left:0px;">
	<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 药库工作 <span class="c-gray en">&gt;</span>药品采购<a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
	<div class="page-container">
		<div class="text-c"> 
		药品编码：<input type="text" name="drugId" id="drugId" placeholder="药品编码" style="width:150px" class="input-text">&nbsp;<button name="F1" id="F1" class="btn btn-primary btn-sm" data-toggle="modal" data-target="#myModal">F1</button>
		&emsp;药品名称：<input type="text" name="drugName" id="drugName" placeholder="药品名称" style="width:150px" class="input-text" disabled/>
		&emsp;单位：<input type="text" name="unit" id="unit" placeholder="药品单位" style="width:150px" class="input-text" disabled />
		&emsp;规格：<input type="text" name="specific" id="specific" placeholder="药品规格" style="width:150px" class="input-text" disabled />
		</br>申请人：<input type="text" name="adminName" id="adminName" placeholder="请输入申请人" style="width:150px;margin-top:5px" class="input-text" />
		&emsp;数量：<input type="text" name="applyNum" id="applyNum" placeholder="请输入申请数量" style="width:150px;margin-top:5px" class="input-text" />
		&emsp;申请理由：<input type="text" name="applyReason" id="applyReason" placeholder="请输入申请理由" style="width:250px;margin-top:5px" class="input-text" />
		<a class="btn btn-primary radius" onclick="product_add('添加产品','product-add.html')" href="javascript:;"><i class="Hui-iconfont">&#xe600;</i> 增加</a>
		</br>
		
		
		
		
		<!-- 日期范围：
			<input type="text" onfocus="WdatePicker({ maxDate:'#F{$dp.$D(\'logmax\')||\'%y-%M-%d\'}' })" id="logmin" class="input-text Wdate" style="width:120px;">
			-
			<input type="text" onfocus="WdatePicker({ minDate:'#F{$dp.$D(\'logmin\')}',maxDate:'%y-%M-%d' })" id="logmax" class="input-text Wdate" style="width:120px;">
			<input type="text" name="" id="" placeholder=" 产品名称" style="width:250px" class="input-text">
			<button name="" id="" class="btn btn-success" type="submit"><i class="Hui-iconfont">&#xe665;</i> 搜产品</button> -->
		</div>
		<div class="cl pd-5 bg-1 bk-gray mt-20"> <span class="l"><a href="javascript:;" onclick="datadel()" class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i> 批量删除</a> <a class="btn btn-primary radius" onclick="product_add('添加产品','product-add.html')" href="javascript:;"><i class="Hui-iconfont">&#xe600;</i> 添加产品</a></span> <span class="r">共有数据：<strong>54</strong> 条</span> </div>
		<form id="apply" action="">
		<div class="mt-20">
			<table class="table table-border table-bordered table-bg table-hover table-sort">
				<thead>
					<tr class="text-c">
						<th width="40">药品编码</th>
						<th width="60">药品名称</th>
						<th width="100">申请数量</th>
						<th width="100">申请时间</th>
						<th width="100">申请理由</th>
						<th width="60">申请人</th>
						<th width="100">操作</th>
					</tr>
				</thead>
				<tbody>
					<tr class="text-c va-m">
						<td>001</td>
						<td></td>
						<td class="text-l"></td>
						<td class="text-l"></td>
						<td></td>
						<td class="td-status"><span class="label label-success radius">已发布</span></td>
						<td class="td-manage"><a style="text-decoration:none" onClick="product_stop(this,'10001')" href="javascript:;" title="下架"><i class="Hui-iconfont">&#xe6de;</i></a> <a style="text-decoration:none" class="ml-5" onClick="product_edit('产品编辑','product-add.html','10001')" href="javascript:;" title="编辑"><i class="Hui-iconfont">&#xe6df;</i></a> <a style="text-decoration:none" class="ml-5" onClick="product_del(this,'10001')" href="javascript:;" title="删除"><i class="Hui-iconfont">&#xe6e2;</i></a></td>
					</tr>
				</tbody>
			</table>
		</div>
		</form>
	</div>
</div>

<!-- 模态框（Modal） -->
<div class="modal fade bs-example-modal-lg" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
					&times;
				</button>
				<h4 class="modal-title" id="myModalLabel">
					药品添加
				</h4>
			</div>
			<div class="modal-body">
			  拼音码：<input type="text" name="pinyinCode" id="pinyinCode" style="width:100px" class="input-text"/>
			  &emsp; 药品编码：<input type="text" name="drugId" id="drugId" style="width:100px" class="input-text"/>
			  &emsp; 药品名称：<input type="text" name="drugName" id="drugName" style="width:100px" class="input-text"/>
			  &emsp; <button name="searchDrug" id="searchDrug" class="btn btn-success" onclick="drugSearch()"><i class="Hui-iconfont">&#xe665;</i>查询药品</button>
			</div>
			<div class="mt-20">
			<table class="table table-border table-bordered table-bg table-hover table-sort">
				<thead>
					<tr class="text-c">
						<th width="100">药品编码</th>
						<th width="100">药品名称</th>
						<th width="80">规格</th>
						<th width="80">单位</th>
						<th width="100">剂型</th>
						<th width="100">生产厂商</th>
						<th width="100">药品产地</th>
					</tr>
				</thead>
				<tbody>
					<tr class="text-c va-m">
						<td>001</td>
						<td></td>
						<td class="text-l"></td>
						<td class="text-l"></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
				</tbody>
			</table>
		</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭
				</button>
				<button type="button" class="btn btn-primary">
					提交更改
				</button>
			</div>
		</div><!-- /.modal-content -->
	</div><!-- /.modal -->
</div>


<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="<%=path %>lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="<%=path %>lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="<%=path %>static/h-ui/js/H-ui.min.js"></script> 
<script type="text/javascript" src="<%=path %>static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="<%=path %>lib/zTree/v3/js/jquery.ztree.all-3.5.min.js"></script>
<script type="text/javascript" src="<%=path %>lib/My97DatePicker/4.8/WdatePicker.js"></script> 
<script type="text/javascript" src="<%=path %>lib/datatables/1.10.0/jquery.dataTables.min.js"></script> 
<script type="text/javascript" src="<%=path %>lib/laypage/1.2/laypage.js"></script>
<script type="text/javascript">
/* 通过AJAX获取药品列表 */


/* $("#searchDrug").click(function(){
	console.log($("#pinyinCode").val()); */
function drugSearch(){
		console.log(00000);
		$.ajax({
			contentType:"appliction/json;charset=utf-8",
		    url:"stoDrugSearch.action",
			data:{"pinyinCode":$("#pinyinCode").val(),"drugId":$("#drugId").val(),"drugName":$("#drugName").val()},
			type:"post",
			dataType:"json",
			success:function(data){		
			}
		});
	}
</script>
</body>
</html>