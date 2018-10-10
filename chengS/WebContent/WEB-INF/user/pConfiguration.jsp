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
<script type="text/javascript" src="http://cdn.bootcss.com/jquery/2.1.3/jquery.min.js"></script>
<script type="text/javascript" src="<%=path%>lib/Transfer.js"></script>
<link rel="stylesheet" type="text/css" href="<%=path%>lib/style.css">
<link rel="stylesheet" type="text/css" href="<%=path%>static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="<%=path%>static/h-ui.admin/css/style.css" />

<title>角色权限配置</title>
</head>

<body>


 <div class="pd-20">
  <div class="Huiform">
    
      
      <div class="fl ty-transfer-list">
        <div class="ty-transfer-list-head">
          		角色
        </div>
      	<table>
      		
      		<c:forEach items="${ulist}" var="list">
            <tr>
            <td class="row cl"><a href="<%=path%>user/pConfiguration.action?roleid=${list.roleId }">${list.roleName }</a></td>
         	</tr>
         	</c:forEach>
      	</table>
      </div>
        <div class="ty-transfer mt20 ml20">
        <div class="fl ty-transfer-list transfer-list-left">
        <div class="ty-transfer-list-head">
          	已有权限
        </div>
          <div class="ty-transfer-list-body form-group" style="width:100%">
		<form id="form1" action="<%=path%>user/ConfigurationDel.action" method="post" >
			<c:forEach items="${rlist }" var="fathId">
					<c:if test="${fathId.pid eq 0 }">
						<dt>${fathId.permission}</dt>
						<c:forEach items="${rlist }" var="sonId">
							<c:if test="${fathId.permissionId == sonId.pid }">
								<dd class=" ty-tree-div checkbox">
									<input name="time" id="1" class="tyue-checkbox-input"  type="checkbox" value="${sonId.permissionId}" />${sonId.permission}</dd>
									<input type="hidden" name="roleId" value="${roleid }"/>
							</c:if>
						</c:forEach>
							<br/>
					</c:if>
			</c:forEach>
	</form>
	</div>
	  
    </div>


<div class="fl ty-transfer-operation">
        <span class="ty-transfer-btn-toright to-switch " onclick="configDel()"></span>
        <span class="ty-transfer-btn-toleft to-switch" onclick="configAdd()"></span>
    </div>
			<!--  </select> -->
			<div  class="fl ty-transfer-list transfer-list-right">
			<div class="ty-transfer-list-head">
            未分配权限
        </div>
        <div class="ty-transfer-list-body">
		<form id="form2" action="<%=path%>user/ConfigurationAdd.action" method="post">
				<c:forEach items="${roleother}" var="other">
					<c:if test="${other.pid eq 0 }">
						<dt class="ty-tree-select">${other.permission}</dt>
						<c:forEach items="${roleother }" var="sonother">
							<c:if test="${other.permissionId == sonother.pid }">
								<dd class="ty-tree-div">
									<input name="time1" id="1" class="transfer-all-check"  type="checkbox"
										value="${sonother.permissionId}" />${sonother.permission}</dd>
										<input type="hidden" name="roleId" value="${roleid }"/>
							</c:if>
						</c:forEach>
						<br/>
					</c:if>
				</c:forEach>
				</form>	
			</div>
	</div>
	</div>
   </div> 
 
</div>



<script type="text/javascript" src="<%=path%>static/h-ui/js/H-ui.js"></script> 
<script type="text/javascript" src="<%=path%>static/h-ui.admin/js/H-ui.admin.js"></script> 
<script type="text/javascript">
	$("#ued-transfer-1").transferItem();
</script>
<script>

//提交已有权限，相当于删除掉
function configDel(){
	var objs=document.getElementsByName('time');
	var isSel=false;//判断是否有选中项，默认为无
	for(var i=0;i<objs.length;i++){	
		if(objs[i].checked==true){
			isSel=true;
			break;
	}
	}
	if(isSel==false){
	alert("请选择权限！");
	return false;
	}else
	{
		$("#form1").submit();
	}
	
}

function configAdd(){

	var objs=document.getElementsByName('time1');
	var isSel=false;//判断是否有选中项，默认为无
	for(var i=0;i<objs.length;i++){	
		if(objs[i].checked==true){
			isSel=true;
			break;
	}
	}
	if(isSel==false){
	alert("请选择权限！");
	return false;
	}else
	{
		$("#form2").submit();
	}
	
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