<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 
	首页 <span class="c-gray en">&gt;</span> 
	药房统计<span class="c-gray en">&gt;</span> 
	药品调价信息 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<!-- 主页面 -->
<div class="page-container">
		<div class="text-c">
			<form action="findAdjustPrice.action" method="post" id="formSd" >
			药品名称: 
			<input type="text" class="input-text" id="drugName" name="drugName" value="${condi.drugName==null?"":condi.drugName}"
				style="width: 150px"> 
		 	 药品编码: 
			<input type="text" class="input-text" id="drugId" name="drugId" style="width: 150px" 
			value="${condi.drugId==0?"":condi.drugId}">
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
			<button type="submit" class="btn btn-primary radius" id="sub" name=""> 搜索药品</button>
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
						<th>当前售价(元)</th>
						<th>查看调价统计</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach items="${stoDrugList}" var="sdList" varStatus="vs">
					<tr class="text-c">
						<td>${sdList.drugId}</td>
						<td>${sdList.drugName}</td>
						<td>${sdList.generalName}</td>
						<td>${sdList.retailPrice}</td>
						<td class="f-14">
							<a href="<%=path%>storage/toPriceStatistics.action?drugId=${sdList.drugId}"  style="text-decoration: none" title="调价统计" >
							<i class="Hui-iconfont">&#xe725;</i></a>
							</td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<br />
		<div style="float: right; margain-top: 20px;">
			<button type="submit" class="btn btn-secondary-outline radius"  onclick="upPage('${condi.page}')">上一页</button>
			<label class="label label-default radius"><font size="2">当前页${condi.page}/共${pageTotal}页</font></label>
			<button type="submit" class="btn btn-secondary-outline radius" onclick="nextPage('${condi.page}','${pageTotal }')">下一页</button>
			<input type="text" style="width:30px" class="input-text"  id="page" name="page" oninput = "value=value.replace(/[^\d]/g,'')" >
			<button type="button" class="btn btn-secondary-outline radius"  onclick="return jumpPage('${pageTotal}')">跳转</button>
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

//提交搜索
$(function(){
	$("#sub").click(function(){
		var num = $("#drugId").val()
		if(num==""){ 
			$("#drugId").val($("#h_drugId").val())
		}
		$("#formSd").submit();
	})
})

//上一页
function upPage(page) {
	var str = "";
	if (page > 1) {
		page -= 1;
		str = "findAdjustPrice.action?page="+page;
		//attr更改form表单的action属性，改为str1
		$("#formSd").attr("action",str);
		//把form表单提交。
		var num = $("#drugId").val()
		if(num==""){
			$("#drugId").val($("#h_drugId").val())
		}
		$("#formSd").submit();
	} else {
		return;
	}
	//location.href = str1;
}

//下一页,pageNum,当前页数，total 总页数
function nextPage(page, total) {
	var str = "";
	if (page < total) {
		page = Number(page) + 1;
		str = "findAdjustPrice.action?page="+page;
		//attr更改form表单的action属性，改为str1
		$("#formSd").attr("action",str);
		//把form表单提交。
		var num = $("#drugId").val()
		if(num==""){
			$("#drugId").val($("#h_drugId").val())
		}
		$("#formSd").submit();
	} else {
		return;
	}
}

//跳转页面
function jumpPage(total){
	var page = $("#page").val();
	var str = "";
	str = "findAdjustPrice.action?page="+page;
	$("#formSd").attr("action",str);
	
	if(page=='' || page>total || page==0){
		$("#page").val('');
	}else{
		var num = $("#drugId").val()
		if(num==""){
			$("#drugId").val($("#h_drugId").val())
		}
		//把form表单提交。
		$("#formSd").submit();
	}
	
}

/* //点击按钮跳转页面
function skipPage(page){
	var str = "";
	str = "findAdjustPrice.action?page="+page;
	$("#formSd").attr("action",str);
	//把form表单提交。
	var num = $("#drugId").val()
	if(num==""){
		$("#drugId").val($("#h_drugId").val())
	}
	$("#formSd").submit();
}
	 */


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