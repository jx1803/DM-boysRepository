<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />

<link rel="stylesheet" type="text/css"
	href="<%=path%>static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css"
	href="<%=path%>static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css"
	href="<%=path%>lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css"
	href="<%=path%>static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css"
	href="<%=path%>static/h-ui.admin/css/style.css" />
<!--[if IE 6]>
<script type="text/javascript" src="lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>退还厂家申请</title>
<link rel="stylesheet"
	href="<%=path%>lib/zTree/v3/css/zTreeStyle/zTreeStyle.css"
	type="text/css">
</head>
<body class="pos-r">

	<div style="margin-left: 0px;">
		<nav class="breadcrumb">
			<i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span>
			药库工作 <span class="c-gray en">&gt;</span>退还厂家<a
				class="btn btn-success radius r"
				style="line-height: 1.6em; margin-top: 3px"
				href="javascript:location.replace(location.href);" title="刷新"><i
				class="Hui-iconfont">&#xe68f;</i></a>
		</nav>
		<div class="page-container">
			<div class="text-c">
				药品编码：<input type="text" name="drugId" id="drugId" placeholder="药品编码"
					style="width: 150px" class="input-text" readonly="readonly">&nbsp;<a
					href="javascript:;"
					onclick="drug_select('药品选择','<%=path%>storage/drugSelectShow.action','','510')"
					class="btn btn-primary btn-sm">F1</a> &emsp;药品名称：<input type="text"
					name="drugName" id="drugName" placeholder="药品名称"
					style="width: 150px" class="input-text" readonly="readonly" />
				&emsp;单位：<input type="text" name="unit" id="unit" placeholder="药品单位"
					style="width: 150px" class="input-text" readonly="readonly" />
				&emsp;规格：<input type="text" name="specific" id="specific"
					placeholder="药品规格" style="width: 150px" class="input-text"
					readonly="readonly" /> </br>生产厂商：<input type="text" name="drugmanu"
					id="drugmanu" placeholder="生产厂商" style="width: 150px"
					class="input-text" readonly="readonly" /> &emsp;药品产地：<input
					type="text" name="proPlace" id="proPlace" placeholder="药品产地"
					style="width: 150px" class="input-text" readonly="readonly" />
				&emsp;申请人：<input type="text" name="adminName" id="adminName"
					style="width: 150px; margin-top: 5px" class="input-text"
					value="${sessionScope.USER.adminName}" readonly="readonly" />
				&emsp;数量：<input type="text" name="applyNum" id="applyNum"
					placeholder="请输入申请数量" style="width: 150px; margin-top: 5px"
					class="input-text"
					onkeyup="this.value=this.value.replace(/\D/g,'')"
					onafterpaste="this.value=this.value.replace(/\D/g,'')" /> <input
					type="hidden" name="inventoryNum" id="inventoryNum" /> <input
					type="hidden" name="maximum" id="maximum" /> <label id="hint"></label>
				</br>申请理由：<input type="text" name="applyReason" id="applyReason"
					placeholder="请输入申请理由" style="width: 250px; margin-top: 5px"
					class="input-text" />
					<!-- 添加到列表的记录数 -->
					<input type="hidden" id="total"/>
				<button type="button" class="btn btn-primary" id=""
					onclick="addApply()">添加</button>
				&nbsp;&nbsp;
				<button type="button" class="btn btn-primary" id=""
					onclick="empty()">清空</button>
				&nbsp;&nbsp;
				<button type="button" class="btn btn-primary" id=""
					onclick="subApply()">提交申请</button>
				</br>
			</div>

			<form id="papply" action="purchaseApply.action" method="post">
			</form>

			<div class="cl pd-5 bg-1 bk-gray mt-20">
				<span class="r">共有数据：<strong></strong> 条
				</span>
			</div>
			<form id="apply" action="" method="">
				<div class="mt-20">
					<table class="table table-border table-bordered table-bg"
						id="tblDrugList">
						<thead>
							<tr class="text-c">
								<th width="80">药品编码</th>
								<th width="100">药品名称</th>
								<th width="60">申请数量</th>
								<th width="60">单位</th>
								<th width="100">生产厂商</th>
								<th width="100">药品产地</th>
								<th width="100">申请理由</th>
								<th width="60">申请人</th>
							</tr>
						</thead>
						<tbody id="tbodyId">
						</tbody>
					</table>
				</div>
			</form>
		</div>
	</div>

	<!--_footer 作为公共模版分离出去-->
	<script type="text/javascript"
		src="<%=path%>lib/jquery/1.9.1/jquery.min.js"></script>
	<script type="text/javascript" src="<%=path%>lib/layer/2.4/layer.js"></script>
	<script type="text/javascript"
		src="<%=path%>static/h-ui/js/H-ui.min.js"></script>
	<script type="text/javascript"
		src="<%=path%>static/h-ui.admin/js/H-ui.admin.js"></script>
	<!--/_footer 作为公共模版分离出去-->

	<!--请在下方写此页面业务相关的脚本-->
	<script type="text/javascript"
		src="<%=path%>lib/zTree/v3/js/jquery.ztree.all-3.5.min.js"></script>
	<script type="text/javascript"
		src="<%=path%>lib/My97DatePicker/4.8/WdatePicker.js"></script>
	<script type="text/javascript"
		src="<%=path%>lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
	<script type="text/javascript"
		src="<%=path%>lib/laypage/1.2/laypage.js"></script>
	<script type="text/javascript">
