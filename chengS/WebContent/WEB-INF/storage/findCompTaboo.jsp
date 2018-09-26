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
<title>配伍禁忌</title>
</head>
<body>
	<nav class="breadcrumb">
		<i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span>
		药品管理 <span class="c-gray en">&gt;</span> 配伍禁忌 <a
			class="btn btn-success radius r"
			style="line-height: 1.6em; margin-top: 3px"
			href="javascript:location.replace(location.href);" title="刷新"><i
			class="Hui-iconfont">&#xe68f;</i></a>
	</nav>

	<!-- 模态框（添加药品类型） -->
	<div class="modal fade" id="addModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true" >
		<form action="addCompTaboo.action" method="post"
			onsubmit="window.opener=null;window.close();">
			<div class="modal-dialog" style="width: 450px">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">添加配伍禁忌</h4>
					</div>
					<div class="modal-body">
						<div class="pd-20">
							<div class="Huiform">

								<table class="table table-bg">
									<tbody>
										<tr>
											<th width="100" class="text-r"><span class="c-red">*</span>
												药品名称1：</th>
											<td><input type="text" style="width: 250px"
												class="input-text" value="" placeholder="" id="drugId1"
												name="drugId1" datatype="*2-16" nullmsg="用户名不能为空">
												<span id="name1" style="color:red"></span>
												</td>
										</tr>
										<tr>
											<th width="100" class="text-r"><span class="c-red">*</span>
												药品名称2：</th>
											<td><input type="text" style="width: 250px"
												class="input-text" value="" placeholder="" id="drugId2"
												name="drugId2" datatype="*2-16" nullmsg="用户名不能为空">
													<span id="name2" style="color:red"></span>
												</td>
										</tr>
										<tr>
											<th width="100" class="text-r"><span class="c-red">*</span>
												产生作用：</th>
											<td><input type="textarea" style="width: 250px"
												class="textarea" value="" placeholder="" id="sideeffect"
												name="sideeffect" datatype="*2-16" nullmsg="用户名不能为空"></td>
										</tr>
									</tbody>
								</table>

							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭
						</button>
						<button type="submit" class="btn btn-primary" onclick="return comfSub()">提交更改</button>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
		</form>
	</div>

