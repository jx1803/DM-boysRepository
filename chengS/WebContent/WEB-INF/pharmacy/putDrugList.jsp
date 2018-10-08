<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 药品报损列表 <span class="c-gray en">&gt;</span> 管理员列表 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
<form action="selectPutDrug.action" method="post" id="select">
	<div class="text-c"> 药品入库日期范围：
		<input type="text" onfocus="WdatePicker({ maxDate:'#F{$dp.$D(\'datemax\')||\'%y-%M-%d\'}' })" id="datemin" class="input-text Wdate" name="afterDate" style="width:120px;">
		--
		<input type="text" onfocus="WdatePicker({ minDate:'#F{$dp.$D(\'datemin\')}',maxDate:'%y-%M-%d' })" id="datemax" class="input-text Wdate" name="beforeDate" style="width:120px;">
		<br>
		药品拼音码：<input type="text" class="input-text" style="width:80px" placeholder="输入拼音码" id="" name="pinyinCode" >
		&nbsp;&nbsp;药品名：<input type="text" class="input-text" style="width:120px" placeholder="输入药品名" id="" name="drugName"><br>
		药品厂家批号：<input type="text" class="input-text" style="width:230px" placeholder="输入厂家批号" id="" name="manuBatch">
		
		<button type="submit" class="btn btn-success" id="" name=""><i class="Hui-iconfont">&#xe665;</i> 搜索</button>
	</form>
	</div>
	<table class="table table-border table-bordered table-bg">
		<thead>
			<tr>
				<th scope="col" colspan="11">药品入库记录</th>
			</tr>
			<tr class="text-c">
				<th width="100">药品编号</th>
				<th width="100">药品名称</th>
				<th width="70">规格</th>
				<th width="70">单位</th>
				<th width="90">药品厂家</th>
				<th width="90">产品批号</th>
				<th width="90">进价（元）</th>
				<th width="90">剩余数量</th>
				<th width="90">选择</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${drugList }" var="drug">
			<tr class="text-c">
				<td>${drug.stoDrugBean.drugId }</td>
				<td>${drug.stoDrugBean.drugName }</td>
				<td>${drug.stoDrugBean.specific }</td>
				<td>${drug.stoDrugBean.unit }</td>
				<td>${drug.stoDrugBean.drugmanu }</td>
				<td>${drug.manuBatch }</td>
				<td>${drug.purPrice }</td>
				<td>${drug.handleNum }&nbsp;(${ fn:split(drug.stoDrugBean.specific,'-')[1]})</td>
				<td><button class="btn btn-primary" onclick="addDrug('${drug.stoDrugBean.drugId }','${drug.stoDrugBean.drugName }','${drug.stoDrugBean.drugmanu }','${drug.manuBatch }','${drug.purPrice }','${drug.batchDetailId }','${drug.handleNum }','${drug.stoDrugBean.unit }','${ fn:split(drug.stoDrugBean.specific,'-')[1]}','${drug.stoDrugBean.specific }')">添加</button></td>
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
	function addDrug(drugId,drugName,drugmanu,manuBatch,purPrice,batchDetailId,drugNum,unit,sUnit,specific){
		/* window.opener.setValue(drugName,drugmanu,putBatch,manuBatch,purPrice); */
		var str = specific.split('-');
		parent.$("#batchDetailId").val(batchDetailId);
		parent.$("#drugId").val(drugId);
		parent.$("#drugName").val(drugName);
		parent.$("#drugManu").val(drugmanu);
		parent.$("#manuBatch").val(manuBatch);
		parent.$("#purPrice").val(purPrice);
		parent.$("#drugNum").val(drugNum);
		parent.$("#unit").val(unit);
		parent.$("#sUnit").html(sUnit);
		parent.$("#specific").val(specific);
		parent.$("#sspecific").text("("+str[1]+")");
		parent.$("#sunit").text("("+unit+")");
		var index=parent.layer.getFrameIndex(window.name);
		parent.layer.close(index);
	}
</script>
</body>
</html>