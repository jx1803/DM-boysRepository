<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link rel="Bookmark" href="/favicon.ico" >
<link rel="Shortcut Icon" href="/favicon.ico" />
<!--[if lt IE 9]>
<script type="text/javascript" src="lib/html5shiv.js"></script>
<script type="text/javascript" src="lib/respond.min.js"></script>
<![endif]-->
<link rel="stylesheet" type="text/css" href="../static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="../static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="../lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css" href="../static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="../static/h-ui.admin/css/style.css" />
<!--[if IE 6]>
<script type="text/javascript" src="lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>修改价格</title>
</head>
<body>

<form action="adjustPrice.action" method="post" target="_parent" id="mform">

<table border="0" style="heigth:300px">
<input type="hidden" value="${maximum }" id="maximum">
<input type="hidden" name="drugId" value="${drugId }">
<input type="hidden" name="adminId" value="1001">
	<tr>
		<td width="170px" align="center">原零售价（元）：</td>
		<td><input type="text" class="form-control" name="beforeAdjust" value="${beforeAdjust }" id="beforePrice" readonly="readonly" ></td>
	</tr>
	<tr><td width="170" align="center">修改为  </td>
	</tr>	
	<tr>
		<td width="170px" align="center">修改后零售价（元）：</td>
		<td><input type="text" class="form-control" name="afterAdjust"  id="afterPrice" placeholder="输入修改价格" ></td>
	</tr>
					
				
 </table><br><br>
 <div class="text-c">
 	<button class="btn btn-default" type="button" onclick="checkSub()" >确定</button>&nbsp;&nbsp;&nbsp;&nbsp;
 	<button class="btn btn-default" type="button" onclick="cancle()" >取消</button>
  </div>
</form>



<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="../lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="../lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="../static/h-ui/js/H-ui.min.js"></script> 
<script type="text/javascript" src="../static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="../lib/My97DatePicker/4.8/WdatePicker.js"></script> 
<script type="text/javascript" src="../lib/datatables/1.10.0/jquery.dataTables.min.js"></script> 
<script type="text/javascript" src="../lib/laypage/1.2/laypage.js"></script>
<script type="text/javascript">
	function checkSub(){
		var afterPrice=$("#afterPrice").val();
		var maximum=$("#maximum").val();
		
		 if(afterPrice.length==0){
			alert("请输入调整价格");
			return;
		}else if(Number(afterPrice)>Number(maximum)){
			alert("调整价格超出国家表准")
		} else{
			var r=confirm("确定要提交吗?");
			if(r==true){
				$("#mform").submit();
			}
		}
		
	}

	function cancle(){
		var index=parent.layer.getFrameIndex(window.name);
		parent.layer.close(index);
	}
	
</script>
</body>
</html>