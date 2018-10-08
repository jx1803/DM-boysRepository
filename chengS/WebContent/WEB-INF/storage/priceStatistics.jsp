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
<!--[if lt IE 9]>
<script type="text/javascript" src="lib/html5shiv.js"></script>
<script type="text/javascript" src="lib/respond.min.js"></script>
<![endif]-->
<link rel="stylesheet" type="text/css" href="<%=path%>static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="<%=path%>static/h-ui.admin/css/style.css" />
<!--[if IE 6]>
<script type="text/javascript" src="<%=path%>lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>折线图</title>
</head>
<body>
<nav class="breadcrumb">
	<i class="Hui-iconfont">&#xe67f;</i> 
	首页 <span class="c-gray en">&gt;</span> 
	统计管理 <span class="c-gray en">&gt;</span> 
	药品调价统计折线图 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="#" onclick="javascript:history.back(-1);" title="返回" >
	<i class="Hui-iconfont">&#xe678;</i></a>
</nav>
<div class="page-container">
	<input type="hidden" id="drugId" name="drugId" value="${drugId}"/>
	<div id="container" style="min-width:700px;height:400px"></div>
</div>
<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="<%=path%>lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="<%=path%>lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="<%=path%>static/h-ui/js/H-ui.min.js"></script> 
<script type="text/javascript" src="<%=path%>static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="<%=path%>lib/hcharts/Highcharts/5.0.6/js/highcharts.js"></script>
<script type="text/javascript" src="<%=path%>lib/hcharts/Highcharts/5.0.6/js/modules/exporting.js"></script>
<script type="text/javascript">
$(function(){
	var drugId = $("#drugId").val();
	$.ajax({
		type : 'POST',
		url : 'showPriceStatistics.action?drugId='+drugId,
		dataType : 'json',
		success : function(data) {
			
			$(function () {
			    Highcharts.chart('container', {
			        title: {
			            text: '药品调价统计',
			            x: -20 //center
			        },
			        subtitle: {
			            text: data.generalName,	//副标题（药品名）
			            x: -20
			        },
			        xAxis: {
			            categories:data.countArray	//次数（横坐标）
			        },
			        yAxis: {
			            title: {
			                text: 'Price (￥)'
			            },
			            plotLines: [{
			                value: 0,
			                width: 1,
			                color: '#808080'
			            }]
			        },
			        tooltip: {
			            valueSuffix: '￥'
			        },
			        legend: {
			            layout: 'vertical',
			            align: 'right',
			            verticalAlign: 'middle',
			            borderWidth: 0
			        },
			        series: [{
			            name: '价格',
			            data: data.dataArray	//统计数据
			        }]
			    });
			});
		}
	});
});


</script>
</body>
</html>