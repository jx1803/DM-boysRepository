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
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link href="<%=path %>static/h-ui/css/H-ui.min.css" rel="stylesheet" type="text/css" />
<link href="<%=path %>static/h-ui.admin/css/H-ui.login.css" rel="stylesheet" type="text/css" />
<link href="<%=path %>static/h-ui.admin/css/style.css" rel="stylesheet" type="text/css" />
<link href="<%=path %>lib/Hui-iconfont/1.0.8/iconfont.css" rel="stylesheet" type="text/css" />
 <link rel="stylesheet" href="<%=path %>lib/jigsaw.css">
 <link rel="stylesheet" href="<%=path %>lib/bootstrap.css"/> 
 <link href="<%=path %>lib/bootstrapValidator.css" rel="stylesheet" />

<title>后台登录 </title>
<meta name="keywords" content="">
<meta name="description" content="">
<style type="text/css" >

	#ccont{
            background-image:url(../images/error.jpg);

            background-position:center;           

             background-repeat:repeat-y；
        }

</style>

</head>
<body>


<input type="hidden" id="TenantId" name="TenantId" value="" />
<div class=""></div>
<div class=loginWraper >
   <div id="loginform" class="loginBox"><center><div style="font-size:25px">智慧药房管理系统</div></center>
     <form class="form form-horizontal" action="<%=path %>admin/toIndex.action" method="post" name="form1" id="form1" target="_parent" >
      <div class="row cl form-group" style="width:100%;">
        <label class="form-label col-xs-3"><i class="Hui-iconfont">&#xe60d;</i></label>
        <div class="formControls col-xs-8">
          <input id="adminAccount" name="adminAccount" type="text" placeholder="账户" class="form-control" >
        </div>
      </div>
      
      <div class="row cl form-group" style="width:100%;">
        <label class="form-label col-xs-3"><i class="Hui-iconfont">&#xe60e;</i></label>
        <div class="formControls col-xs-8">
          <input id="password2" name="password" type="password" placeholder="密码" class="form-control">
        </div>
      </div>
      <div class="row cl form-group" style="width:100%;">
      <div class="formControls col-xs-8 col-xs-offset-3">
          <input class="input-text size-L" type="text" placeholder="验证码"  style="width:150px;" name="loginy" id="loginy" onkeyup="checkUname()">
          <img src="<%=path %>code/toCaptcha.action" onclick ="getCode()" id="imgCode"> <span id="loginstate"></span> </div>
         <!--  <div style="position:relative; height:40px;top:40px;" ><a style="position:absolute; left:180px; top:10px;" href="javascript:;" onclick="forgetpassword()" >修改密码</a></div> -->
      </div>
      
      <div class="row cl form-group" style="width:100%">
        <div class="formControls col-xs-8 col-xs-offset-3" >
         <!--  <input name="" onclick="test1()" class="btn btn-success radius size-L" value="&nbsp;登&nbsp;&nbsp;&nbsp;&nbsp;录&nbsp;"> -->
           <button type="submit" class="btn btn-primary btn-success radius size-L" style="width:150px" name="signup" id="signup" value="Sign up">登录</button>
          <button type="reset" class="btn btn-default radius size-L" style="width:150px" name="signup" value="Sign up">取消</button>
        </div>
      </div>
    </form>
  </div>
</div>  
<input type="hidden" value="${sessionScope.error }" id="error" name="error">
<script src="<%=path %>lib/jigsaw.js"></script>
<script type="text/javascript" src="<%=path %>lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="<%=path %>static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="<%=path %>lib/bootstrapValidator.js"></script>
<script type="text/javascript" src=".<%=path %>lib/bootstrap.min.js"></script>
<script type="text/javascript">

 $(function () { 
	 
	 var str = $('#error').val();
	 if(str=="false"){
		 alert("用户名或密码错误");
	 }
	 
	 
	$('#form1').bootstrapValidator({
	feedbackIcons: {
    valid: 'glyphicon glyphicon-ok',
    invalid: 'glyphicon glyphicon-remove',
    validating: 'glyphicon glyphicon-refresh'
	},
        fields: {
        	adminAccount: {
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
                validators: {
                    notEmpty: {
                        message: '密码不得为空'
                    },
                    stringLength: {
                        min: 5,
                        max: 30,
                        message: '密码长度不得小于6位或大于30位'
                    },
                    different: {
                        field: 'adminAccount',
                        message: '密码不能与账户一致'
                    }
                }
            },
           
        }
    });
	

 }); 


function getCode(){
	//得到图片对象
	
	var image = document.getElementById("imgCode");
	image.src="<%=path%>code/toCaptcha.action?" + Math.random();
	}
	
function checkUname(){
	
	$.ajax({
    url:"codeTest.action",
    data:"codename="+$("#loginy").val(),
    dataType:"text",
	type:"post",
   success:function(redata){
	 
	  
	   if(redata=="2"){
		   //2是验证码错误
		    document.getElementById('loginstate').innerHTML ="验证码错误";
		   $('#signup').attr('disabled',true);
		   
	   }else{
		   document.getElementById('loginstate').innerHTML="验证码正确"
			   $('#signup').attr('disabled',false);
	   }
   	},

	
   });
        };
</script>
</body>
</html>