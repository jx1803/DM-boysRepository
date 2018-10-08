<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
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

<link rel="stylesheet" type="text/css" href="<%=path%>static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="<%=path%>static/h-ui.admin/css/style.css" />

<title>Insert title here</title>
</head>
<body>
<div class="pd-20">
  <div class="Huiform">
  <article class="page-container">
   <form action="<%=path %>user/paramSave.action" method="post" target="_parent">
      <div class="row cl" style="width:470px;margin:0 auto;">
			<label class="form-label col-xs-2 col-sm-3" style="width:120px"><span class="c-red">*</span>一级参数名：</label>
			<div class="formControls col-xs-4 col-sm-6">
				<input type="text"  class="input-text"  value="${pbean.fname }"  id="fname" name="fname">
				<input type="hidden" value="${pbean.paramId }" name="paramId" >
			</div>
		</div>
		<div class="row cl" style="width:470px;margin:0 auto;">
			<label class="form-label col-xs-2 col-sm-3" style="width:120px"><span class="c-red">*</span>二级参数名：</label>
			<div class="formControls col-xs-4 col-sm-6">
				<input type="text" class="input-text"  value="${pbean.param }"  id="param" name="param">
				<input type="hidden" value="${pbean.pid }" name="pid" >
			</div>
		</div>

		<div class="row cl">
			<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
				<input class="btn btn-primary radius" style="margin:25px 25px 25px 0px;" type="submit" value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
			</div>
		</div>    </form>
    </article>
  </div>
</div>

</body>
</html>