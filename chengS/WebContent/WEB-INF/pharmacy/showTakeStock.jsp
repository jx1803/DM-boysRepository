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
<meta name="viewport"
	content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link rel="Bookmark" href="/favicon.ico">
<link rel="Shortcut Icon" href="/favicon.ico" />
<!--[if lt IE 9]>
<script type="text/javascript" src="lib/html5shiv.js"></script>
<script type="text/javascript" src="lib/respond.min.js"></script>
<![endif]-->
<link rel="stylesheet" type="text/css"
	href="../static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css"
	href="../static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css"
	href="../lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css"
	href="../static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css"
	href="../static/h-ui.admin/css/style.css" />
<!--[if IE 6]>
<script type="text/javascript" src="lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>药房药品盘点</title>
</head>
<body>
	<nav class="breadcrumb"> <i class="Hui-iconfont">&#xe67f;</i> 首页
	<span class="c-gray en">&gt;</span> 药房日常工作 <span class="c-gray en">&gt;</span>
	药房药品盘点 <a class="btn btn-success radius r"
		style="line-height: 1.6em; margin-top: 3px"
		href="javascript:location.replace(location.href);" title="刷新"><i
		class="Hui-iconfont">&#xe68f;</i></a></nav>
	<div class="page-container">
		<form action="showTakeStock.action" method="post" id="select">
			<div class="text-c">
				<div class="text-c">
					<input type="hidden" placeholder="请输入药品编码" id="hidId" name="drugId"
						value="0"> 盘点日期： <input type="text"
						onfocus="WdatePicker({ maxDate:'#F{$dp.$D(\'datemax\')||\'%y-%M-%d\'}' })"
						id="datemin" class="input-text Wdate" name="checkDate"
						style="width: 120px;"><br> 药品名称：<input type="text"
						class="input-text" style="width: 250px" placeholder="请输入药品名称"
						id="" name="drugName"> 拼音码：<input type="text"
						class="input-text" style="width: 250px" placeholder="请输入拼音码" id=""
						name="pinyinCode"> 药品编码：<input type="text"
						class="input-text" style="width: 250px" placeholder="请输入药品编码"
						id="drugId"> <br>药品分类： <span class="select-box"
						id="addShowDt" style="width: 160px"> <select class="select"
						size="1" name="typeId" id="typeId">
							<option value="0">请选择类型</option>
							<c:forEach items="${drugTypeList}" var="typeList">
								<c:if test="${typeList.typeId ==condiBean.typeId }">
									<option value="${typeList.typeId}" selected="selected">${typeList.drugType}</option>
								</c:if>
								<c:if test="${typeList.typeId !=condiBean.typeId }">
									<option value="${typeList.typeId}">${typeList.drugType}</option>
								</c:if>
							</c:forEach>
					</select>
					</span>
					<button type="button" class="btn btn-success" id="selectButton"
						name="">
						<i class="Hui-iconfont">&#xe665;</i> 搜索
					</button>
				</div>
		</form>
		<table class="table table-border table-bordered table-bg">
			<thead>
				<tr>
					<th scope="col" colspan="11">药房药品盘点盈亏情况</th>
				</tr>
				<tr class="text-c">
					<th width="80">药品编号</th>
					<th width="100">药品名称</th>
					<th width="60">单位</th>
					<th width="90">规格</th>
					<th width="150">药品厂家</th>
					<th width="90">上月入库数量</th>
					<th width="90">上月出库数量</th>
					<th width="90">当前库存</th>
					<th width="90">盘点库存</th>
					<th width="90">盈亏情况</th>
					<th width="90">盈亏数量</th>

				</tr>
			</thead>
			<tbody id="tbid">
				<form action="checkStockData.action" id="checkForm" method="post"></form>
				<c:forEach items="${stockList }" var="stock" varStatus="in">
					<tr class="text-c">
						<td>${stock.stoDrugBean.drugId }</td>
						<td>${stock.stoDrugBean.drugName }</td>
						<td>${stock.stoDrugBean.unit }</td>
						<td>${stock.stoDrugBean.specific }</td>
						<td>${stock.stoDrugBean.drugmanu }</td>
						<td>${stock.outStorage}&nbsp;(${ fn:split(stock.stoDrugBean.specific,'-')[1]})</td>
						<td>${stock.putStorage}&nbsp;(${ fn:split(stock.stoDrugBean.specific,'-')[1]})</td>
						<td>${stock.drugNum}&nbsp;(${ fn:split(stock.stoDrugBean.specific,'-')[1]})</td>
						<td>${stock.realNum}&nbsp;(${ fn:split(stock.stoDrugBean.specific,'-')[1]})</td>
						<td>${stock.paramBean.param}</td>
						<td>${stock.realNum-stock.drugNum}&nbsp;(${ fn:split(stock.stoDrugBean.specific,'-')[1]})</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>


		<div style="float: right; margain-top: 20px;">

			<button class="btn btn-secondary-outline radius" id="pre" name=""
				onclick="prePage('${condiBean.pageNum}')">上一页</button>
			<label class="label label-default radius"><font size="2">当前页数${condiBean.pageNum }/共${pageTotal }页
			</label>
			<button class="btn btn-secondary-outline radius" id="next" name=""
				onclick="nextPage('${condiBean.pageNum}','${pageTotal }')">下一页</button>
			<input type="text" style="width: 30px" class="input-text" id="page"
				onkeyup="this.value=this.value.replace(/\D/g,'')"
				onafterpaste="this.value=this.value.replace(/\D/g,'')" >
			<button type="button" onclick="jumpPage('${pageTotal }')"
				class="btn btn-secondary-outline radius">
				跳转
				<button>
		</div>
	</div>




	<!--_footer 作为公共模版分离出去-->
	<script type="text/javascript" src="../lib/jquery/1.9.1/jquery.min.js"></script>
	<script type="text/javascript" src="../lib/layer/2.4/layer.js"></script>
	<script type="text/javascript" src="../static/h-ui/js/H-ui.min.js"></script>
	<script type="text/javascript"
		src="../static/h-ui.admin/js/H-ui.admin.js"></script>
	<!--/_footer 作为公共模版分离出去-->

	<!--请在下方写此页面业务相关的脚本-->
	<script type="text/javascript"
		src="../lib/My97DatePicker/4.8/WdatePicker.js"></script>
	<script type="text/javascript"
		src="../lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
	<script type="text/javascript" src="../lib/laypage/1.2/laypage.js"></script>
	<script type="text/javascript">
		/*上一页  */
		function prePage(pageNum) {
			var str1 = "";
			if (pageNum > 1) {
				$("#pre").disabled = false;
				pageNum -= 1;
				str1 = "showTakeStock.action?pageNum=" + pageNum;
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
				str2 = "showTakeStock.action?pageNum=" + pageNum;
			} else {
				return;
			}
			$("#select").attr("action", str2);
			$("#select").submit();
		}

		/* 跳转页数 */
		function jumpPage(total) {
			var pageNum = $("#page").val();
			var str = "";
			if (pageNum > total || pageNum == '' || pageNum == 0) {
				return;
			} else {
				str = "showTakeStock.action?pageNum=" + pageNum
				$("#select").attr("action", str);
				$("#select").submit();
			}
		}

		$("#select").submit(function() {
			var drugId = $("#drugId").val();
			if (drugId == "") {
				$("#drugId").val(0);
			}
		});
		/*
		 参数解释：
		 title	标题
		 url		请求的url
		 id		需要操作的数据id
		 w		弹出层宽度（缺省调默认值）
		 h		弹出层高度（缺省调默认值）
		 */
		/*管理员-增加*/
		function admin_add(title, url, w, h) {
			layer_show(title, url, w, h);
		}
	</script>
</body>
</html>