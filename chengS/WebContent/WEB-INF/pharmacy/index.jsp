<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link rel="Bookmark" href="<%=path%>/favicon.ico">
<link rel="Shortcut Icon" href="<%=path%>/favicon.ico" />
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

<link href="<%=path%>lib/bootstrapValidator.css" rel="stylesheet" />

<!--[if IE 6]>
<script type="text/javascript" src="lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>SPMS智慧药房 V1.0</title>

</head>
<body>
	<!-- 修改密码 -->
	<div class="modal fade" id="changepassword" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<form action="<%=path%>admin/forgetPassword.action" method="post"
			id="form2" name="form2">
			<div class="modal-dialog" style="width: 450px">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">修改密码</h4>
					</div>
					<div class="modal-body">
						<div class="pd-20">
							<div class="Huiform">

								<table class="table table-bg">
									<tbody>


										<input type="hidden" value="${sessionScope.User.adminAccount }" name="adminAccount" id="adminAccount"/>

										<tr class="row cl form-group">
											<th width="100" class="text-r"><span class="c-red">*</span>
												原始密码：</th>
											<td><input type="password" style="width: 200px"
												class="form-control" value="" placeholder=""
												name="password" id="password"></td>
										</tr>
										<tr class="row cl form-group">
											<th width="100" class="text-r"><span class="c-red">*</span>
												新密码：</th>
											<td><input type="password" style="width: 200px"
												class="form-control" value="" placeholder=""  id="newPassword"
												 name="newPassword"></td>
										</tr>
										<tr class="row cl form-group">
											<th width="100" class="text-r"><span class="c-red">*</span>
												确认密码：</th>
											<td><input type="password" style="width: 200px"
												class="form-control" value="" placeholder=""
												 name="comfirmPassword"></td>
										</tr>
									</tbody>
								</table>

							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
						<button type="button"
							class="btn btn-primary btn-success radius size-L"
							style="width: 150px" name="signup" value="Sign up" onclick="changePsw()">提交</button>

					</div>
				</div>
				<!-- /.modal-content -->
			</div>
		</form>
	</div>
	<header class="navbar-wrapper">
		<div class="navbar navbar-fixed-top">
			<div class="container-fluid cl">
				<a class="logo navbar-logo f-l mr-10 hidden-xs"
					href="<%=path%>/aboutHui.shtml">SPMS智慧药房 </a> <span
					class="logo navbar-slogan f-l mr-10 hidden-xs">V1.0</span> <a
					aria-hidden="false" class="nav-toggle Hui-iconfont visible-xs"
					href="javascript:;">&#xe667;</a>

				<nav id="Hui-userbar"
					class="nav navbar-nav navbar-userbar hidden-xs">
					<ul class="cl">
						<li>${role.roleName }</li>
						<li class="dropDown dropDown_hover"><a href="#"
							class="dropDown_A"> ${sessionScope.User.adminName }<i class="Hui-iconfont">&#xe6d5;</i></a>
							<ul class="dropDown-menu menu radius box-shadow">
								<li><a href="javascript:;" onClick="myselfinfo()">个人信息</a></li>

								<li><a href="javascript:;" onclick="forgetpassword()">修改密码</a></li>
								<li><a href="<%=path%>admin/toLogin.action">退出</a></li>
							</ul></li>

						<li id="Hui-msg"><a href="javascript:;"
							onclick="member_add('消息提醒','<%=path%>pharmacy/toWarn.action','','510')"
							title="消息"><span class="badge badge-danger" id="warnId">${warnCount}</span><i
								class="Hui-iconfont" style="font-size: 18px">&#xe68a;</i></a></li>

						<li id="Hui-skin" class="dropDown right dropDown_hover"><a
							href="javascript:;" class="dropDown_A" title="换肤"><i
								class="Hui-iconfont" style="font-size: 18px">&#xe62a;</i></a>
							<ul class="dropDown-menu menu radius box-shadow">
								<li><a href="javascript:;" data-val="default"
									title="默认（黑色）">默认（黑色）</a></li>
								<li><a href="javascript:;" data-val="blue" title="蓝色">蓝色</a></li>
								<li><a href="javascript:;" data-val="green" title="绿色">绿色</a></li>
								<li><a href="javascript:;" data-val="red" title="红色">红色</a></li>
								<li><a href="javascript:;" data-val="yellow" title="黄色">黄色</a></li>
								<li><a href="javascript:;" data-val="orange" title="橙色">橙色</a></li>
							</ul></li>
					</ul>
				</nav>
			</div>
		</div>
	</header>
	<aside class="Hui-aside">
		<div class="menu_dropdown bk_2">

			

			<c:forEach items="${ulist}" var="list">

				<dl id="menu-product">
					<c:if test="${list.pid == 0 }">
						<dt>
							<i class="Hui-iconfont">&#xe620;</i> ${list.permission }<i
								class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
						</dt>
					</c:if>
					<dd>
						<c:forEach items="${ulist }" var="slist">
							<c:if test="${list.permissionId == slist.pid }">
								<ul>
									<li><a data-href="<%=path%>${slist.url}"
										data-title="${slist.permission }" href="javascript:void(0)">${slist.permission}</a></li>
								</ul>
							</c:if>
						</c:forEach>
					</dd>
				</dl>

			</c:forEach>
			

		</div>
	</aside>
	<div class="dislpayArrow hidden-xs">
		<a class="pngfix" href="javascript:void(0);"
			onClick="displaynavbar(this)"></a>
	</div>
	<section class="Hui-article-box">
		<div id="Hui-tabNav" class="Hui-tabNav hidden-xs">
			<div class="Hui-tabNav-wp">
				<ul id="min_title_list" class="acrossTab cl">
					<li class="active"><span title="我的桌面" data-href="welcome.html">我的桌面</span>
						<em></em></li>
				</ul>
			</div>
			<div class="Hui-tabNav-more btn-group">
				<a id="js-tabNav-prev" class="btn radius btn-default size-S"
					href="javascript:;"><i class="Hui-iconfont">&#xe6d4;</i></a><a
					id="js-tabNav-next" class="btn radius btn-default size-S"
					href="javascript:;"><i class="Hui-iconfont">&#xe6d7;</i></a>
			</div>
		</div>
		<div id="iframe_box" class="Hui-article">
			<div class="show_iframe">
				<div style="display: none" class="loading"></div>
			</div>
		</div>
	</section>

	<div class="contextMenu" id="Huiadminmenu">
		<ul>
			<li id="closethis">关闭当前</li>
			<li id="closeall"><iframe scrolling="yes" frameborder="0" src=""></iframe>
				关闭全部</li>
		</ul>
	</div>
	<!--_footer 作为公共模版分离出去-->
	<script type="text/javascript"
		src="<%=path%>lib/jquery/1.9.1/jquery.min.js"></script>
	<script type="text/javascript" src="<%=path%>lib/layer/2.4/layer.js"></script>
	<script type="text/javascript"
		src="<%=path%>static/h-ui/js/H-ui.min.js"></script>
	<script type="text/javascript"
		src="<%=path%>static/h-ui.admin/js/H-ui.admin.js"></script>
	<script type="text/javascript"
		src="<%=path%>lib/bootstrapValidator.js"></script>
	<script type="text/javascript" src=".<%=path%>lib/bootstrap.min.js"></script>
	<!--/_footer 作为公共模版分离出去-->

	<!--请在下方写此页面业务相关的脚本-->
	<script type="text/javascript"
		src="<%=path%>lib/jquery.contextmenu/jquery.contextmenu.r2.js"></script>
	<script type="text/javascript">
	
	//修改密码变成ajax
	function changePsw(){
		$.ajax({
		    url:"forgetPassword.action",
		    data:{"adminAccount":$("#adminAccount").val(),"password":$("#password").val(),"newPassword":$('#newPassword').val()},
		    dataType:"text",
			type:"post",
		   success:function(redata){
			   if(redata=="1"){
				   alert("密码错误！");
			   }else if(redata=="2"){
				   alert("密码修改成功，请重新登录！");
				   top.location.href="${pageContext.request.contextPath}/admin/toLogin.action";
			   }
		   },
	});
	}
	
		$(function() {
			addModelFrom2();

		})
		//弹出修改密码模态框
		function forgetpassword() {

			$('#changepassword').modal('show');
			addModelFrom2();
		};

		$(function() {

			$('#changepassword').on(
					'hidden.bs.modal',
					function() {
						$("div.form-group").removeClass(
								"has-feedback has-success has-error");
						$('#password1').val("");
						$("form2").bootstrapValidator('removeField',
								'password1');

					});
		})
		function addModelFrom2() {
			/* $(function () { */
			$('#form2').bootstrapValidator({
				feedbackIcons : {
					valid : 'glyphicon glyphicon-ok',
					invalid : 'glyphicon glyphicon-remove',
					validating : 'glyphicon glyphicon-refresh'
				},
				fields : {

					password: {
		                validators: {
		                    notEmpty: {
		                        message: '密码不得为空'
		                    },
		                    stringLength: {
		                        min: 6,
		                        max: 30,
		                        message: '密码长度不得小于6位或大于30位'
		                    }
		                }
		            },
					newPassword : {
						validators : {
			                    notEmpty: {
			                        message: '密码不得为空'
			                    },
			                    stringLength: {
			                        min: 6,
			                        max: 30,
			                        message: '密码长度不得小于6位或大于30位'
			                    },
							
							
						}
					},
					comfirmPassword : {
						validators: {
		                    notEmpty: {
		                        message: '密码不得为空'
		                    },
		                    stringLength: {
		                        min: 6,
		                        max: 30,
		                        message: '密码长度不得小于6位或大于30位'
		                    },
		                    identical : {
								field : 'newPassword',
								message : '密码不一致'
							}
		            
		                }
					},

				}
			});
		}

		function myselfinfo() {
			layer.open({
				type : 1,
				area : [ '300px', '200px' ],
				fix : false, //不固定
				maxmin : true,
				shade : 0.4,
				title : '查看信息',
				content : '<div>管理员信息</div>'
			});
		}

		/*资讯-添加*/
		function article_add(title, url) {
			var index = layer.open({
				type : 2,
				title : title,
				content : url
			});
			layer.full(index);
		}
		/*图片-添加*/
		function picture_add(title, url) {
			var index = layer.open({
				type : 2,
				title : title,
				content : url
			});
			layer.full(index);
		}
		/*产品-添加*/
		function product_add(title, url) {
			var index = layer.open({
				type : 2,
				title : title,
				content : url
			});
			layer.full(index);
		}
		/*用户-添加*/
		function member_add(title, url, w, h) {
			layer_show(title, url, w, h);
		}
	</script>


</body>
</html>