<!-- 模态框（修改药品类型） -->
	<div class="modal fade" id="updModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<form action="updCompTaboo.action" method="post" >
			<div class="modal-dialog" style="width: 450px">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">修改药品类型</h4>
					</div>
					<div class="modal-body">
						<div class="pd-20">
							<div class="Huiform">

								<table class="table table-bg">
									<tbody>
										<tr>
											<th width="100" class="text-r"><span class="c-red">*</span>
												药品名称1：</th>
											<td><input type="text" style="width: 250px"
												class="input-text" value="" placeholder="" id="updDrugId1"
												name="drugId1" datatype="*2-16" nullmsg="用户名不能为空" 
												readonly unselectable="on" />
												<input type="hidden" id="tabuId" name="tabuId" value=""/>
											</td>
										</tr>
										<tr>
											<th width="100" class="text-r"><span class="c-red">*</span>
												药品名称2：</th>
											<td><input type="text" style="width: 250px"
												class="input-text" value="" placeholder="" id="updDrugId2"
												name="drugId2" datatype="*2-16" nullmsg="用户名不能为空"></td>
										</tr>
										<tr>
											<th width="100" class="text-r"><span class="c-red">*</span>
												产生作用：</th>
											<td><input type="textarea" style="width: 250px"
												class="textarea" value="" placeholder="" id="updSideeffect"
												name="sideeffect" datatype="*2-16" nullmsg="用户名不能为空"></td>
										</tr>
									</tbody>
								</table>

							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭
						</button>
						<button type="submit" class="btn btn-primary" onclick="return comfSub()">提交更改</button>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
		</form>
	</div>

	<!-- 主页面 -->
	<div class="page-container">
		<form action="findCompTaboo.action" method="post" id="formCt" >
		<div class="text-c">
				药品名称: <input type="text" class="input-text" id="drugId1" name="drugId1" value="${condi.drugId1}"
				style="width: 150px"> <input type="hidden" id="" name="">
			<button type="submit" class="btn btn-primary radius" id="" name="">
				搜索</button>
			<button type="button" class="btn btn-primary radius" id="" 
				onclick="addModal()" >
				<i class="Hui-iconfont">&#xe600;</i> 添加
			</button>
		</div>
		</form>
		<div class="mt-20">
			<table
				class="table table-border table-bordered table-bg table-hover table-sort">
				<thead>
					<tr class="text-c">
						<th>序号</th>
						<th>药品名称1</th>
						<th>药品名称2</th>
						<th>副作用</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach items="${tabuList}" var="tabuList" varStatus="vs">
					<tr class="text-c">
						
						<td>${vs.index+1}</td>
						<td>${tabuList.drugId1}</td>
						<td>${tabuList.drugId2}</td>
						<td>${tabuList.sideeffect}</td>
						<td class="f-14"><a style="text-decoration: none" 
							onclick="updModel('${tabuList.tabuId }','${tabuList.drugId1 }','${tabuList.drugId2 }'
							 ,'${tabuList.sideeffect}')"
							href="#" title="编辑"><i class="Hui-iconfont">&#xe6df;</i></a>
							<a title="删除" href="delCompTaboo.action?tabuId=${tabuList.tabuId  }" onclick="return del()" class="ml-5"
							style="text-decoration: none"><i class="Hui-iconfont">&#xe6e2;</i></a>
						</td>
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
			<button type="submit" class="btn btn-secondary-outline radius" onclick="nextPage('${condi.page}','${pageTotal}')">下一页</button>
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
		var k=1;
		//验证第一个药品名(添加配伍禁忌)
		$(function(){
			$("#drugId1").blur(function(){
				$.ajax({
					url:"findfirstName.action?drugId1="+$("#drugId1").val(),
					type:"post",
					dataType:"text",
					success: function(data) {
						$("#name1").html(data);
						if(data=="此药品不存在"){
							k=-1;
						}
					}
				})
			})
		})
		//验证第二个药品名(添加配伍禁忌)
		$(function(){
			$("#drugId2").blur(function(){
				$.ajax({
					url:"findSecondName.action?drugId2="+$("#drugId2").val(),
					type:"post",
					dataType:"text",
					success: function(data) {
						$("#name2").html(data);
						if(data=="此药品不存在"){
							k=-1;
						}
					}
				})
			})
		})
		//验证第二个药品名(修改配伍禁忌)
		$(function(){
			$("#updDrugId2").blur(function(){
				$.ajax({
					url:"findSecondName.action?drugId2="+$("#updDrugId2").val(),
					type:"post",
					dataType:"text",
					success: function(data) {
						$("#name2").html(data);
						if(data=="此药品不存在"){
							k=-1;
						}
					}
				})
			})
		})
		function comfSub(){
			if(k==-1){
				return false;
			}
		}
		
		//弹出添加模态框
		function addModal(){
			$('#addModal').modal('show');
		}
	
	
		//弹出修改模态框
		function updModel(tabuId ,drugId1 ,drugId2,sideeffect){
			
			$("#tabuId").val(tabuId);
			$("#updDrugId1").val(drugId1);
			$("#updDrugId2").val(drugId2);
			$("#updSideeffect").val(sideeffect);
			$('#updModal').modal('show');
			
		}
		
		/*删除*/
		function del() {
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
				str = "findCompTaboo.action?page="+page;
				//attr更改form表单的action属性，改为str
				$("#formCt").attr("action",str);
				//把form表单提交。
				$("#formCt").submit();
			} else {
				return;
			}
			//location.href = str;
		}

		//下一页,pageNum,当前页数，total 总页数
		function nextPage(page, total) {
			var str = "";
			if (page < total) {
				page = Number(page) + 1;
				str = "findCompTaboo.action?page="+page;
				//attr更改form表单的action属性，改为str1
				$("#formCt").attr("action",str);
				//把form表单提交。
				$("#formCt").submit();
			} else {
				return;
			}
		}
		//点击按钮跳转页面
		function skipPage(page){
			var str = "";
			str = "findCompTaboo.action?page="+page;
			$("#formCt").attr("action",str);
			//把form表单提交。
			$("#formCt").submit();
		}
	</script>
</body>
</html>