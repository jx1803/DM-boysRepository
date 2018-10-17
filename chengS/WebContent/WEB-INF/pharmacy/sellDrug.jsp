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
<title>药品发药</title>

</head>
<body>

<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 药品日常工作 <span class="c-gray en">&gt;</span> 药品发药 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>

	<table>
		<tr>
			<td colspan="8">药品发药</td>
		</tr>
	</table>
	
	<table class="table table-border table-bordered table-bg" id="tblSelect">
			<tr><input type="hidden" id="putBatch" >
				<td  width="100">药品名称:</td>
				<td width="100"><input class="form-control" style="width:74px" id="drugName" placeholder="点击查询按钮" readonly="readonly"><button type="button" class="btn btn-default" id=""
					onclick="selectDrug('查询药品','sellDrugLayer.action','800','500')">查询</button></td>
				<td width="100">药品编号：</td>
				<td width="100"> <input class="form-control" id="drugId" readonly="readonly"></td>
				<td width="100">规格：</td>
				<td width="100"> <input class="form-control" id="specific" readonly="readonly"></td>
				<td width="50">单位:</td>
				<td width="100"><input class="form-control" id="unit" readonly="readonly"></td>
				<td width="100">剩余数量:</td>
				<td width="100"><input class="form-control" id="drugNum" readonly="readonly" style="width:80px;"><span id="sUnit"></span></td>
				
			</tr>
			<tr>
			<td width="100">药品厂家:</td>
				<td width="100"><input class="form-control"  id="drugManu" readonly="readonly"></td>
				<td width="100">药品产品编号:</td>
				<td width="100"><input class="form-control"  id="manuBatch" readonly="readonly"></td>
				<td width="100">零售价（元）:</td>
				<td width="100"><input class="form-control" id="retailPrice" readonly="readonly"></td>
				<td width="50">数量:</td>
				<td width="100"><input style="width:100px" class="form-control" id="sellNum" placeholder="请输入发药数量" oninput="setSales()" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" ><span id="sunit"></span>
				<input style="width:100px" class="form-control" id="sellNum1" placeholder="请输入发药数量" oninput="setSales()" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" ><span id="sspecific"></span>
				</td>
				<td width="100">合计金额(元):</td>
				<td width="100"><input class="form-control" id="sales" readonly="readonly" style="width:100px;"></td>
				
			</tr>
	</table>
	<div style="float:right;"><button type="button" class="btn btn-default" id=""
					onclick="addApply()">添加</button>&nbsp;&nbsp;
					<button type="button" class="btn btn-primary" id=""
					onclick="empty()">清空</button>&nbsp;&nbsp;
					<button type="button" class="btn btn-default" id=""
					onclick="subApply()">发药</button></div>
	
	<form id="fapply" action="sellDrug.action"  method="post">
	
	</form>
	
	<table class="table table-border table-bordered table-bg" id="tblDrugList">
		<thead>
			
			<tr class="text-c">
				<th width="100">药品编号</th>
				<th width="90">药品名称</th>
				<th width="80">规格</th>
				<th width="100">单位</th>
				<th width="90">药品厂家</th>
				<th width="90">产品批号</th>
				<th width="90">零售价(元)</th>
				<th width="90">数量</th>
				<th width="90">合计金额（元）</th>
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
widow.top.location.reload();
</script>

<script type="text/javascript">
	  


	var count=0;
	
	function getDrug(){
		$("#drugName").val($("#drugName1").val());
	}

	/*  增加药品发药记录*/
	function addApply(){
		
		var retailPrice=$("#retailPrice").val();
		var specific=$("#specific").val();
		var str=specific.split('-');
		
		var unit=$("#unit").val();
		var drugName=$("#drugName").val();
		var drugmanu=$("#drugManu").val();
		var drugId=$("#drugId").val();
		var sellNum1=$("#sellNum").val()*str[0];
		var sellNum2=$("#sellNum1").val()*1;
		var sellNum=sellNum1+sellNum2;
		var sales=$("#sales").val();
		var manuBatch=$("#manuBatch").val();
		var putBatch=$("#putBatch").val();
		var mid=$("#"+manuBatch).val();
		if(typeof(mid)!="undefined"){
			alert("该药品已添加,请重新选择");
			return;
		}
		
		if(drugName=="" || sellNum==""  ){
			alert("请完善药品信息");
			return;
		}
		var input="<input id='"+manuBatch+"' type='hidden' name='drugList["+count+"].drugName' value='"+drugName+
		"'><input type='hidden' name='drugList["+count+"].drugmanu' value='"+drugmanu+
		"'><input type='hidden' name='drugList["+count+"].retailPrice' value='"+retailPrice+
		"'><input type='hidden' name='drugList["+count+"].specific' value='"+specific+
		"'><input type='hidden' name='drugList["+count+"].unit' value='"+unit+
		"'><input type='hidden' name='drugList["+count+"].sellBean.drugId' value='"+drugId+
		"'><input type='hidden' name='drugList["+count+"].sellBean.sellNum' value='"+sellNum+
		"'><input type='hidden' name='drugList["+count+"].sellBean.sales' value='"+sales+
		"'><input type='hidden' name='drugList["+count+"].sellBean.manuBatch' value='"+manuBatch+
		"'><input type='hidden' name='drugList["+count+"].sellBean.putBatch' value='"+putBatch+
		"'>";
		
		
		
		$("#fapply").append(input);
		count+=1;
		
	
		 var str="<tr id='tr"+count+"' class='text-c'><td>"+drugId+"</td><td>"+drugName+"</td><td>"+specific+"</td><td>"+
		 unit+"</td><td>"+drugmanu+"</td><td>"+manuBatch+"</td><td>"+retailPrice+
		 "</td><td>"+sellNum+"</td><td>"+sales+"</td></tr> ";
		 
		  $("#tblDrugList").append(str); 
		  $("#tblSelect"+" :input").each(function(){
			  $(this).val("");
		  })
	}
	
	/* 清空申请记录 */
	function empty(){
		var r=confirm("确定要清空吗？");
		if(r){
		$("#tbodyId").html("");
		$("#fapply").html("");
		count=0;
			
		}
	}
	
	/* 提交申请 */
	function subApply(){
		var d=$("#tbodyId").html();
		if(d.length==0){
			
		alert("请选择药品");
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
  			$("#sellNum").val("");
  			$("#sellNum1").val("");
  			return;
  		}
  		
  		
  		var m=$("#retailPrice").val();
  		var specific=$("#specific").val();
		var str=specific.split('-');
  		var num=$("#drugNum").val();
		var snum=$("#sellNum").val()*str[0]+$("#sellNum1").val()*1;
		var smoney=m/str[0];
  		if(snum>num){
  			alert("超出药品剩余量");
  			$("#sellNum").val("");
  			$("#sellNum1").val("");
  			return;
  		}
  		var am=smoney*snum;
  		
  		$("#sales").val(am);
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