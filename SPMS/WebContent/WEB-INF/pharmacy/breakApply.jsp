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
<title>报损申请</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 药品报损 <span class="c-gray en">&gt;</span> 管理员列表 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
	<table>
		<tr>
			<td>报损药品名称:</td>
			<td><input class="form-control" id="drugName" placeholder="输入药品名"></td>
		</tr>
	</table>
	
	<table class="table table-border table-bordered table-bg">
			<tr>
				<td>报损药品名称:</td>
				<td width="100"><input class="form-control" id="drugName" readonly="readonly"></td>
				<td>报损药品厂家:</td>
				<td width="100"><input class="form-control" id="drugManu" readonly="readonly"></td>
				<td>报损药品厂家批号:</td>
				<td width="100"><input class="form-control" id="proPlace" readonly="readonly"></td>
			</tr>
			<tr>
				<td>报损数量:</td>
				<td width="100"><input class="form-control" id="applyNum" ></td>
				<td>报损药品金额(元):</td>
				<td width="100"><input class="form-control" id="applyMoney" readonly="readonly" ></td>
				<th width="90">报损原因</th>
				<td width="100"><input class="form-control" id="applyReason" placeholer="输入报损原因"></td>
			</tr>
			
		
	
		
	</table>
</div>
<button type="button" class="btn btn-info" id=""
					onclick="showModal()">按钮</button>

<!-- 文档上传模态框 -->
	 <div class="modal fade" id="uploadModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title" id="">我要上传</h4>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<form action="${pageContext.request.contextPath}/front/upload.action" method="post" enctype="multipart/form-data">
					<div class="modal-body" >
					<input type="hidden" name="dom.userId" value="${userInfo.userId }">
					<table border="0" style="heigth:300px">
					<tr>
						<td width="170px" align="center">文档标题：</td>
						<td><input type="text" class="form-control" name="dom.domName"></td>
					</tr>
						<tr align="center">
						<td width="170px" >文档简介：</td>
						<td><textarea name="dom.domInfo" 
							cols="50" rows="5"></textarea></td>
					</tr>
					</tr>
						<tr>
						<td width="170px" align="center">选择上传文档：</td>
						<td>	<input type="file" name="myfile">
							</td>
					</tr>
						<tr>
						<td width="170px" align="center">设置下载积分：</td>
						<td>	<input type="text" name="dom.integral">
							</td>
					</tr>
				
					 </table>
					</div>

					<div class="modal-footer">
						<button type="submit" class="btn btn-primary" id="btupload">上传</button>
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				</form>
			</div>
		</div>
	</div>

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
	function showModal(){
		$("#uploadModal").modal("show");
	}



</script>
</body>
</html>