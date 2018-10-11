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
<link rel="stylesheet" type="text/css"
	href="<%=path%>lib/bootstrapValidator.css"/>
<!--[if IE 6]>
<script type="text/javascript" src="<%=path%>lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>药品剂型</title>
</head>
<body>
	<nav class="breadcrumb">
		<i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span>
		药品管理 <span class="c-gray en">&gt;</span> 修改库存限制 <a
			class="btn btn-success radius r"
			style="line-height: 1.6em; margin-top: 3px"
			href="javascript:location.replace(location.href);" title="刷新"><i
			class="Hui-iconfont">&#xe68f;</i></a>
	</nav>

	<!-- 模态框（修改药品剂型） -->
	<div class="modal fade" id="updModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<form action="updPharmacyStock.action" method="post" id="updForm">
		<div class="modal-dialog" style="width: 450px">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">修改药房库存限制</h4>
				</div>
				<div class="modal-body">
					<div class="pd-20">
						<div class="Huiform">
							<input type="hidden" id="phaDrugId" name="phaDrugId" value="" />
								<table class="table table-bg">
									<tbody>
										<tr>
											<th width="100" class="text-r"><span class="c-red">*</span>
												最低库存：</th>
											<td>
												<div class="form-group">
												<input type="text" style="width: 200px"
												class="input-text"  id="minimum" name="minimum"><br/>
												</div>
											<td>
										</tr>
										
										 <tr>
											
											<th width="100" class="text-r"><span class="c-red">*</span>
												最高库存：</th>
											<td>
												<div class="form-group">
												<input type="text" style="width: 200px"
												class="input-text"  id="maximum" name="maximum" ><br/>
												</div>
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
		
		</div>
		</form>
	</div>
	
	<!-- 主页面 -->
	<div class="page-container">
		<form action="findPharmacyStock.action" method="post" id="formDi" >
		<div class="text-c">
			药品编号: <input type="text" class="input-text" id="drugId" name="drugId" 
			value="${condi.drugId==0?"":condi.drugId}" style="width: 150px">
			<input type='hidden' id="h_drugId" value="0">
			<button type="button" class="btn btn-primary radius" id="sub" name="">搜索</button>
		</div>
		</form>
		<div class="mt-20">
			<table
				class="table table-border table-bordered table-bg table-hover table-sort">
				<thead>
					<tr class="text-c">

						<th>ID</th>
						<th>药品编码</th>
						<th>药品通用名</th>
						<th>库存量</th>
						<th>最小库存</th>
						<th>最大库存</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach items="${phaDrugList }" var="phaDrug" varStatus="vs">
					<tr class="text-c">
						<td>${vs.index+1}</td>
						<td>${phaDrug.drugId}</td>
						<td>${phaDrug.stoDrugBean.generalName}</td>
						<td>${phaDrug.drugNum}</td>
						<td>${phaDrug.minimum}</td>
						<td>${phaDrug.maximum}</td>
						<td class="f-14">
							<a style="text-decoration: none" 
							onclick="updModel('${phaDrug.phaDrugId}','${phaDrug.minimum}','${phaDrug.maximum}')"  title="编辑">
							<i class="Hui-iconfont">&#xe6df;</i></a>
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
	<script type="text/javascript" src="<%=path%>lib/bootstrap.js"></script>	
	<script type="text/javascript" src="<%=path%>lib/bootstrapValidator.js"></script>	
	<script type="text/javascript">
	
	  //Modal验证销毁重构
    $(function(){
    	 $('#updModal').on('hidden.bs.modal', function() {
    		 $("#updForm").data('bootstrapValidator').resetForm(); 
		    });
    	 
    }) 

    //form验证规则(修改剂型)
    function updFormValidator(){
        $('#updForm').bootstrapValidator({
        	live: 'disabled',
            message: 'This value is not valid',
            feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
                    },
            fields: {
                //最小库存
            	minimum: {
                    validators: {
                        notEmpty: {
                            message: '输入不能为空'
                        },
                        regexp: {
                            regexp: /^[0-9]+$/,
                            message: '请输入数字'
                        },
                    }
                },
                maximum: {
                    validators: {
                        notEmpty: {
                            message: '输入不能为空'
                        },
                        regexp: {
                            regexp: /^[0-9]+$/,
                            message: '请输入数字'
                        },
                    }
                }
            }
        });
    }
	
    //提交搜索
	$(function(){
		$("#sub").click(function(){
			var num = $("#drugId").val()
			if(num==""){ 
				$("#drugId").val($("#h_drugId").val())
			}
			$("#formDi").submit();
		})
	})
		
		//弹出修改模态框
		function updModel(phaDrugId,minimum,maximum){
			updFormValidator();
			$("#phaDrugId").val(phaDrugId); 
			$("#minimum").val(minimum);
			$("#maximum").val(maximum);
			$('#updModal').modal('show');
		}
	
		//上一页
		function upPage(page) {
			var str = "";
			if (page > 1) {
				page -= 1;
				str = "findPharmacyStock.action?page="+page;
				//attr更改form表单的action属性，改为str1
				$("#formDi").attr("action",str);
				//把form表单提交。
				var num = $("#drugId").val()
				if(num==""){
					$("#drugId").val($("#h_drugId").val())
				}
				$("#formDi").submit();
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
				str = "findPharmacyStock.action?page="+page;
				//attr更改form表单的action属性，改为str1
				$("#formDi").attr("action",str);
				//把form表单提交。
				var num = $("#drugId").val()
				if(num==""){
					$("#drugId").val($("#h_drugId").val())
				}
				$("#formDi").submit();
			} else {
				return;
			}
		}
		
		//跳转页面
		function jumpPage(total){
			var page = $("#page").val();
			var str = "";
			str = "findPharmacyStock.action?page="+page;
			$("#formDi").attr("action",str);
			
			if(page=='' || page>total || page==0){
				$("#page").val('');
			}else{
				var num = $("#drugId").val()
				if(num==""){
					$("#drugId").val($("#h_drugId").val())
				}
				//把form表单提交。
				$("#formDi").submit();
			}
			
		}
		
	/* 	
		//点击按钮跳转页面
		function skipPage(page){
			var str = "";
			str = "findPharmacyStock.action?page="+page;
			$("#formDi").attr("action",str);
			//把form表单提交。
			$("#formDi").submit();
		} */
	</script>
</body>
</html>