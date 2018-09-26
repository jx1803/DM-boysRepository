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
			<td colspan="8">药品报损</td>
		</tr>
	</table>
	
	<table class="table table-border table-bordered table-bg" id="tblSelect">
			<tr><input type="hidden" id="batchDetailId" >
				<td  width="100">报损药品名称:</td>
				<td width="100"><input class="form-control" id="drugName" readonly="readonly"><button type="button" class="btn btn-default" id=""
					onclick="selectDrug('查询药品','selectPutDrug.action','800','500')">查询</button></td>
				<td width="100">药品编号：</td>
				<td width="100"> <input class="form-control" id="drugId" readonly="readonly"></td>
				<td width="100">报损药品厂家:</td>
				<td width="100"><input class="form-control" id="drugManu" readonly="readonly"></td>
				<td width="100">报损药品产品批号:</td>
				<td width="100"><input class="form-control" id="manuBatch" readonly="readonly"></td>
				<td width="100">报损药品产品批号:</td>
				<td width="100"><input class="form-control" id="" readonly="readonly"></td>
				
			</tr>
			<tr>
			<td width="100">药品剩余数量:</td>
				<td width="100"><input class="form-control" id="drugNum" readonly="readonly"></td>
			<td width="100">药品进价:</td>
				<td width="100"><input class="form-control" id="purPrice" readonly="readonly"></td>
				<td width="100">报损数量:</td>
				<td width="100"><input class="form-control" id="applyNum" onkeyup="this.value=this.value.replace(/\D/g,''),setSales()" onafterpaste="this.value=this.value.replace(/\D/g,'')" ></td>
				<td width="100">报损药品金额(元):</td>
				<td width="100"><input class="form-control" id="applyMoney" readonly="readonly" ></td>
				<th width="90">报损原因:</th>
				<td width="100"><input class="form-control" id="applyReason" placeholer="输入报损原因"></td>
			</tr>
	</table>
	<div style="float:right;"><button type="button" class="btn btn-default" id=""
					onclick="addApply()">添加</button>&nbsp;&nbsp;
					<button type="button" class="btn btn-primary" id=""
					onclick="empty()">清空</button>&nbsp;&nbsp;
					<button type="button" class="btn btn-default" id=""
					onclick="subApply()">提交申请</button></div>
	<form id="fapply" action="breakApply.action"  method="post">
	
	</form>
	
	<table class="table table-border table-bordered table-bg" id="tblDrugList">
		<thead>
			
			<tr class="text-c">
				<th width="100">报损药品名称</th>
				<th width="90">报损药品厂家</th>
				<th width="80">报损数量</th>
				<th width="100">报损药品进价</th>
				<th width="90">报损药品产品批号</th>
				<th width="90">报损药品金额(元)</th>
				<th width="90">报损原因</th>
			</tr>
		</thead>
		<tbody id="tbodyId"></tbody>
	</table>
	
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
	function showModal(){
		
		$("#drugModal").modal("show");
	}
	
	function getDrug(){
		$("#drugName").val($("#drugName1").val());
	}

	/*  增加药品报损记录*/
	function addApply(){
		
		var drugName=$("#drugName").val();
		var drugmanu=$("#drugManu").val();
		var purPrice=$("#purPrice").val();
		var manuBatch=$("#manuBatch").val();
		var applyNum=$("#applyNum").val();
		var applyMoney=$("#applyMoney").val();
		var applyReason=$("#applyReason").val();
		var drugId=$("#drugId").val();
		var batchDetailId=$("#batchDetailId").val();
		if(drugName=="" || applyNum=="" || applyReason=="" ){
			alert("请完善报损药品信息");
			return;
		}
		var input="<input type='hidden' name='applyList["+count+"].drugId' value='"+drugId+
		"'><input type='hidden' name='applyList["+count+"].stoDrugBean.drugName' value='"+drugName+
		"'><input type='hidden' name='applyList["+count+"].stoDrugBean.drugmanu' value='"+drugmanu+
		"'><input type='hidden' name='applyList["+count+"].bdBean.purPrice' value='"+purPrice+
		"'><input type='hidden' name='applyList["+count+"].manuBatch' value='"+manuBatch+
		"'><input type='hidden' name='applyList["+count+"].bdBean.batchDetailId' value='"+batchDetailId+
		"'><input type='hidden' name='applyList["+count+"].applyNum' value='"+applyNum+
		"'><input type='hidden' name='applyList["+count+"].applyMoney' value='"+applyMoney+
		"'><input type='hidden' name='applyList["+count+"].applyReason' value='"+applyReason+
		"'><input type='hidden' name='applyList["+count+"].applyTypeId' value='12'><input type='hidden' name='applyList["+count+"].checkId' value='7'>";
		$("#fapply").append(input);
		count+=1;
		
	
		 var str="<tr id='tr"+count+"' class='text-c'><td>"+drugName+"</td><td>"+drugmanu+"</td><td>"+applyNum+"</td><td>"+
		 purPrice+"</td><td>"+manuBatch+"</td><td>"+applyMoney+"</td><td>"+applyReason+"</td></tr>";
		
		  $("#tblDrugList").append(str); 
	}
	
	
	/* 清空申请记录 */
	function empty(){
		$("#tbodyId").html("");
		$("#fapply").html("");
		count=0;
	}
	
	/* 提交申请 */
	function subApply(){
		var d=$("#tbodyId").html();
		if(d.length==0){
			
		alert("请选择报损药品");
		return;
		}
		var r=confirm("确定提交吗?");
		if(r){
			$("#fapply").submit();
		}
	}
	
  
	
	/* 计算合计金额 */
  	function setSales(){
  		var drugName=$("#drugName").val();
  		if(drugName==""){
  			alert("请先选择药品");
  			$("#applyNum").val("");
  			return;
  		}
  		var m=$("#purPrice").val();
  		var num=$("#applyNum").val();
  		var am=m*num;
  		$("#applyMoney").val(am);
	}
	/*
	参数解释：
	title	标题
	url		请求的url
	id		需要操作的数据id
	w		弹出层宽度（缺省调默认值）
	h		弹出层高度（缺省调默认值）
*/
/*查询药品出入库记录*/
function selectDrug(title,url,w,h){
	layer_show(title,url,w,h);
}

</script>
</body>
</html>