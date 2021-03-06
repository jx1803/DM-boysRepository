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
<meta name="viewport"
	content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<!--[if lt IE 9]>
<script type="text/javascript" src="lib/html5shiv.js"></script>
<script type="text/javascript" src="lib/respond.min.js"></script>
<![endif]-->
<link rel="stylesheet" type="text/css"
	href="<%=path%>static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css"
	href="<%=path%>static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css"
	href="<%=path%>lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css"
	href="<%=path%>static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css"
	href="<%=path%>static/h-ui.admin/css/style.css" />

<title>药品出入库明细统计</title>
<link rel="stylesheet"
	href="<%=path%>lib/zTree/v3/css/zTreeStyle/zTreeStyle.css"
	type="text/css">
</head>
<body class="pos-r">

	<div style="margin-left: 0px;">
		<nav class="breadcrumb">
			<i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span>
			药库统计<span class="c-gray en">&gt;</span>药品出入库统计<a
				class="btn btn-success radius r"
				style="line-height: 1.6em; margin-top: 3px"
				href="javascript:location.replace(location.href);" title="刷新"><i
				class="Hui-iconfont">&#xe68f;</i></a>
		</nav>
		<form id="audit" action="specialDrugsInStorage.action" method="post">
			<div class="page-container">
				<div class="text-c">
					<table class="table table-border table-bordered table-bg">
						<tr>
							<td colspan="2">日期范围： <input type="text"
								onfocus="WdatePicker({ maxDate:'#F{$dp.$D(\'logmax\')||\'%y-%M-%d\'}' })"
								id="logmin" name="afterDate" class="input-text Wdate"
								value="${condiBean.afterDate==null?'':condiBean.afterDate}"
								style="width: 120px;"> -
							<input type="text"
								onfocus="WdatePicker({ minDate:'#F{$dp.$D(\'logmin\')}',maxDate:'%y-%M-%d' })"
								id="logmax" name="beforeDate" class="input-text Wdate"
								value="${condiBean.beforeDate==null?'':condiBean.beforeDate}"
								style="width: 120px;"></td>
							<td>经办人：<input type="text" name="adminName" id="adminName"
								placeholder="经办人"
								value="${condiBean.adminName==null?'':condiBean.adminName}"
								style="width: 120px" class="input-text"></td>
							
						</tr>
						<tr>
							<td>药品名称： <input type="text" name="drugName" id="drugName"
								placeholder=""
								value="${condiBean.drugName==null?'':condiBean.drugName}"
								style="width: 120px" class="input-text"></td>
							<td>拼音码： <input type="text" name="pinyinCode"
								id="pinyinCode" placeholder=""
								value="${condiBean.pinyinCode==null?'':condiBean.pinyinCode}"
								style="width: 120px" class="input-text"></td>
							<td>药品编码： <input type="text" name="drugId" id="drugId"
								placeholder="药品编码"
								style="width: 120px" class="input-text" value="${condiBean.drugId==0?'':condiBean.drugId }"></td>
							<td>
								<button name="serachRecord" id="serachRecord"
									class="btn btn-success" onclick="submitForm()">
									<i class="Hui-iconfont">&#xe665;</i>搜索
								</button>
							
							</td>
						
						</tr>	
					</table>
							</div>
	
	</div>
