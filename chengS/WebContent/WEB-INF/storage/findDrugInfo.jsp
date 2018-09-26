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
<meta name="viewport"
	content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<!--[if lt IE 9]>
<script type="text/javascript" src="<%=path%>lib/html5shiv.js"></script>
<script type="text/javascript" src="<%=path%>lib/respond.min.js"></script>
<![endif]-->
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
<script type="text/javascript" src="<%=path%>lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>药品字典</title>
</head>
<body>
	<nav class="breadcrumb">
		<i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span>
		药品管理 <span class="c-gray en">&gt;</span> 药品字典 <a
			class="btn btn-success radius r"
			style="line-height: 1.6em; margin-top: 3px"
			href="javascript:location.replace(location.href);" title="刷新"><i
			class="Hui-iconfont">&#xe68f;</i></a>
	</nav>

	<!-- 模态框（添加药品信息） -->
	<div class="modal fade" id="addModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<form action="addDrugInfo.action" method="post">
			<div class="modal-dialog" style="width: 800px">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">添加药品信息</h4>
					</div>
					<div class="modal-body">
						<div class="pd-20">
							<div class="Huiform">
								
									<table class="table table-bg">
										<tbody>
											<tr>
												<th width="100" class="text-r"><span class="c-red">*</span>
													药品名称：</th>
												<td><input type="text" style="width: 260px"
													class="input-text" value="" placeholder="" id="drugName"
													name="drugName" datatype="*2-16" nullmsg="用户名不能为空"></td>
												<th width="100" class="text-r">通用名称：</th>
												<td><input type="text" style="width: 260px"
													class="input-text" value="" placeholder="" id="generalName"
													name="generalName" datatype="*2-16" nullmsg="用户名不能为空"></td>
											</tr>
											<tr>
												<th class="text-r">抗生素：</th>
												<td><label> <input name="antibiotic" type="radio"
														id="antibiotic_1" value="是" checked> 是
												</label> <label> <input type="radio" name="antibiotic" value="否"
														id="antibiotic_0"> 否
												</label></td>
												<th width="100" class="text-r">拼音码：</th>
												<td><input type="text" style="width: 260px"
													class="input-text" value="" placeholder="" id="pinyinCode"
													name="pinyinCode" datatype="*2-16" nullmsg="用户名不能为空"></td>
											</tr>

											<tr>
												<th class="text-r">单位：</th>
												<td><input type="text" style="width: 260px"
													class="input-text" value="" placeholder="" id="unit"
													name="unit"></td>
												<th class="text-r">规格：</th>
												<td><input type="text" style="width: 260px"
													class="input-text" value="" placeholder="" id="specific"
													name="specific"></td>
											</tr>
											<tr>
												<th class="text-r">零售价：</th>
												<td><input type="text" style="width: 260px"
													class="input-text" value="" placeholder="" id="retailPrice"
													name="retailPrice"></td>
												<th class="text-r">使用方法：</th>
												<td><input type="text" style="width: 260px"
													class="input-text" value="" placeholder="" id="usage"
													name="usage"></td>
											</tr>
											<tr>
												<th class="text-r">日次数：</th>
												<td><input type="text" style="width: 260px"
													class="input-text" value="" placeholder="" id="dailyNum"
													name="dailyNum"></td>
												<th class="text-r">次剂量：</th>
												<td><input type="text" style="width: 260px"
													class="input-text" value="" placeholder="" id="dosage"
													name="dosage"></td>
											</tr>
											<tr>
												<th class="text-r">生产厂家：</th>
												<td><input type="text" style="width: 260px"
													class="input-text" value="" placeholder="" id="drugmanu"
													name="drugmanu"></td>
												<th class="text-r">生产场地：</th>
												<td><input type="text" style="width: 260px"
													class="input-text" value="" placeholder="" id="proPlace"
													name="proPlace"></td>
											</tr>
											<tr>
												<th class="text-r">剂型：</th>
												<td><span class="select-box" id="addShowDf"> <select
														class="select" size="1" name="dosageId" id="dosageId">
														<c:forEach items="${dfList}" var="dfList" >
															<option value="${dfList.dosageId}">${dfList.dosageForm}</option>
														</c:forEach>
													</select>
												</span></td>
												<th class="text-r">发票抬头：</th>
												<td><input type="text" style="width: 260px"
													class="input-text" value="" placeholder="" id="invoiceTitle"
													name="invoiceTitle"></td>
											</tr>
											<tr>
												<th class="text-r">一级类型：</th>
												<td><span class="select-box"> <select onchange="findSecondType()"
														class="select" size="1" name="pid" id="pid">
															<option value="">请选择类型</option>
															<option value="1">中药</option>
															<option value="2">西药</option>
															<option value="3">特殊药物</option>
													</select>
												</span></td>
												<th class="text-r">二级类型：</th>
												<td><span class="select-box" id="addShowDt"> <select
														class="select" size="1" name="typeId" id="typeId">
															<option value="" selected>请选择</option>
															<option value="1">菜单一</option>
															<option value="2">菜单二</option>
															<option value="3">菜单三</option>
													</select>
												</span></td>
											</tr>
										</tbody>
									</table>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭
						</button>
						<button type="submit" class="btn btn-primary">提交更改</button>
					</div>
				</div>
			</div>
		</form>
	</div>

	<!-- 模态框（修改药品信息） -->
	<div class="modal fade" id="updModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<form action="updDrugInfo.action" method="post">
			<div class="modal-dialog" style="width: 800px">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">修改药品信息</h4>
					</div>
					<div class="modal-body">
						<div class="pd-20">
							<div class="Huiform">
								
									<table class="table table-bg">
										<tbody>
											<tr>
												<th width="100" class="text-r"><span class="c-red">*</span>
													药品名称：</th>
												<td><input type="text" style="width: 260px"
													class="input-text" value="" placeholder="" id="updDrugName"
													name="drugName" datatype="*2-16" nullmsg="用户名不能为空">
													<input type="hidden" id="updID" name="drugId" ></td>
												<th width="100" class="text-r">通用名称：</th>
												<td><input type="text" style="width: 260px"
													class="input-text" value="" placeholder="" id="updGeneralName"
													name="generalName" datatype="*2-16" nullmsg="用户名不能为空"></td>
											</tr>
											<tr>
												<th class="text-r">抗生素：</th>
												<td><label> <input name="antibiotic" type="radio"
														id="updAntibiotic_1" value="是" checked> 是
												</label> <label> <input type="radio" name="antibiotic" value="否"
														id="updAntibiotic_0"> 否
												</label></td>
												<th width="100" class="text-r">拼音码：</th>
												<td><input type="text" style="width: 260px"
													class="input-text" value="" placeholder="" id="updPinyinCode"
													name="pinyinCode" datatype="*2-16" nullmsg="用户名不能为空"></td>
											</tr>

											<tr>
												<th class="text-r">单位：</th>
												<td><input type="text" style="width: 260px"
													class="input-text" value="" placeholder="" id="updUnit"
													name="unit"></td>
												<th class="text-r">规格：</th>
												<td><input type="text" style="width: 260px"
													class="input-text" value="" placeholder="" id="updSpecific"
													name="specific"></td>
											</tr>
											<tr>
												<th class="text-r">零售价：</th>
												<td><input type="text" style="width: 260px"
													class="input-text" value="" placeholder="" id="updRetailPrice"
													name="retailPrice" readonly="readonly"></td>
												<th class="text-r">使用方法：</th>
												<td><input type="text" style="width: 260px"
													class="input-text" value="" placeholder="" id="updUsage"
													name="usage"></td>
											</tr>
											<tr>
												<th class="text-r">日次数：</th>
												<td><input type="text" style="width: 260px"
													class="input-text" value="" placeholder="" id="updDailyNum"
													name="dailyNum"></td>
												<th class="text-r">次剂量：</th>
												<td><input type="text" style="width: 260px"
													class="input-text" value="" placeholder="" id="updDosage"
													name="dosage"></td>
											</tr>
											<tr>
												<th class="text-r">生产厂家：</th>
												<td><input type="text" style="width: 260px"
													class="input-text" value="" placeholder="" id="updDrugmanu"
													name="drugmanu"></td>
												<th class="text-r">生产场地：</th>
												<td><input type="text" style="width: 260px"
													class="input-text" value="" placeholder="" id="updProPlace"
													name="proPlace"></td>
											</tr>
											<tr>
												<th class="text-r">剂型：</th>
												<td><span class="select-box" id="updShowDf"> <select
														class="select" size="1" name="dosageId" id="updDosageId">
															<option value="1">菜单一</option>
															<option value="2">菜单二</option>
															<option value="3">菜单三</option>
													</select>
												</span></td>
												<th class="text-r">发票抬头：</th>
												<td><input type="text" style="width: 260px"
													class="input-text" value="" placeholder="" id="updInvoiceTitle"
													name="invoiceTitle"></td>
											</tr>
											<tr>
												<th class="text-r">一级类型：</th>
												<td><span class="select-box"> <select onchange="findSecondType()"
														class="select" size="1" name="pid" id="updPid">
															<option value="">请选择类型</option>
															<option value="1">中药</option>
															<option value="2">西药</option>
															<option value="3">特殊药物</option>
													</select>
												</span></td>
												<th class="text-r">二级类型：</th>
												<td><span class="select-box" id="updShowDt"> <select
														class="select" size="1" name="typeId" id="updTypeId">
															<option value="" selected>请选择</option>
															
													</select>
												</span></td>
											</tr>
										</tbody>
									</table>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭
						</button>
						<button type="submit" class="btn btn-primary">提交更改</button>
					</div>
				</div>
			</div>
		</form>
	</div>

	<!-- 主页面 -->
	<div class="page-container">
		<div class="text-c">
			<form action="findDrugInfo.action" method="post" id="formSd" >
			药品名称: 
			<input type="text" class="input-text" id="drugName" name="drugName" value="${condi.drugName==null?"":condi.drugName}"
				style="width: 150px"> 
		 	 药品编码: 
			<input type="text" class="input-text" id="drugId" name="drugId" style="width: 150px" 
			value="${condi.drugId==null?"":condi.drugId}">
			<input type='hidden' id="h_drugId" value="0">
			拼音码:
			 <input type="text" class="input-text" id="pinyinCode" name="pinyinCode" 
			 style="width: 150px" value="${condi.pinyinCode==null?"":condi.pinyinCode}">
			药品分类：
			<span class="select-box" id="addShowDt" style="width:160px"> 
				<select class="select" size="1" name="typeId" id="typeId" >
						<option value="0">请选择类型</option>
						<c:forEach items="${drugTypeList}" var="typeList">
						<c:if test="${typeList.typeId ==condi.typeId }">
						<option value="${typeList.typeId}" selected="selected">${typeList.drugType}</option>
						</c:if>
						<c:if test="${typeList.typeId !=condi.typeId }">
						<option value="${typeList.typeId}" >${typeList.drugType}</option>
						</c:if>
						</c:forEach>
				</select>
			</span> 
			<button type="button" class="btn btn-primary radius" id="sub" name=""> 搜索药品</button>
			<button type="button" class="btn btn-primary radius" id="" name="" onclick="addModal()">
			<i class="Hui-iconfont">&#xe600;</i> 添加药品
			</button>
			</form>
		</div>
		<div class="mt-20">
			<table
				class="table table-border table-bordered table-bg table-hover table-sort">
				<thead>
					<tr class="text-c">

						<th>药品编码</th>
						<th>药品名称</th>
						<th>通用名称</th>
						<th>规格</th>
						<th>单位</th>
						<th>零售价</th>
						<th>类别</th>
						<th>剂型</th>
						<th>使用方法</th>
						<th>发票抬头</th>
						<th>拼音码</th>
						<th>抗生素</th>
						<th>次剂量</th>
						<th>日次数</th>
						<th>生产厂商</th>
						<th>生产场地</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach items="${stoDrugList}" var="sdList" varStatus="vs">
					<tr class="text-c">

						<td>${sdList.drugId}</td>
						<td>${sdList.drugName}</td>
						<td>${sdList.generalName}</td>
						<td>${sdList.specific}</td>
						<td>${sdList.unit}</td>
						<td>${sdList.retailPrice}</td>
						<td>${sdList.drugTypeBean.drugType}</td>
						<td>${sdList.dfBean.dosageForm}</td>
						<td>${sdList.usage}</td>
						<td>${sdList.invoiceTitle}</td>
						<td>${sdList.pinyinCode}</td>
						<td>${sdList.antibiotic}</td>
						<td>${sdList.dosage}</td>
						<td>${sdList.dailyNum}</td>
						<td>${sdList.drugmanu}</td>
						<td>${sdList.proPlace}</td>
						<td class="f-14"><a style="text-decoration: none"
							onclick="updModal('${sdList.drugId}','${sdList.drugName}','${sdList.generalName}','${sdList.specific}',
							'${sdList.unit}','${sdList.retailPrice}','${sdList.typeId}','${sdList.drugTypeBean.pid}','${sdList.dosageId}',
							'${sdList.usage}','${sdList.invoiceTitle}','${sdList.pinyinCode}','${sdList.antibiotic}','${sdList.dosage}',
							'${sdList.dailyNum}','${sdList.drugmanu}','${sdList.proPlace}')"
							
							href="javascript:;" title="编辑"><i class="Hui-iconfont">&#xe6df;</i></a>
							</td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<br />
		<div style="float: right; margain-top: 20px;">
			<span ></span> ><font size="2">共${pageTotal}页  &nbsp;当前页${condi.page}/${pageTotal}</font></span>  &nbsp; &nbsp; &nbsp;
			<button type="submit" class="btn btn-secondary-outline radius"  onclick="upPage('${condi.page}')">上一页</button>
			<c:forEach var="page" begin="1" end="${pageTotal}">
			 <button type="button" class="btn btn-primary size-S radius" onclick="skipPage('${page}')">${page}</button>
			</c:forEach>
			<button type="button" class="btn btn-secondary-outline radius" onclick="nextPage('${condi.page}','${pageTotal }')">下一页</button>
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
		src="<%=path%>lib/My97DatePicker/4.8/WdatePicker.js"></script>
	<script type="text/javascript"
		src="<%=path%>lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
	<script type="text/javascript"
		src="<%=path%>lib/laypage/1.2/laypage.js"></script>
	<script type="text/javascript">
		//提交搜索
		$(function(){
			$("#sub").click(function(){
				var num = $("#drugId").val()
				if(num==""){ 
					$("#drugId").val($("#h_drugId").val())
				}
				$("#formSd").submit();
			})
		})
		//弹出修改模态框
		function updModal(	drugId,drugName, generalName,specific,unit,retailPrice,typeId,pid,
				dosageId,usage,invoiceTitle,pinyinCode,antibiotic,dosage,dailyNum,drugmanu,proPlace){
	
			$.ajax({
				type : 'POST',
				url : 'findDf.action',
				dataType : 'json',
				success : function(data) {
					
					var str = '<select class="select" size="1" name="dosageId" id="updDosageId">';
					for(var i=0;i<data.length;i++){
						if(data[i].dosageId==dosageId){
							str +='<option  value="'+data[i].dosageId +'" selected="selected">'+data[i].dosageForm+'</option>';
						}else{
							str +='<option  value="'+data[i].dosageId +'">'+data[i].dosageForm+'</option>';
						}
						
					}
					str +='</select>';
					$("#updShowDf").html(str);
				}
			});
			
			$("#updDrugName").val(drugName);
			$("#updGeneralName").val(generalName);
			if(antibiotic=="是"){
				$("#updAntibiotic_1").attr("checked", true);
			}else{
				$("#updAntibiotic_0").attr("checked", true);
			}
			$("#updPinyinCode").val(pinyinCode);
			$("#updUnit").val(unit);
			$("#updSpecific").val(specific);
			$("#updRetailPrice").val(retailPrice);
			$("#updUsage").val(usage);
			$("#updDailyNum").val(dailyNum);
			$("#updProPlace").val(proPlace);
			$("#updDosageId").val(dosageId);
			$("#updInvoiceTitle").val(invoiceTitle);
			$("#updDosage").val(dosage);
			$("#updDrugmanu").val(drugmanu);
			$("#updPid").val(pid);
			$("#updID").val(drugId);
			$.ajax({
				type : 'POST',
				url : 'findSecondType.action?pid='+pid,
				dataType : 'json',
				success : function(data) {
					var str = '<select class="select" size="1" name="typeId" id="typeId">';
					for(var i=0;i<data.length;i++){
						if(data[i].typeId==typeId){
							str +='<option  value="'+data[i].typeId +'" selected="selected">'+data[i].drugType+'</option>';
						}else{
							str +='<option  value="'+data[i].typeId +'">'+data[i].drugType+'</option>';
						}
					}
					str +='</select>'
					$("#updShowDt").html(str);
				}
		   })
		
			$('#updModal').modal('show');
		}
		
		
		
		//弹出添加模态框
		function addModal(){
			
			$.ajax({
				type : 'POST',
				url : 'findDf.action',
				dataType : 'json',
				success : function(data) {
					
					var str = '<select class="select" size="1" name="dosageId" id="dosageId">';
					for(var i=0;i<data.length;i++){
						str +='<option value="'+data[i].dosageId
						str +='" >'
						str +=data[i].dosageForm
						str +='</option>'
					}
					str +='</select>'
					$("#updShowDf").html(str);
				}
			});
			
			
			$('#addModal').modal('show');
		}
		
		//获取二级药品类型(添加药品)
	 	function findSecondType(){
			$.ajax({
				type : 'POST',
				url : 'findSecondType.action?pid='+$("#pid").val(),
				dataType : 'json',
				success : function(data) {
					var str = '<select class="select" size="1" name="typeId" id="typeId">';
					for(var i=0;i<data.length;i++){
						str +='<option value="'+data[i].typeId
						str +='" >'
						str +=data[i].drugType
						str +='</option>'
					}
					str +='</select>'
					$("#addShowDt").html(str);
				}
		   })
		
		} 
		//上一页
		function upPage(page) {
			var str = "";
			if (page > 1) {
				page -= 1;
				str = "findDrugInfo.action?page="+page;
				//attr更改form表单的action属性，改为str1
				$("#formSd").attr("action",str);
				//把form表单提交。
				var num = $("#drugId").val()
				if(num==""){
					$("#drugId").val($("#h_drugId").val())
				}
				$("#formSd").submit();
			} else {
				return;
			}
			//location.href = str1;
		}

		//下一页,pageNum,当前页数，total 总页数
		function nextPage(page, total) {
			var str = "";
			if (page < total) {
				page = Number(page) + 1;
				str = "findDrugInfo.action?page="+page;
				//attr更改form表单的action属性，改为str1
				$("#formSd").attr("action",str);
				//把form表单提交。
				var num = $("#drugId").val()
				if(num==""){
					$("#drugId").val($("#h_drugId").val())
				}
				$("#formSd").submit();
			} else {
				return;
			}
		}
		//点击按钮跳转页面
		function skipPage(page){
			var str = "";
			str = "findDrugInfo.action?page="+page;
			$("#formSd").attr("action",str);
			//把form表单提交。
			var num = $("#drugId").val()
			if(num==""){
				$("#drugId").val($("#h_drugId").val())
			}
			$("#formSd").submit();
		}
	</script>
</body>
</html>