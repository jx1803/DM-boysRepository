<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<!--[if lt IE 9]>
<script type="text/javascript" src="<%=path%>lib/html5shiv.js"></script>
<script type="text/javascript" src="<%=path%>lib/respond.min.js"></script>
<![endif]-->
<link rel="stylesheet" type="text/css" href="<%=path%>static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="<%=path%>static/h-ui.admin/css/style.css" />
<!--[if IE 6]>
<script type="text/javascript" src="<%=path%>lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>添加用户</title>
</head>
<body>
<div class="pd-20">
  <div class="Huiform">
    <form action="/" method="post">
      <table class="table table-bg">
        <tbody>
          <tr>
            <th width="100" class="text-r"><span class="c-red">*</span> 药品名称：</th>
            <td><input type="text" style="width:300px" class="input-text" value="" placeholder="" id="user-name" name="user-name" datatype="*2-16" nullmsg="用户名不能为空"></td>
            <th width="100" class="text-r">通用名称：</th>
            <td><input type="text" style="width:300px" class="input-text" value="" placeholder="" id="user-name" name="user-name" datatype="*2-16" nullmsg="用户名不能为空"></td>
          </tr>
          <tr>
            <th class="text-r">抗生素：</th>
            <td><label>
                <input name="sex" type="radio" id="six_1" value="1" checked>
              	 是</label>
              <label>
                <input type="radio" name="sex" value="0" id="six_0">
              	 否</label></td>
             <th width="100" class="text-r"> 拼音码：</th>
            <td><input type="text" style="width:300px" class="input-text" value="" placeholder="" id="user-name" name="user-name" datatype="*2-16" nullmsg="用户名不能为空"></td>
          </tr>
          
          <tr>
            <th class="text-r"> 单位：</th>
            <td><input type="text" style="width:300px" class="input-text" value="" placeholder="" id="user-tel" name="user-tel"></td>
             <th class="text-r">规格：</th>
            <td><input type="text" style="width:300px" class="input-text" value="" placeholder="" id="user-tel" name="user-tel"></td>
          </tr>
          <tr>
            <th class="text-r">零售价：</th>
            <td><input type="text" style="width:300px" class="input-text" value="" placeholder="" id="user-email" name="user-email"></td>
          	 <th class="text-r">加成率：</th>
            <td><input type="text" style="width:200px" class="input-text" value="" placeholder="" id="user-tel" name="user-tel"></td>
          </tr>
          <tr>
            <th class="text-r">日次数：</th>
            <td><input type="text" style="width:300px" class="input-text" value="" placeholder="" id="user-email" name="user-email"></td>
          	 <th class="text-r">次剂量：</th>
            <td><input type="text" style="width:300px" class="input-text" value="" placeholder="" id="user-tel" name="user-tel"></td>
          </tr>
          <tr>
            <th class="text-r">剂型：</th>
            <td><span class="select-box">
			  <select class="select" size="1" name="demo1" >
			    <option value="" selected>上级菜单</option>
			    <option value="1">菜单一</option>
			    <option value="2">菜单二</option>
			    <option value="3">菜单三</option>
			  </select>
			 </span></td>
          	 <th class="text-r">发票抬头：</th>
            <td><input type="text" style="width:300px" class="input-text" value="" placeholder="" id="user-tel" name="user-tel"></td>
          </tr>
          <tr>
            <th class="text-r">一级类型：</th>
            <td><span class="select-box">
			  <select class="select" size="1" name="demo1" >
			    <option value="" selected>请选择</option>
			    <option value="1">菜单一</option>
			    <option value="2">菜单二</option>
			    <option value="3">菜单三</option>
			  </select>
			 </span></td>
			  <th class="text-r">二级类型：</th>
			 <td><span class="select-box">
			  <select class="select" size="1" name="demo1" >
			    <option value="" selected>请选择</option>
			    <option value="1">菜单一</option>
			    <option value="2">菜单二</option>
			    <option value="3">菜单三</option>
			  </select>
			 </span></td>
          </tr>
          <tr>
            <th></th>
            <td><button type="submit" class="btn btn-primary size-M radius"  id="" name="" >确定</button></td>
          </tr>
        </tbody>
      </table>
    </form>
  </div>
</div>
<script type="text/javascript" src="http://cdn.bootcss.com/jquery/2.1.3/jquery.min.js"></script>
<script type="text/javascript" src="<%=path%>js/Validform_v5.3.2_min.js"></script> 
<script type="text/javascript" src="<%=path%>static/h-ui/js/H-ui.js"></script> 
<script type="text/javascript" src="<%=path%>static/h-ui.admin/js/H-ui.admin.js"></script> 
<script type="text/javascript">
$(".Huiform").Validform(); 
</script>
<script>
var _hmt = _hmt || [];
(function() {
  var hm = document.createElement("script");
  hm.src = "//hm.baidu.com/hm.js?080836300300be57b7f34f4b3e97d911";
  var s = document.getElementsByTagName("script")[0]; 
  s.parentNode.insertBefore(hm, s);
})();
var _bdhmProtocol = (("https:" == document.location.protocol) ? " https://" : " http://");
document.write(unescape("%3Cscript src='" + _bdhmProtocol + "hm.baidu.com/h.js%3F080836300300be57b7f34f4b3e97d911' type='text/javascript'%3E%3C/script%3E"));
</script>
</body>
</html>