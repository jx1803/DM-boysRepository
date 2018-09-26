<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<meta name="viewport"
	content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<!--[if lt IE 9]>
<script type="text/javascript" src="<%=path%>lib/html5shiv.js"></script>
<script type="text/javascript" src="<%=path%>lib/respond.min.js"></script>
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
<!--[if IE 6]>
<script type="text/javascript" src="<%=path%>lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>药品剂型</title>
</head>
<body>
	<nav class="breadcrumb">
		<i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span>
		药品管理 <span class="c-gray en">&gt;</span> 药品剂型 <a
			class="btn btn-success radius r"
			style="line-height: 1.6em; margin-top: 3px"
			href="javascript:location.replace(location.href);" title="刷新"><i
			class="Hui-iconfont">&#xe68f;</i></a>
	</nav>

	<!-- 模态框（添加药品剂型） -->
	<div class="modal fade" id="addModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<form action="addDfInfo.action" method="post">
		<div class="modal-dialog" style="width: 450px">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">添加药品剂型</h4>
				</div>
				<div class="modal-body">
					<div class="pd-20">
						<div class="Huiform">
							
								<table class="table table-bg">
									<tbody>
										<tr>
											<th width="100" class="text-r"><span class="c-red">*</span>
												药品剂型：</th>
											<td><input type="text" style="width: 200px"
												class="input-text" value="" placeholder="" id="user-name"
												name="dosageForm" datatype="*2-16" nullmsg="不能为空">
												
											</td>
										</tr>
									</tbody>
								</table>
							
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭
					</button>
					<button type="submit" class="btn btn-primary">提交更改</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		</form>
	</div>

	<!-- 模态框（修改药品剂型） -->
	<div class="modal fade" id="updModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<form action="updDfInfo.action" method="post" >
		<div class="modal-dialog" style="width: 450px">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">修改药品剂型</h4>
				</div>
				<div class="modal-body">
					<div class="pd-20">
						<div class="Huiform">
							
								<table class="table table-bg">
									<tbody>
										<tr>
											<th width="100" class="text-r"><span class="c-red">*</span>
												药品剂型：</th>
											<td><input type="text" style="width: 200px"
												class="input-text" value="" placeholder="" id="updname"
												name="dosageForm" datatype="*2-16" nullmsg="不能为空">
												<input type="hidden" id="updId" name="dosageId" value="" />
											</td>
										</tr>
									</tbody>
								</table>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="submit" class="btn btn-primary">提交更改</button>
					
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		</form>
	</div>
	
	<!-- 主页面 -->
	<div class="page-container">
		<form action="findDfInfo.action" method="post" id="formDf" >
		<div class="text-c">
			药品名称: <input type="text" class="input-text" id="" name="dosageForm" value="${dosageForm==null?"":dosageForm}" style="width: 150px">
			<button type="submit" class="btn btn-primary radius" id="" name="">
				搜索剂型</button>
			<button type="button" class="btn btn-primary radius" id="" name=""
				onclick="addModel()">
				<i class="Hui-iconfont">&#xe600;</i> 添加剂型
			</button>
		</div>
		</form>
		<div class="mt-20">
			<table
				class="table table-border table-bordered table-bg table-hover table-sort">
				<thead>
					<tr class="text-c">

						<th>剂型ID</th>
						<th>剂型名称</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach items="${dfList }" var="dfList" varStatus="vs">
					<tr class="text-c">
						<td>${vs.index+1}</td>
						<td>${dfList.dosageForm }</td>
						<td class="f-14">
							<a style="text-decoration: none" 
							onclick="updModel('${dfList.dosageId}','${dfList.dosageForm}')"  title="编辑">
							<i class="Hui-iconfont">&#xe6df;</i></a>
							<a title="删除" href="delDfInfo.action?dosageId=${dfList.dosageId}"
							onclick="return upd()" class="ml-5"
							style="text-decoration: none">
							<i class="Hui-iconfont">&#xe6e2;</i></a></td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
		</div>
		<br />
		<div style="float: right; margain-top: 20px;">
			<span ><font size="2">共${pageTotal}页  &nbsp;当前页${condi.page}/${pageTotal}</font></span>  &nbsp; &nbsp; &nbsp;
			<button type="submit" class="btn btn-secondary-outline radius"  onclick="upPage('${condi.page}')">上一页</button>
			<c:forEach var="page" begin="1" end="${pageTotal}">
			 <button type="submit" class="btn btn-primary size-S radius" onclick="skipPage('${page}')">${page}</button>
			</c:forEach>
			<button type="submit" class="btn btn-secondary-outline radius" onclick="nextPage('${condi.page}','${pageTotal }')">下一页</button>
		</div>
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
		src="<%=path%>lib/My97DatePicker/4.8/WdatePicker.js"></script>
	<script type="text/javascript"
		src="<%=path%>lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
	<script type="text/javascript"
		src="<%=path%>lib/laypage/1.2/laypage.js"></script>
	<script type="text/javascript">
		//弹出添加模态框
		function addModel(){
			$('#addModal').modal('show');
		}
	
		//弹出修改模态框
		function updModel(id,name){
			$('#updModal').modal('show');
			$("#updname").val(name); 
			$("#updId").val(id);
			
		}
	
		/*删除*/
		function upd() {
			var k =confirm('是否删除数据？')
			if(k==true){
				return true;
			}else{
				return false;
			}
		}
		
		//上一页
		function upPage(page) {
			var str = "";
			if (page > 1) {
				page -= 1;
				str = "findDfInfo.action?page="+page;
				//attr更改form表单的action属性，改为str1
				$("#formDf").attr("action",str);
				//把form表单提交。
				$("#formDf").submit();
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
				str = "findDfInfo.action?page="+page;
				//attr更改form表单的action属性，改为str1
				$("#formDf").attr("action",str);
				//把form表单提交。
				$("#formDf").submit();
			} else {
				return;
			}
		}
		//点击按钮跳转页面
		function skipPage(page){
			var str = "";
			str = "findDfInfo.action?page="+page;
			$("#formDf").attr("action",str);
			//把form表单提交。
			$("#formDf").submit();
		}
	</script>
</body>
</html>