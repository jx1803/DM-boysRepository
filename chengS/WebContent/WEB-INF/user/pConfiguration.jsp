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
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />

<link rel="stylesheet" type="text/css" href="<%=path%>static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="<%=path%>static/h-ui.admin/css/style.css" />

<title>添加用户</title>
</head>
<body>
<div class="pd-20">
  <div class="Huiform">
    
      
      <div>
      	<table>
      		<tr><td>角色</td> </tr>
      		<c:forEach items="${ulist}" var="list">
            <tr>
            <td><a href="<%=path%>user/pConfiguration.action?roleid=${list.roleId }">${list.roleName }</a></td>
         	</tr>
         	</c:forEach>
      	</table>
      </div>
     
        <div style="position: absolute; top: 0; left: 230px; width: 400px;">
		<form id="form1" action="<%=path%>user/ConfigurationDel.action" method="post" >
			<c:forEach items="${rlist }" var="fathId">
			
				<dl>
					<c:if test="${fathId.pid eq 0 }">
						<dt>${fathId.permission}</dt>
						<c:forEach items="${rlist }" var="sonId">
							<c:if test="${fathId.permissionId == sonId.pid }">
								<dd>
									<input name="time" id="1" class="time" type="checkbox"
										value="${sonId.permissionId}" />${sonId.permission}</dd>
									<input type="hidden" name="roleId" value="${roleid }"/>
							</c:if>
						</c:forEach>
							<br/>
					</c:if>
				</dl>
			</c:forEach>
</form>

			<!--  </select> -->
			<div style="position: absolute; top: 0; left: 270px; width: 400px;">
	<form id="form2" action="<%=path%>user/ConfigurationAdd.action" method="post">
				<c:forEach items="${roleother}" var="other">
					<c:if test="${other.pid eq 0 }">
						<dt>${other.permission}</dt>
						<c:forEach items="${roleother }" var="sonother">
							<c:if test="${other.permissionId == sonother.pid }">
								<dd>
									<input name="time" id="1" class="time" type="checkbox"
										value="${sonother.permissionId}" />${sonother.permission}</dd>
										<input type="hidden" name="roleId" value="${roleid }"/>
							</c:if>
						</c:forEach>
						<br/>
					</c:if>
				</c:forEach>
				</form>	
			</div>
		
			<div style="position: absolute; top: 0; left: 220px; width: 40px; height: 400px; border: 1px solid;">
				<button type="button" id="right_one" onclick="configDel()" style="display: block;">
					<input type="button" id="left_all" value="-&gt;"/>
				</button> 
				<button id="left_one" onclick="configAdd()" style="display: block">
					<input type="button" id="left_all" value="&lt-">
				</button>
			</div>
		
	</div>
    
 
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

//提交已有权限，相当于删除掉
function configDel(){
	alert("进来了");
	//把form1提交给后台
	$("#form1").submit();
}

function configAdd(){
	alert("进来要增加");
	$("#form2").submit();
}
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