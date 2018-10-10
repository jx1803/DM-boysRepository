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
<!--[if IE 6]>
<script type="text/javascript" src="lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>药品价格管理</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 药房药品统计 <span class="c-gray en">&gt;</span> 药房药品库存 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<!-- 主页面 -->
	<div class="page-container">
		<div class="text-c">
			<form action="selectPhaDrug.action" method="post" id="formSd" >
			药品名称: 
			<input type="text" class="input-text" id="drugName" name="drugName" value="${condi.drugName==null?"":condi.drugName}"
				style="width: 150px"> 
		 	 药品编码: 
			<input type="text" class="input-text" id="drugId" name="drugId" style="width: 150px" 
			value="${condi.drugId==0?'':condi.drugId}">
			<input type='hidden' id="h_drugId" value="0">
			拼音码:
			 <input type="text" class="input-text" id="pinyinCode" name="pinyinCode" 
			 style="width: 150px" value="${condi.pinyinCode==null?"":condi.pinyinCode}">
			药品分类：
			<span class="select-box" id="addShowDt" style="width:160px"> 
				<select class="select" size="1" name="typeId" id="typeId" >
						<option value="0">请选择类型</option>
						<c:forEach items="${drugTypeList}" var="typeList">
						<c:if test="${typeList.typeId ==condi.typeId }">
						<option value="${typeList.typeId}" selected="selected">${typeList.drugType}</option>
						</c:if>
						<c:if test="${typeList.typeId !=condi.typeId }">
						<option value="${typeList.typeId}" >${typeList.drugType}</option>
						</c:if>
						</c:forEach>
				</select>
			</span> 
			<button type="button" class="btn btn-primary radius" id="sub" onclick="subSe()" name=""> 搜索药品</button>
			</form>
		</div>
		<div class="mt-20">
			<table
				class="table table-border table-bordered table-bg table-hover table-sort">
				<thead>
					<tr class="text-c">

						<th>药品编码</th>
						<th>药品名称</th>
						<th>通用名称</th>
						<th>规格</th>
						<th>单位</th>
						<th>零售价</th>
						<th>类别</th>
						<th>剂型</th>
						<th>抗生素</th>
						<th>生产厂商</th>
						<th>生产场地</th>
						<th>库存</th>
						<th>库存低限</th>
						<th>库存上限</th>
						<th>当前状态</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach items="${drugList}" var="sdList" varStatus="vs">
					<tr class="text-c">

						<td>${sdList.stoDrugBean.drugId}</td>
						<td>${sdList.stoDrugBean.drugName}</td>
						<td>${sdList.stoDrugBean.generalName}</td>
						<td>${sdList.stoDrugBean.specific}</td>
						<td>${sdList.stoDrugBean.unit}</td>
						<td>${sdList.stoDrugBean.retailPrice}</td>
						<td>${sdList.stoDrugBean.drugTypeBean.drugType}</td>
						<td>${sdList.stoDrugBean.dfBean.dosageForm}</td>
						<td>${sdList.stoDrugBean.antibiotic}</td>
						<td>${sdList.stoDrugBean.drugmanu}</td>
						<td>${sdList.stoDrugBean.proPlace}</td>
						<td>${sdList.drugNum }&nbsp;(${ fn:split(sdList.stoDrugBean.specific,'-')[1]})</td>
						<td>${sdList.minimum }&nbsp;(${ fn:split(sdList.stoDrugBean.specific,'-')[1]})</td>
						<td>${sdList.maximum }&nbsp;(${ fn:split(sdList.stoDrugBean.specific,'-')[1]})</td>
						<td>${sdList.useable}</td>
						
					</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<br />
	
	<div style="float: right; margain-top: 20px;">
			<button  class="btn btn-secondary-outline radius" id="pre"
				name="" onclick="prePage('${condiBean.pageNum}')">上一页</button>
			<button  class="btn btn-primary size-S radius" id=""
				name="">1</button>
			<button  class="btn btn-secondary-outline radius" id="next"
				name="" onclick="nextPage('${condiBean.pageNum}','${pageTotal }')">下一页</button>
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
	function subSe(){
		var drugId=$("#drugId").val();
		if(drugId==''){
			$("#drugId").val(0);
			$("#formSd").submit();
		}else{
			$("#formSd").submit();
		}
	}

/*上一页  */
function prePage(pageNum) {
	var str1 = "";
	if (pageNum > 1) {
		$("#pre").disabled=false;
		pageNum -= 1;
		str1 = "selectPhaDrug.action?pageNum="
				+ pageNum;
	} else {
		return;
	}
	$("#formSd").attr("action", str1);
	$("#formSd").submit();
}
/*下一页  */
function nextPage(pageNum, total) {
	var str2 = "";
	if (pageNum < total) {
		pageNum = Number(pageNum) + 1;
		str2 = "selectPhaDrug.action?pageNum="
				+ pageNum;
	} else {
		return;
	}
	$("#formSd").attr("action", str2);
	$("#formSd").submit();
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
function showAdjust(title,url,w,h){
	layer_show(title,url,w,h);
}

</script>
</body>
</html>