/*药品-选择*/
function drug_select(title,url,w,h){
	layer_show(title,url,w,h);
}
	var count=0;
	var arr=new Array();
	var i=100;
	var p=100;
	for(var k=0;k<i;k++){
		arr[k]=new Array(k);
		for(var j=0;j<p;j++){
			arr[k][j]="";
		}
	}
	
	/*  增加药品采购申请记录*/
	function addApply(){
		
		var drugId=$("#drugId").val();
		var drugName=$("#drugName").val();
		var applyNum=$("#applyNum").val();
		var applyReason=$("#applyReason").val();
		var adminName=$("#adminName").val();
		var unit=$("#unit").val();
		var drugmanu=$("#drugmanu").val();
		var proPlace=$("#proPlace").val();
		var inventoryNum=$("#inventoryNum").val();
		var maximum =$("#maximum").val();
		var d=$("#"+drugId).val();
		if(applyNum=="" || applyReason=="" ){
			alert("请完善药品采购申请信息");
			return;
		}
		/* 判断输入的数量是否超出所需可存储量 */
		var stoage= (maximum -inventoryNum-applyNum);
		if(stoage<0){
			$("#hint").html("超过当前储存量");
			$("#hint").css("color","red");	
			return;
		}else if(typeof(d)=="undefined"){
		$("#hint").html("");
		var input="<input type='hidden'id='"+drugId+"' name='applyList["+count+"].drugId' value='"+drugId+
		"'><input type='hidden' name='applyList["+count+"].stoDrugBean.drugName' value='"+drugName+
		"'><input type='hidden' name='applyList["+count+"].applyNum' value='"+applyNum+
		"'><input type='hidden' name='applyList["+count+"].stoDrugBean.unit' value='"+unit+
		"'><input type='hidden' name='applyList["+count+"].stoDrugBean.drugmanu' value='"+drugmanu+
		"'><input type='hidden' name='applyList["+count+"].stoDrugBean.proPlace' value='"+proPlace+
		"'><input type='hidden' name='applyList["+count+"].applyReason' value='"+applyReason+
		"'><input type='hidden' name='applyList["+count+"].adminName' value='"+adminName+
		"'>";
		$("#papply").append(input);
		count+=1;
	   // $("#total").val(count+=1);
		
		 var str="<tr id='tr"+count+"' class='text-c'><td>"+drugId+"</td><td>"+drugName+"</td><td>"+applyNum+"</td><td>"+unit+"</td><td>"+drugmanu+"</td><td>"+proPlace+"</td><td>"+applyReason+"</td><td>"+adminName+
		 "</td></tr> ";
		 
		 $("#tblDrugList").append(str); 
		}else{
			alert("该药品已经添加，如需修改数量请清空重新选择");
			return;
		}
	}
	
	/* 清空申请记录 */
	function empty(){
		$("#tbodyId").html("");
		$("#papply").html("");
		count=0;
		//$("#total").val(count)
	}
	
	/* 提交申请 */
	function subApply(){
		var r=confirm("确定提交吗?");
		if(r){
			$("#papply").submit();
		}
	}

</script>
</body>
</html>