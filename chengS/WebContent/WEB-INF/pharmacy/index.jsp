<%@ page language="java" pageEncoding="UTF-8"%>
<%
	String path = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%> 
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link rel="Bookmark" href="/favicon.ico">
<link rel="Shortcut Icon" href="/favicon.ico" />
<!--[if lt IE 9]>
<script type="text/javascript" src="lib/html5shiv.js"></script>
<script type="text/javascript" src="lib/respond.min.js"></script>
<![endif]-->
<link rel="stylesheet" type="text/css"
	href="../static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css"
	href="../static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css"
	href="../lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css"
	href="../static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css"
	href="../static/h-ui.admin/css/style.css" />
<!--[if IE 6]>
<script type="text/javascript" src="lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>H-ui.admin v3.1</title>

</head>
<body>
	<header class="navbar-wrapper">
		<div class="navbar navbar-fixed-top">
			<div class="container-fluid cl">
				<a class="logo navbar-logo f-l mr-10 hidden-xs"
					href="/aboutHui.shtml">SPMS智慧药房 </a> <span
					class="logo navbar-slogan f-l mr-10 hidden-xs">V1.0</span> <a
					aria-hidden="false" class="nav-toggle Hui-iconfont visible-xs"
					href="javascript:;">&#xe667;</a>

				<nav id="Hui-userbar"
					class="nav navbar-nav navbar-userbar hidden-xs">
					<ul class="cl">
						<li>超级管理员</li>
						<li class="dropDown dropDown_hover"><a href="#"
							class="dropDown_A">admin <i class="Hui-iconfont">&#xe6d5;</i></a>
							<ul class="dropDown-menu menu radius box-shadow">
								<li><a href="javascript:;" onClick="myselfinfo()">个人信息</a></li>
								<li><a href="#">切换账户</a></li>
								<li><a href="#">退出</a></li>
							</ul></li>
						<li id="Hui-msg"><a href="#" title="消息"><span
								class="badge badge-danger">1</span><i class="Hui-iconfont"
								style="font-size: 18px">&#xe68a;</i></a></li>
						<li id="Hui-skin" class="dropDown right dropDown_hover"><a
							href="javascript:;" class="dropDown_A" title="换肤"><i
								class="Hui-iconfont" style="font-size: 18px">&#xe62a;</i></a>
							<ul class="dropDown-menu menu radius box-shadow">
								<li><a href="javascript:;" data-val="default"
									title="默认（黑色）">默认（黑色）</a></li>
								<li><a href="javascript:;" data-val="blue" title="蓝色">蓝色</a></li>
								<li><a href="javascript:;" data-val="green" title="绿色">绿色</a></li>
								<li><a href="javascript:;" data-val="red" title="红色">红色</a></li>
								<li><a href="javascript:;" data-val="yellow" title="黄色">黄色</a></li>
								<li><a href="javascript:;" data-val="orange" title="橙色">橙色</a></li>
							</ul></li>
					</ul>
				</nav>
			</div>
		</div>
	</header>
	<aside class="Hui-aside">
		<div class="menu_dropdown bk_2">
		
		<dl id="menu-system">
			<dt><i class="Hui-iconfont">&#xe62e;</i> 药品管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					<li><a data-href="<%=path%>storage/findDrugTypeInfo.action" data-title="药品类型" href="javascript:void(0)">药品类型</a></li>
					<li><a data-href="<%=path%>storage/findDfInfo.action" data-title="药品剂型" href="javascript:void(0)">药品剂型</a></li>
					<li><a data-href="<%=path%>storage/findDrugInfo.action" data-title="药品字典" href="javascript:void(0)">药品字典</a></li>
					<li><a data-href="<%=path%>storage/findCompTaboo.action" data-title="配伍禁忌" href="javascript:void(0)">配伍禁忌</a></li>
					<li><a data-href="system-log.html" data-title="系统日志" href="javascript:void(0)">系统日志</a></li>
			</ul>
		</dd>
	</dl>
		
		<dl id="menu-product">
			<dt><i class="Hui-iconfont">&#xe620;</i> 库存工作<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					<li><a data-href="<%=path%>storage/purchaseApplyShow.action" data-title="药品采购申请" href="javascript:void(0)">药品采购申请</a></li>
					<li><a data-href="<%=path%>storage/purchaseApplyLook.action" data-title="药品采购申请记录" href="javascript:void(0)">药品采购申请记录</a></li>
					<li><a data-href="<%=path%>storage/pdInstorage.action" data-title="药品采购入库" href="javascript:void(0)">药品采购入库</a></li>
					<li><a data-href="admin-permission.html" data-title="退还厂家" href="javascript:void(0)">退还厂家</a></li>
					<li><a data-href="admin-list.html" data-title="出库审核" href="javascript:void(0)">出库审核</a></li>
					<li><a data-href="<%=path%>storage/drugApplyAudit.action" data-title="药品采购审核" href="javascript:void(0)">药品采购审核</a></li>
					<li><a data-href="admin-list.html" data-title="退还审核" href="javascript:void(0)">退还审核</a></li>
			</ul>
		</dd>
	</dl>
	
		
			<dl id="menu-comments">
				<dt>
					<i class="Hui-iconfont">&#xe622;</i> 日常工作<i
						class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
				</dt>
				<dd>
					<ul>
						<li><a data-href="toTakeDA.action" data-title="药品请领"
							href="javascript:;">药品请领</a></li>
						<li><a data-href="toCaceApply.action" data-title="退库申请"
							href="javascript:void(0)">退库申请</a></li>
						<li><a data-href="totakeApplyList.action" data-title="请领审核"
							href="javascript:void(0)">请领审核</a></li>
						<li><a data-href="toCancellingApplyList.action"
							data-title="退库审核" href="javascript:void(0)">退库审核</a></li>
						<li><a data-href="toAllTakeApply.action" data-title="查看请领记录"
							href="javascript:void(0)">查看请领记录</a></li>
						<li><a data-href="toAllCancApply.action" data-title="查看退库记录"
							href="javascript:void(0)">查看退库记录</a></li>
							
							
							<li><a data-href="<%=path%>pharmacy/toBreakApply.action" data-title="产品管理" href="javascript:void(0)">药品报损</a></li>
					<li><a data-href="<%=path%>pharmacy/toAdjustPrice.action" data-title="药品调价" href="javascript:void(0)">药品调价</a></li>
					<li><a data-href="<%=path%>pharmacy/product-list.html" data-title="产品管理" href="javascript:void(0)">药品停用</a></li>
					<li><a data-href="<%=path%>pharmacy/toSellDrug.action" data-title="产品管理" href="javascript:void(0)">药品发药</a></li>
					<li><a data-href="<%=path%>pharmacy/product-list.html" data-title="产品管理" href="javascript:void(0)">药品盘点</a></li>
					<li><a data-href="<%=path%>pharmacy/toBreakCheck.action" data-title="报损审核" href="javascript:void(0)">药品报损审核</a></li>
					</ul>
				</dd>
			</dl>
			
			
			<dl id="menu-member">
			<dt><i class="Hui-iconfont">&#xe60d;</i> 会员管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					<li><a data-href="<%=path %>user/memberList.action" data-title="会员列表" href="javascript:;">会员列表</a></li>
					<li><a data-href="member-del.html" data-title="删除的会员" href="javascript:;">删除的会员</a></li>
					
			</ul>
		</dd>
	</dl>
		<dl id="menu-admin">
			<dt><i class="Hui-iconfont">&#xe62d;</i> 管理员管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					<li><a data-href="<%=path %>user/roleList.action" data-title="角色管理" href="javascript:void(0)">角色管理</a></li>
					<li><a data-href="admin-permission.html" data-title="权限管理" href="javascript:void(0)">权限管理</a></li>
					<li><a data-href="admin-list.html" data-title="管理员列表" href="javascript:void(0)">管理员列表</a></li>
					<li><a data-href="<%=path %>user/deptList.action" data-title="部门管理" href="javascript:void(0)">部门管理</a></li>
					<li><a data-href="<%=path %>user/menuLevel1List.action" data-title="一级菜单管理" href="javascript:void(0)">一级菜单管理</a></li>
					<li><a data-href="<%=path %>user/menuLevel2List.action" data-title="二级菜单管理" href="javascript:void(0)">二级菜单管理</a></li>
					<li><a data-href="<%=path %>user/pConfiguration.action" data-title="权限配置" href="javascript:void(0)">权限配置</a></li>
			</ul>
		</dd>
	</dl>

		</div>
	</aside>
	<div class="dislpayArrow hidden-xs">
		<a class="pngfix" href="javascript:void(0);"
			onClick="displaynavbar(this)"></a>
	</div>
	<section class="Hui-article-box">
		<div id="Hui-tabNav" class="Hui-tabNav hidden-xs">
			<div class="Hui-tabNav-wp">
				<ul id="min_title_list" class="acrossTab cl">
					<li class="active"><span title="我的桌面" data-href="welcome.html">我的桌面</span>
						<em></em></li>
				</ul>
			</div>
			<div class="Hui-tabNav-more btn-group">
				<a id="js-tabNav-prev" class="btn radius btn-default size-S"
					href="javascript:;"><i class="Hui-iconfont">&#xe6d4;</i></a><a
					id="js-tabNav-next" class="btn radius btn-default size-S"
					href="javascript:;"><i class="Hui-iconfont">&#xe6d7;</i></a>
			</div>
		</div>
		<div id="iframe_box" class="Hui-article">
			<div class="show_iframe">
				<div style="display: none" class="loading"></div>
			</div>
		</div>
	</section>

	<div class="contextMenu" id="Huiadminmenu">
		<ul>
			<li id="closethis">关闭当前</li>
			<li id="closeall"><iframe scrolling="yes" frameborder="0" src=""></iframe>
				关闭全部</li>
		</ul>
	</div>
	<!--_footer 作为公共模版分离出去-->
	<script type="text/javascript" src="../lib/jquery/1.9.1/jquery.min.js"></script>
	<script type="text/javascript" src="../lib/layer/2.4/layer.js"></script>
	<script type="text/javascript" src="../static/h-ui/js/H-ui.min.js"></script>
	<script type="text/javascript"
		src="../static/h-ui.admin/js/H-ui.admin.js"></script>
	<!--/_footer 作为公共模版分离出去-->

	<!--请在下方写此页面业务相关的脚本-->
	<script type="text/javascript"
		src="../lib/jquery.contextmenu/jquery.contextmenu.r2.js"></script>
	<script type="text/javascript">
		function myselfinfo() {
			layer.open({
				type : 1,
				area : [ '300px', '200px' ],
				fix : false, //不固定
				maxmin : true,
				shade : 0.4,
				title : '查看信息',
				content : '<div>管理员信息</div>'
			});
		}

		/*资讯-添加*/
		function article_add(title, url) {
			var index = layer.open({
				type : 2,
				title : title,
				content : url
			});
			layer.full(index);
		}
		/*图片-添加*/
		function picture_add(title, url) {
			var index = layer.open({
				type : 2,
				title : title,
				content : url
			});
			layer.full(index);
		}
		/*产品-添加*/
		function product_add(title, url) {
			var index = layer.open({
				type : 2,
				title : title,
				content : url
			});
			layer.full(index);
		}
		/*用户-添加*/
		function member_add(title, url, w, h) {
			layer_show(title, url, w, h);
		}
	</script>


</body>
</html>