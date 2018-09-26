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
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 药品列表 <span class="c-gray en">&gt;</span> 管理员列表 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
<form action="sellDrugLayer.action" method="post" id="select">
	<div class="text-c"> 
		药品拼音码：<input type="text" class="input-text" style="width:80px" placeholder="输入拼音码" id="" name="pinyinCode" >
		&nbsp;&nbsp;药品名：<input type="text" class="input-text" style="width:120px" placeholder="输入药品名" id="" name="drugName"><br>
	
		
		<button type="submit" class="btn btn-success" id="" name=""><i class="Hui-iconfont">&#xe665;</i> 搜索</button>
	</form>
	</div>
	<table class="table table-border table-bordered table-bg">
		<thead>
			<tr>
				<th scope="col" colspan="11">药房药品信息</th>
			</tr>
			<tr class="text-c">
				<th width="100">药品名称</th>
				<th width="100">药品编号</th>
				<th width="100">规格</th>
				<th width="100">单位</th>
				<th width="90">剩余数量</th>
				<th width="90">药品厂家</th>
				<th width="90">生产日期</th>
				<th width="90">产品批号</th>
				<th width="90">零售价（元）</th>
				<th width="90">选择</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${drugList }" var="drug">
			<tr class="text-c">
				<td>${drug.drugName }</td>
				<td>${drug.drugId }</td>
				<td>${drug.specific }</td>
				<td>${drug.unit }</td>
				<td>${drug.batchDetailBean.handleNum }</td>
				<td>${drug.drugmanu }</td>
				<td>${drug.batchDetailBean.proDate }</td>
				<td>${drug.batchDetailBean.manuBatch }</td>
				<td>${drug.retailPrice }</td>
				<td><button class="btn btn-primary" onclick="addDrug('${drug.drugId }',
				'${drug.drugName }','${drug.drugmanu }',
				'${drug.retailPrice }','${drug.specific }','${drug.unit }',
				'${drug.batchDetailBean.handleNum }','${drug.batchDetailBean.manuBatch }','${drug.batchDetailBean.batchDetailId }')">添加</button></td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
</div>

<script type="text/javascript" src="../lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="../lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="../static/h-ui/js/H-ui.min.js"></script> 
<script type="text/javascript" src="../static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->

<script type="text/javascript" src="../lib/My97DatePicker/4.8/WdatePicker.js"></script> 
<script type="text/javascript" src="../lib/datatables/1.10.0/jquery.dataTables.min.js"></script> 
<script type="text/javascript" src="../lib/laypage/1.2/laypage.js"></script>

<script type="text/javascript">
	function addDrug(drugId,drugName,drugmanu,retailPrice,specific,unit,drugNum,manuBatch,putBatch){
		var str = specific.split('-');
		/* window.opener.setValue(drugName,drugmanu,putBatch,manuBatch,purPrice); */
		parent.$("#manuBatch").val(manuBatch);
		parent.$("#drugName").val(drugName);
		parent.$("#drugId").val(drugId);
		parent.$("#drugManu").val(drugmanu);
		parent.$("#retailPrice").val(retailPrice);
		parent.$("#specific").val(specific);
		parent.$("#unit").val(unit);
		parent.$("#drugNum").val(drugNum);
		parent.$("#putBatch").val(putBatch);
		parent.$("#sunit").text("("+unit+")");
		parent.$("#sspecific").text("("+str[1]+")");
		var index=parent.layer.getFrameIndex(window.name);
		parent.layer.close(index);
	}
</script>
</body>
</html>