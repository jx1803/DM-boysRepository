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
<title>药品采购入库</title>
<link rel="stylesheet"
	href="<%=path%>lib/zTree/v3/css/zTreeStyle/zTreeStyle.css"
	type="text/css">
</head>
<body class="pos-r">

	<div style="margin-left: 0px;">
		<nav class="breadcrumb">
			<i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span>
			药库工作 <span class="c-gray en">&gt;</span>采购药品入库<a
				class="btn btn-success radius r"
				style="line-height: 1.6em; margin-top: 3px"
				href="javascript:location.replace(location.href);" title="刷新"><i
				class="Hui-iconfont">&#xe68f;</i></a>
		</nav>
		<div style="height: 50px"></div>
		<div class="page-container">
			<form id="submitInstorage" action="pdInstorageSuccess.action?drugApplyBean.drugApplyId=${daBean.drugApplyId}&drugApplyBean.checkId=24" method="post">
				<div class="text-c">
					<table width="600" border="1"
						class="table table-border table-bordered table-bg">
						<tr>
							<td>药品编码：</td>
							<td><input type="text" name="drugId" id="drugId"
								placeholder="药品编码" style="width: 150px" class="input-text"
								readonly value="${sdBean.drugId}" /></td>
							<td>药品名称：</td>
							<td><input type="text" name="drugName" id="drugName"
								placeholder="药品名称" style="width: 150px" class="input-text"
								readonly value="${sdBean.drugName}" /></td>
							<td>单位：</td>
							<td><input type="text" name="unit" id="unit"
								placeholder="药品单位" style="width: 150px" class="input-text"
								readonly value="${sdBean.unit}" /></td>
						</tr>
						<tr>
							<td>规格：</td>
							<td><input type="text" name="specific" id="specific"
								placeholder="药品规格" style="width: 150px" class="input-text"
								readonly="readonly" value="${sdBean.specific}" /></td>
							<td>生产厂商：</td>
							<td><input type="text" name="stoDrugBean.drugmanu" id="drugmanu"
								placeholder="生产厂商" style="width: 150px" class="input-text"
								readonly value="${sdBean.drugmanu}" readonly="readonly" /></td>
							<td>药品产地：</td>
							<td><input type="text" name="proPlace" id="proPlace"
								placeholder="药品产地" style="width: 150px" class="input-text"
								value="${sdBean.proPlace}" readonly="readonly" /></td>

						</tr>
						<tr>
							<td>采购数量：</td>
							<td><input type="text" name="handleNum" id="applyNum"
								placeholder="请输入申请数量" style="width: 150px; margin-top: 5px"
								class="input-text" value="${daBean.applyNum}"
								readonly="readonly"
								onkeyup="this.value=this.value.replace(/\D/g,'')"
								onafterpaste="this.value=this.value.replace(/\D/g,'')" />
								<!-- <input type="hidden" id="totalInventory" name="inventoryNum"/> -->
								</td>
							<td>当前库存：</td>
							<td><input type="text" name="inventoryBean.inventoryNum" id="inventoryNum"
								placeholder="当前库存" style="width: 150px" class="input-text"
								readonly="readonly" value="${sdBean.inventoryBean.inventoryNum}" /></td>
							<td>药品进价：</td>
							<td><input type="text" name="purPrice" id="purPrice"
								placeholder="药品进价" style="width: 150px" class="input-text"
								value="${sdBean.purPrice}"
								onkeyup="this.value=this.value.replace(/\D/g,''),countMoney()"
								onafterpaste="this.value=this.value.replace(/\D/g,'')"
								oninput="countMoney()"
								/></td>
						</tr>
						<tr>
							<td>入库批次：</td>
							<td><input type="text" name="putBatch" id="putBatch"
								placeholder="请输入库批次" style="width: 150px; margin-top: 5px"
								class="input-text" onkeyup="this.value=this.value.replace(/\D/g,'')"
								onafterpaste="this.value=this.value.replace(/\D/g,'')" /></td>
							<td>生产批号：</td>
							<td><input type="text" name="manuBatch" id="manuBatch"
								placeholder="请输生产批号" style="width: 150px; margin-top: 5px"
								class="input-text" onkeyup="this.value=this.value.replace(/\D/g,'')"
								onafterpaste="this.value=this.value.replace(/\D/g,'')" /></td>
							<td>生产日期：</td>
							<td><input id="test1" name="proDate"
								class="inline laydate-icon" style="width: 150px;" /></td>
						</tr>
						<tr>
							<td>有效日期(天)：</td>
							<td><input type="number" name="inDate" id="inDate"
								placeholder="请输入有效日期" style="width: 150px" class="input-text"
								oninput="if(value>1826)value=1826;if(value.length>4)value=value.slice(0,4);if(value<=0)value=15"
								onkeyup="this.value=this.value.replace(/\D/g,'')"
								onafterpaste="this.value=this.value.replace(/\D/g,'')" /></td>
							<td>操作人：</td>
							<td><input type="text" name="adminName" id="adminName"
								style="width: 150px; margin-top: 5px" class="input-text"
								value="${sessionScope.User.adminName}" readonly="readonly" />
								<input type="hidden" id="adminId" name="outAndInBean.adminId" value="${User.adminId }"/>
								<input type="hidden" name="drugApplyBean.auditorId" id="auditorId" value="${User.adminId }"/>
								</td>
								
							<td>合计(元)：</td>
							<td><input type="text" name="totalMoney" id="totalMoney"
								placeholder="药品总价" style="width: 150px" class="input-text"
								value="${daBean.applyNum*sdBean.purPrice}"readonly="readonly"/></td>
							</td>
							
						</tr>
						<tr style="align: center">
							<td colspan="6"><button type="button"
									class="btn btn-primary" id="" onclick="cfInstorage()">确认入库</button></td>
						</tr>
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
	<script src="<%=path%>lib/laydate/laydate.js"></script>
	<script type="text/javascript">
		/* 时间控件 */
		!function() {
			laydate.skin('molv');//切换皮肤，请查看skins下面皮肤库
			laydate({
				elem : '#demo'
			});//绑定元素
		}();

		//自定义日期格式
		laydate({
			elem : '#test1',
			format : 'YYYY-MM-DD',
			min : '2016-01-01',
			max : laydate.now(-5),
			festival : true, //显示节日
			choose : function(datas) { //选择日期完毕的回调
			}
		});
		
		/* 计算总金额 */
		function countMoney(){
			if($("#purPrice").val()!="") {
				$("#totalMoney").val($("#purPrice").val()*$("#applyNum").val());
			}
		}

		/*药品-选择*/
		function drug_select(title, url, w, h) {
			layer_show(title, url, w, h);
		}
		var count = 0;

		/*确认入库*/
		function cfInstorage() {
			var purPrice = $("#purPrice").val();
			var putBatch = $("#putBatch").val();
			var manuBatch = $("#manuBatch").val();
			var proDate = $("#test1").val();
			var inDate = $("#inDate").val();
			var applyNum = $("#applyNum").val()
			var inventoryNum = $("#inventoryNum").val();
			if (applyNum == "" || manuBatch == "" || proDate == ""
					|| inDate == "" ||purPrice == "") {
				alert("请完善药品入库信息");
				return;
			}else{
				var r = confirm("确定核对准确入库?");
				if (r) {
					$("#submitInstorage").submit();
				}
			}
			
		}

		/* 清空申请记录 */
		function empty() {
			$("#tbodyId").html("");
			$("#papply").html("");
			count = 0;
		}

		/* 提交申请 */
		function subApply() {
			var r = confirm("确定提交吗?");
			if (r) {
				$("#papply").submit();
			}
		}
	</script>
</body>
</html>