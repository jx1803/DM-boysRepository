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
<title>药品价格管理</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 药品报损列表 <span class="c-gray en">&gt;</span> 管理员列表 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
<form action="selectAdjustPrice.action" method="post" id="select">
	<div class="text-c"> 
	<div class="text-c"> 申请日期范围：
		<input type="text" onfocus="WdatePicker({ maxDate:'#F{$dp.$D(\'datemax\')||\'%y-%M-%d\'}' })" id="datemin" class="input-text Wdate" name="afterDate" style="width:120px;">
		-
		<input type="text" onfocus="WdatePicker({ minDate:'#F{$dp.$D(\'datemin\')}',maxDate:'%y-%M-%d' })" id="datemax" class="input-text Wdate" name="beforeDate" style="width:120px;">
		<br>
		药品名称：<input type="text" class="input-text" style="width:250px" placeholder="请输入药品名称" id="" name="drugName">
		拼音码：<input type="text" class="input-text" style="width:250px" placeholder="请输入拼音码" id="" name="pinyinCode">
		药品编码：<input type="text" class="input-text" style="width:250px" placeholder="请输入药品编码" id="" name="drugId">
		<button type="submit" class="btn btn-success" id="" name=""><i class="Hui-iconfont">&#xe665;</i> 搜索</button>
	</div>
	</form>
	<div class="cl pd-5 bg-1 bk-gray mt-20">  <span class="r">共有数据：<strong>${count }</strong> 条</span> </div>
	<table class="table table-border table-bordered table-bg">
		<thead>
			<tr>
				<th scope="col" colspan="11">调价历史查询</th>
			</tr>
			<tr class="text-c">
				<th width="100">药品编号</th>
				<th width="100">药品名称</th>
				<th width="90">单位</th>
				<th width="150">规格</th>
				<th width="90">改前零售价（元）</th>
				<th width="90">改后零售价（元）</th>
				<th width="150">经手人</th>
				<th width="90">调价时间</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${adjustList }" var="adjust">
			<tr class="text-c">
				<td>${adjust.drugId }</td>
				<td>${adjust.stoDrugBean.drugName }</td>
				<td>${adjust.stoDrugBean.unit }</td>
				<td>${adjust.stoDrugBean.specific }</td>
				<td>${adjust.beforeAdjust }</td>
				<td>${adjust.afterAdjust}</td>
				<td>${adjust.adminBean.adminName }</td>
				<td>${adjust.adjustDate}</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	
	
	<div style="float: right; margain-top: 20px;">
			<button  class="btn btn-secondary-outline radius" id="pre"
				name="" onclick="prePage('${pageNum}')">上一页</button>
			<button  class="btn btn-primary size-S radius" id=""
				name="">1</button>
			<button  class="btn btn-secondary-outline radius" id="next"
				name="" onclick="nextPage('${pageNum}','${pageTotal }')">下一页</button>
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
/*上一页  */
function prePage(pageNum) {
	var str1 = "";
	if (pageNum > 1) {
		$("#pre").disabled=false;
		pageNum -= 1;
		str1 = "selectAdjustPrice.action?pageNum="
				+ pageNum;
	} else {
		return;
	}
	$("#select").attr("action", str1);
	$("#select").submit();
}
/*下一页  */
function nextPage(pageNum, total) {
	var str2 = "";
	if (pageNum < total) {
		pageNum = Number(pageNum) + 1;
		str2 = "selectAdjustPrice.action?pageNum="
				+ pageNum;
	} else {
		return;
	}
	$("#select").attr("action", str2);
	$("#select").submit();
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

</script>
</body>
</html>