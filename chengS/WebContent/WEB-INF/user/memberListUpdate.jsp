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

<link rel="stylesheet" type="text/css" href="<%=path%>static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="<%=path%>static/h-ui.admin/css/style.css" />

<title>用户修改</title>
</head>
<body>
<div class="pd-20">
  <div class="Huiform">
    <form action="/" method="post">
      <table class="table table-bg">
        <tbody>
          <tr>
            <th width="100" class="text-r"><span class="c-red">*</span> 用户名称：</th>
            <td><input type="text" style="width:300px" class="input-text" value="" placeholder="" id="user-name" name="user-name" datatype="*2-16" nullmsg="用户名不能为空"></td>
            <th width="100" class="text-r">密码：</th>
            <td><input type="text" style="width:300px" class="input-text" value="" placeholder="" id="user-name" name="user-name" datatype="*2-16" nullmsg="用户名不能为空"></td>
            </tr>
          <tr>
            <th class="text-r">性别：</th>
            <td><label>
                <input name="sex" type="radio" id="six_1" value="1" checked>
              	男</label>
              <label>
                <input type="radio" name="sex" value="0" id="six_0">
              	女</label></td>
             <th width="100" class="text-r"> 办公电话：</th>
            <td><input type="text" style="width:300px" class="input-text" value="" placeholder="" id="user-name" name="user-name" datatype="*2-16" nullmsg="用户名不能为空"></td>
          </tr>
          
          <tr>
            <th class="text-r"> 手机号码：</th>
            <td><input type="text" style="width:300px" class="input-text" value="" placeholder="" id="user-tel" name="user-tel"></td>
             <th class="text-r">邮箱：</th>
            <td><input type="text" style="width:300px" class="input-text" value="" placeholder="" id="user-tel" name="user-tel"></td>
          </tr>
          <tr>
            <th class="text-r">学历：</th>
            <td><input type="text" style="width:300px" class="input-text" value="" placeholder="" id="user-email" name="user-email"></td>
          	 <th class="text-r">身份证：</th>
            <td><input type="text" style="width:200px" class="input-text" value="" placeholder="" id="user-tel" name="user-tel"></td>
          </tr>
          <tr>
            <th class="text-r">入职日期：</th>
            <td><input type="text" style="width:300px" class="input-text" value="" placeholder="" id="user-email" name="user-email"></td>
          	 <th class="text-r">生日：</th>
            <td><input type="text" style="width:300px" class="input-text" value="" placeholder="" id="user-tel" name="user-tel"></td>
          </tr>
          
          
          <tr>
           <th width="100" class="text-r">角色：</th>
            <td><input type="text" style="width:300px" class="input-text" value="" placeholder="" id="user-name" name="user-name" datatype="*2-16" nullmsg="用户名不能为空"></td>
       
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