<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link rel="Bookmark" href="/favicon.ico" >
<link rel="Shortcut Icon" href="/favicon.ico" />
<link rel="stylesheet" type="text/css" href="../static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="../static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="../lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css" href="../static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="../static/h-ui.admin/css/style.css" />
 <link rel="stylesheet" href="<%=path %>lib/bootstrap.css"/>
 <link href="<%=path %>lib/bootstrapValidator.css" rel="stylesheet" />
<title>添加用户 - H-ui.admin v3.1</title>
<meta name="keywords" content="H-ui.admin v3.1,H-ui网站后台模版,后台模版下载,后台管理系统模版,HTML后台模版下载">
<meta name="description" content="H-ui.admin v3.1，是一款由国人开发的轻量级扁平化网站后台模板，完全免费开源的网站后台管理系统模版，适合中小型CMS后台系统。">
</head>
<body>
<article class="page-container">
	<form action="<%=path %>user/memberAddSave.action" method="post"  class="form form-horizontal" id="form1" name="form1" target="_parent">
		<div class="row cl form-group" style="width:100%">
			<label class="form-label col-xs-3"><span class="c-red">*</span>用户账户：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text"  class="form-control" value="" placeholder="" id="adminAccount" name="adminAccount"><span id="adAccount"></span>
			</div>
		</div>
		<div class="row cl form-group" style="width:100%">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>用户名：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" name="adminName" class="form-control" value="" placeholder="" id="adminName" ><span id="adName"></span>
			</div>
		</div>
			<div class="row cl form-group" style="width:100%">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>密码：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="password" name="password" class="form-control" value="" placeholder="" id="password1" >
			</div>
		</div>
		<div class="row cl form-group" style="width:100%">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>确认密码：</label>
			<div class="formControls col-xs-8 col-sm-9 " >
				<input type="password" class="form-control" value="" placeholder="" id="confirmPassword" name="confirmPassword">
			</div>
		</div>
		<div class="row cl form-group" style="width:100%">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>性别：</label>
			<div class="formControls col-xs-8 col-sm-9 skin-minimal">
				<div class="radio">
					<input name="sex" type="radio" id="sex-1" checked value="男">
					<label for="sex-1">男</label>
				</div>
				<div class="radio">
					<input type="radio" id="sex-2" name="sex" value="女">
					<label for="sex-2">女</label>
				</div>
			</div>
		</div>
		<div class="row cl form-group" style="width:100%">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>角色：</label>
				<div class="formControls col-xs-8 col-sm-9"> <span class="select-box">
				<select class="select" size="1" name="roleId">
					
					<c:forEach items="${rlist }" var="rolelist">
						<option value="${rolelist.roleId }">${rolelist.roleName }</option>
					</c:forEach>
				</select>
			</span>
			</div>
		</div>
		<div class="row cl form-group" style="width:100%">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>部门：</label>
				<div class="formControls col-xs-8 col-sm-9"> <span class="select-box">
				<select class="select" size="1" name="deptId">
					
					<c:forEach items="${dlist }" var="ulist">
						<option value="${ulist.deptId }">${ulist.dept }</option>
					</c:forEach>
				</select>
			</span>
			</div>
		</div>
		<div class="row cl form-group" style="width:100%">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>办公电话：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="form-control" value="" placeholder="0592-8900110" id="officePhone" name="officePhone"><span id="iphone2"></span>
			</div>
		</div>
		<div class="row cl form-group" style="width:100%">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>手机：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="form-control" value="" placeholder="" id="mobilePhone" name="mobilePhone"><span id="iphone1"></span>
			</div>
		</div>
		<div class="row cl form-group" style="width:100%">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>邮箱：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="form-control" placeholder="@" name="email" id="email"><span id="ademail"></span>
			</div>
		</div>
		<div class="row cl form-group" style="width:100%">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>学历：</label>
			<div class="formControls col-xs-8 col-sm-9"> <span class="select-box">
				<select class="select" size="1" name="education">
					<option value="" selected>请选择教育背景</option>
					<option value="大专">大专</option>
					<option value="本科">本科</option>
					<option value="硕士">硕士</option>
					<option value="博士">博士</option>
					<option value="博士后">博士后</option>
				</select>
				</span> </div>
		</div>
		<div class="row cl form-group" style="width:100%">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>身份证：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="form-control"   placeholder="" id="idcard" name="idcard"><span id="adidcard"></span>
			</div>
		</div>
			<div class="row cl form-group" style="width:100%">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>生日日期：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" value="" name="birthDate"  class="form-control" style="width:150px;" />
			</div>
		</div>
		<div class="row cl form-group" style="width:100%">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>地址：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="form-control" value="" placeholder="" id="address" name="address">
			</div>
		</div>

		<div class="row cl form-group">
			<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
				<button type="submit" class="btn btn-primary btn-success radius size-L" style="width:150px;left:200px" name="signup" value="Sign up">提交</button>
			</div>
		</div>
	</form>
