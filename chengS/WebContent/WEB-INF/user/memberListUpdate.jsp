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
    <form action="<%=path %>user/memberUpdateSave.action" method="post" target="_parent">
      <table class="table table-bg">
      	
        <tbody>
          <tr>
            <th width="100" class="text-r"><span class="c-red">*</span> 用户名称：</th>
            <td><input type="text" style="width:300px" class="input-text" value="${userBean.adminName }" placeholder="" id="user-name" name="adminName" datatype="*2-16" nullmsg="用户名不能为空"></td>
           		
           <th width="100" class="text-r">角色：</th>
            <td>
            <input type="hidden" name="adminId" value="${userBean.adminId }">
            <input type="hidden" name="userid" value="${userBean.adminId }">
          	<select class="select" size="1" name="roleid">
					<c:forEach items="${rlist }" var="rolelist">
						<c:if test="${roleid == rolelist.roleId}">
							<option value="${rolelist.roleId }" selected="selected">${rolelist.roleName }</option>
						</c:if>
						
						<c:if test="${roleid != rolelist.roleId}">
							<option value="${rolelist.roleId }">${rolelist.roleName }</option>
						</c:if>
					</c:forEach>
				</select>
       
            </tr>
          <tr>
            <th class="text-r">性别：</th>
            <td>
            	<c:if test="${userBean.sex == '男' }">
            		<label>
            		<input name="sex" type="radio" id="six_1" value="男" checked>
              	男</label>
              	<label>
                <input type="radio" name="sex" value="女" id="six_0">
              	女</label>
            	</c:if>
            	
            	<c:if test="${userBean.sex == '女' }">
            		<label>
            		<input name="sex" type="radio" id="six_1" value="男" >
              	男</label>
              	<label>
                <input type="radio" name="sex" value="女" id="six_0" checked>
              	女</label>
            	</c:if>
                </td>
             <th width="100" class="text-r"> 办公电话：</th>
            <td><input type="text" style="width:300px" class="input-text" value="${userBean.officePhone}" placeholder="" id="user-name" name="officePhone" datatype="*2-16" nullmsg="用户名不能为空"></td>
          </tr>
          
          <tr>
            <th class="text-r"> 手机号码：</th>
            <td><input type="text" style="width:300px" class="input-text" value="${userBean.mobilePhone}" placeholder="" id="user-tel" name="mobilePhone"></td>
             <th class="text-r">邮箱：</th>
            <td><input type="text" style="width:300px" class="input-text" value="${userBean.email}" placeholder="" id="user-tel" name="email"></td>
          </tr>
          <tr>
            <th class="text-r">部门：</th>
            
            <td>
            <select class="select" size="1" name="deptId">
					<c:forEach items="${olist }" var="deptlist">
					<span>${userBean.deptId}</span>-----<span>${deptlist.deptId}</span>
						<c:if test="${userBean.deptId == deptlist.deptId}">
							<option value="${userBean.deptId }" selected="selected">${deptlist.dept }</option>
						</c:if>
						
						<c:if test="${userBean.deptId != deptlist.deptId}">
							<option value="${userBean.deptId }" >${deptlist.dept }</option>
						</c:if>
					</c:forEach>
				</select>
            <%-- <input type="text" style="width:300px" class="input-text" onclick="dept()" value="${userBean.deptbean.dept}" placeholder="" id="user-tel" name="dept">
            	<input type="hidden" name="deptId" value=""> --%>
            </td>
             <th class="text-r">地址：</th>
            <td><input type="text" style="width:300px" class="input-text" value="${userBean.address}" placeholder="" id="user-tel" name="address"></td>
          </tr>
          <tr>
            <th class="text-r">学历：</th>
            <td><input type="text" style="width:300px" class="input-text" value="${userBean.education}" placeholder="" id="user-email" name="education"></td>
          	<th class="text-r">身份证：</th>
            <td><input type="text" style="width:300px" class="input-text" value="${userBean.idcard}" placeholder="" id="user-tel" name="idcard"></td>
          </tr>
          <tr>
            <th class="text-r">入职日期：</th>
            <td><input type="text" style="width:300px" class="input-text" value="${userBean.entryDate}" placeholder="" id="user-email" name="entryDate"></td>
          	 <th class="text-r">生日：</th>
            <td><input type="text" style="width:300px" class="input-text" value="${userBean.birthDate}" placeholder="" id="user-tel" name="birthDate"></td>
          </tr>
          
          
          <tr>
           <th class="text-r">离职日期：</th>
            <td><input type="text" style="width:300px" class="input-text" value="${userBean.quitDate}" placeholder="" id="user-tel" name="quitDate"></td>
            <th></th>
            <td><button type="submit" class="btn btn-primary size-M radius"  id="" name="" >确定</button></td>
          </tr>
        </tbody>
      </table>
    </form>
  </div>
</div>
<script type="text/javascript" src="<%=path%>js/Validform_v5.3.2_min.js"></script> 
<script type="text/javascript" src="<%=path%>static/h-ui/js/H-ui.js"></script> 
<script type="text/javascript" src="<%=path%>static/h-ui.admin/js/H-ui.admin.js"></script> 
<!-- <script type="text/javascript">
$(".Huiform").Validform(); 
</script> -->
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