</form>
				</div>

				<div class="cl pd-5 bg-1 bk-gray mt-20">

					<span class="r">共有数据：<strong>${count}</strong> 条
					</span>
				</div>
				<div class="mt-20">
					<table class="table table-border table-bordered table-bg">
						<thead>
							<tr class="text-c">
								<th width="100">药品编码</th>
								<th width="100">药品名称</th>
								<th width="60">数量</th>
								<th width="60">单价(元)</th>
								<th width="60">总金额(元)</th>
								<th width="100">时间</th>
								<th width="60">经办人</th>
								<th width="100">供应商</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${outInList}" var="daBean">
								<tr class="text-c">
									<td>${daBean.drugId}</td>
									<td>${daBean.stoDrugBean.drugName}</td>
									 <td>${daBean.applyNum}</td> 
									<td>${daBean.stoDrugBean.purPrice}</td>
									 <td>${daBean.applyNum*daBean.stoDrugBean.purPrice}</td> 
									 <td>${daBean.checkDate}</td> 
							 		<td>${daBean.adminBean.adminName}</td> 
									<td>${daBean.stoDrugBean.drugmanu}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					</br>
					<div style="float: right; margain-top: 20px;">
			<button type="submit" class="btn btn-secondary-outline radius"  onclick="upPage('${condiBean.pageNum}')">上一页</button>
			<label class="label label-default radius"><font size="2">当前页${condiBean.page}/共${pageTotal}页</font></label>
			<button type="submit" class="btn btn-secondary-outline radius" onclick="nextPage('${condiBean.page}','${pageTotal }')">下一页</button>
			<input type="text" style="width:30px" class="input-text"  id="page" name="page" >
			<button type="button" class="btn btn-secondary-outline radius"  onclick="return jumpPage('${pageTotal}')">跳转</button>
		</div>
					<%-- <div class="text-c">

						<button type="button" class="btn btn-success"
							onclick="prePage('${condiBean.pageNum}')">上一页</button>
						${condiBean.pageNum}/${pageTotal}
						<button type="button" class="btn btn-success"
							onclick="nextPage('${condiBean.pageNum}', '${pageTotal}')">下一页</button>
					</div> --%>
				</div>
	


	<!--_footer 作为公共模版分离出去-->
	<script type="text/javascript"
		src="<%=path%>lib/jquery/1.9.1/jquery.min.js"></script>
	<script type="text/javascript" src="<%=path%>lib/layer/2.4/layer.js"></script>
	<script type="text/javascript"
		src="<%=path%>static/h-ui/js/H-ui.min.js"></script>
	<script type="text/javascript"
		src="<%=path%>static/h-ui.admin/js/H-ui.admin.js"></script>
	<!--/_footer 作为公共模版分离出去-->

	<!--请在下方写此页面业务相关的脚本-->
	<script type="text/javascript"
		src="<%=path%>lib/zTree/v3/js/jquery.ztree.all-3.5.min.js"></script>
	<script type="text/javascript"
		src="<%=path%>lib/My97DatePicker/4.8/WdatePicker.js"></script>
	<script type="text/javascript"
		src="<%=path%>lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
	<script type="text/javascript"
		src="<%=path%>lib/laypage/1.2/laypage.js"></script>
	<script type="text/javascript">
		
	</script>
	<script type="text/javascript">
	$("#audit").submit (function(){
	
		var drugId=$("#drugId").val();
		if(drugId==""){
			$("#drugId").val(0);
		}
	});
		/*上一页  */
		function upPage(pageNum) {
			
			var str1 = "";
			if (pageNum > 1) {
				pageNum -= 1;
				str1 = "specialDrugsInStorage.action?page=" + pageNum;
			} else {
				return;
			}
			$('#audit').attr("action", str1);
		
			 $("#audit").submit();
		}
		/*下一页  */
		function nextPage(pageNum, total) {
		
			var str2 = "";
			if (pageNum < total) {
				pageNum = Number(pageNum) + 1;
				str2 = "specialDrugsInStorage.action?page=" + pageNum;

			} else {
				return;
			}
			console.log("11111");
			$("#audit").attr("action", str2);
			
			$("#audit").submit();
		
		 /* 	submitForm(); */
		}
		
     /*提交表单进行验证 */
     function submitForm(){
    	 var drugId = $("#drugId").val();
    	 if(drugId==''){
    		 $("#drugId").val(0);
    	 }
    	 alert("提交");
    	 $("#audit").submit();
     }
	</script>
</body>
</html>