</article>

<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="../lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="../lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="../static/h-ui/js/H-ui.min.js"></script> 
<script type="text/javascript" src="../static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本--> 
<script type="text/javascript" src="../lib/My97DatePicker/4.8/WdatePicker.js"></script>
<script type="text/javascript" src="../lib/jquery.validation/1.14.0/jquery.validate.js"></script> 

<script type="text/javascript" src="<%=path %>lib/bootstrapValidator.js"></script>
<script type="text/javascript" src=".<%=path %>lib/bootstrap.min.js"></script>
<script type="text/javascript">

$(function () {
	
	$('#form1').bootstrapValidator({
	feedbackIcons: {
    valid: 'glyphicon glyphicon-ok',
    invalid: 'glyphicon glyphicon-remove',
    validating: 'glyphicon glyphicon-refresh'
	},
        fields: {
        	adminName: {
                message: '用户名验证失败',
                validators: {
                notEmpty: {
                	message: '用户名不能为空'
                    },
                stringLength: {
                        min: 5,
                        max: 30,
                        message: '用户名长度不得小于6位或大于30位'
                    },
                    regexp: {
                        regexp: /^[a-zA-Z0-9_\.]+$/,
                        message: '不可以输入特殊字符串'
                    },
                }
            },
            password: {
                message: '用户名验证失败',
                validators: {
                notEmpty: {
                	message: '密码不能为空'
                    },
                    stringLength: {
                        min: 6,
                        max: 30,
                        message: '密码长度不得小于6位或大于30位'
                    },
                    regexp: {
                        regexp: /^[a-zA-Z0-9_\.]+$/,
                        message: '不可以输入特殊字符串'
                    },
                    different: {
                        field: 'adminName',
                        message: '密码不得与账户名一致'
                    }
                }
            },
            confirmPassword: {
                message: '用户名验证失败',
                validators: {
                	notEmpty: {
                    	message: '密码不能为空'
                        },
                stringLength: {
                        min: 6,
                        max: 30,
                        message: '密码长度不得小于6位或大于30位'
                    },
                    regexp: {
                        regexp: /^[a-zA-Z0-9_\.]+$/,
                        message: '不可以输入特殊字符串'
                    },
                    different: {
                        field: 'adminName',
                        message: '密码不得与账户名一致'
                    }
                }
            },
            officePhone:{
                validators: {
                    notEmpty: {
                        message: '办公电话不得为空'
                    },
                 
                    regexp: {
                        regexp: /(^[0-9]{3,4}-[0-9]{7,8}$)|(^[0-9]{7,8}$)|(^([0-9]{3,4})[0-9]{3,8}$)|(^0{0,1}13[0-9]{9}$)/,
                        message: '办公电话有误'
                    },
                }
            },
            mobilePhone:{
                validators: {
                    notEmpty: {
                        message: '手机不得为空'
                    },
                    stringLength: {
                        min: 11,
                        max: 11,
                        message: '请输入11位数字'
                    },
                    regexp: {
                        regexp: /^[0-9]+$/,
                        message: '请输入数字'
                    },
                }
            },
            birthDate: {
                validators: {
                    date: {
                        format: 'YYYY-MM-DD',
                        message: '生日格式错误，请参照YYYY-MM-DD'
                    }
                }
            },
            idcard:{
            	validators: {
            		regexp: {
                        regexp: /^([0-9]{15}|[0-9]{18})$/,
                        message: '请输入15或18位数字'
                    },
            	}
            },
            email:{
            	validators: {
            		regexp: {
                        regexp: /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/,
                        message: '邮箱格式不正确'
                    },
            	}
            },
           
        }
    });
})
	


//修改密码的账号验证
 document.form1.adminAccount.onkeyup=function(){
    var reg=/^[a-zA-Z0-9]{1}[A-Z|a-z|0-9]{4,29}/;
     if(reg.test(this.value)&&this.value.length>=5&&this.value.length<=30){
    		$.ajax({
    		    url:"testAccount.action",
    		    data:"adminAccount="+$("#adminAccount").val(),
    		    dataType:"text",
    			type:"post",
    		   success:function(redata){
    			 //  document.getElementById("adAccount").innerHTML="输入正确";
    			 if(redata=="true"){
    				 document.getElementById('adAccount').innerHTML = "账户名已存在";
    			 }else{
    				 document.getElementById('adAccount').innerHTML = "可注册使用";
    			 }
    		   		},
    			error:function(){
    				alert("123")
    			}
    		   });
        
     }else{
        document.getElementById("adAccount").innerHTML="输入错误";
     }
 } 

</script> 
<!--/请在上方写此页面业务相关的脚本-->
</body>
</html>