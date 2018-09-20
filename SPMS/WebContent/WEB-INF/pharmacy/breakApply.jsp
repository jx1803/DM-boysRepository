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
			<tr>
				<td  width="100">报损药品名称:</td>
				<td width="100"><input class="form-control" id="drugName" readonly="readonly"><button type="button" class="btn btn-default" id=""
					onclick="showModal()">查询</button></td>
				<td width="100">报损药品批次：</td>
				<td width="100"> <input class="form-control" id="drugBatch" readonly="readonly"></td>
				<td width="100">报损药品厂家:</td>
				<td width="100"><input class="form-control" id="drugManu" readonly="readonly"></td>
				<td width="100">报损药品厂家批号:</td>
				<td width="100"><input class="form-control" id="manuBatch" readonly="readonly"></td>
			</tr>
			<tr>
				<td width="100">报损数量:</td>
				<td width="100"><input class="form-control" id="applyNum" ></td>
				<td width="100">报损药品金额(元):</td>
				<td width="100"><input class="form-control" id="applyMoney" readonly="readonly" ></td>
				<th width="90">报损原因:</th>
				<td width="100"><input class="form-control" id="applyReason" placeholer="输入报损原因"></td>
				<td colspan="2"><button type="button" class="btn btn-default" id=""
					onclick="addApply()">添加</button></td>
			</tr>
			
		
	
		
	</table>
	
	<table class="table table-border table-bordered table-bg" id="tblDrugList">
		<thead>
			
			<tr class="text-c">
				<th width="100">报损药品名称</th>
				<th width="90">报损药品厂家</th>
				<th width="80">报损数量</th>
				<th width="100">报损药品批次</th>
				<th width="90">报损药品厂家批号</th>
				<th width="90">报损药品金额(元)</th>
				<th width="150">报损申请人</th>
				<th width="90">报损原因</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${arr }" var="breakApply">
			<tr class="text-c">
			<td><input name="applyList[${count }].stoDrugBean.drugName" value=""${breakApply[0] } style="border-style: none"></td>
			<td><input name="applyList[${count }].bdBean.drugmanu" value=""${breakApply[1] } style="border-style: none"></td>
			<td><input name="applyList[${count }].bdBean.drugBatch" value=""${breakApply[2] } style="border-style: none"></td>
			<td><input name="applyList[${count }].bdBean.manuBatch" value=""${breakApply[3] } style="border-style: none"></td>
			<td><input name="applyList[${count }].applyNum" value=""${breakApply[4] } style="border-style: none"></td>
			<td><input name="applyList[${count }].applyMoney" value=""${breakApply[5] } style="border-style: none"></td>
			<td><input name="applyList[${count }].applyReason" value=""${breakApply[6] } style="border-style: none"></td>
			<%-- 	<td>${breakApply.stoDrugBean.drugName }</td>
				<td>${breakApply.bdBean.drugmanu }</td>
				<td>${breakApply.applyNum }</td>
				<td>${breakApply.manuBatch }</td>
				<td>${breakApply.applyNum*breakApply.bdBean.purPrice }</td>
				<td>${breakApply.adminBean.adminName }</td>
				<td>${breakApply.applyReason}</td> --%>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	
</div>


<!-- 模态框 -->
	 <div class="modal fade" id="drugModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title" id="">药品查询</h4>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
					<div class="modal-body" >
						<input id="drugName1" value="阿莫西林">
						
					</div>

					<div class="modal-footer">
						<!-- <button type="submit" class="btn btn-primary" id="btupload">上传</button> -->
						<button type="button" class="btn btn-default" data-dismiss="modal" onclick="getDrug()">关闭</button>
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

	function addApply(){
		var drugName=$("#drugName");
		var drugmanu=$("#drugManu");
		var drugBatch=$("#drugBatch");
		var manuBatch=$("#manuBatch");
		var applyNum=$("#applyNum");
		var applyMoney=$("#applyMoney");
		var applyReason=$("#applyReason");
		arr[count][0]=drugName;
		arr[count][1]=drugmanu;
		arr[count][2]=drugBatch;
		arr[count][3]=manuBatch;
		arr[count][4]=applyNum;
		arr[count][5]=applyMoney;
		arr[count][6]=applyReason;
		
		count+=1;
		
	
		 var str="<tr><td>"+drugName+"</td><td>"+drugmanu+"</td><td>"+drugBatch+"</td><td>"+manuBatch+"</td><td>"+applyNum+"</td><td>"+applyMoney+"</td><td>"+applyReason+"</td></tr>";
	}

</script>
</body>
</html>