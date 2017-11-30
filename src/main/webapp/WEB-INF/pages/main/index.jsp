<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Date"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>SaaS平台</title>

<link href="${contextPath}/static/styles/dwz/themes/newcard/style.css" rel="stylesheet" type="text/css" media="screen"/>
<link href="${contextPath}/static/styles/dwz/themes/css/core.css" rel="stylesheet" type="text/css" media="screen"/>
<link href="${contextPath}/static/styles/dwz/themes/css/print.css" rel="stylesheet" type="text/css" media="print"/>
<link href="${contextPath}/static/styles/uploadify/css/uploadify.css" rel="stylesheet" type="text/css" media="screen"/>
<!--[if IE]>
<link href="themes/css/ieHack.css" rel="stylesheet" type="text/css" media="screen"/>
<![endif]-->

<!--[if lte IE 9]>
<script src="${contextPath}/static/styles/dwz/js/speedup.js" type="text/javascript"></script>
<![endif]-->

<script src="${contextPath}/static/styles/dwz/js/jquery-1.7.2.js" type="text/javascript"></script>
<script src="${contextPath}/static/styles/dwz/js/jquery.cookie.js" type="text/javascript"></script>
<script src="${contextPath}/static/styles/dwz/js/jquery.validate.js" type="text/javascript"></script>
<script src="${contextPath}/static/styles/dwz/js/jquery.bgiframe.js" type="text/javascript"></script>
<script src="${contextPath}/static/styles/xheditor/xheditor-1.1.14-zh-cn.min.js" type="text/javascript"></script>
<script src="${contextPath}/static/styles/uploadify/scripts/jquery.uploadify.js" type="text/javascript"></script>
<script src="${contextPath}/static/styles/dwz/js/treeview.js" type="text/javascript"></script>
<script src="${contextPath}/static/styles/dwz/js/leftMenu.js" type="text/javascript"></script>
<%-- form验证 --%>
<script src="${contextPath}/static/styles/validationEngine/js/languages/jquery.validationEngine-zh_CN.js" type="text/javascript" charset="utf-8"></script>
<script src="${contextPath}/static/styles/validationEngine/js/jquery.validationEngine-2.6.4.js" type="text/javascript" charset="utf-8"></script>

<script src="${contextPath}/static/styles/dwz/js/dwz.min.js" type="text/javascript"></script>

<script src="${contextPath}/static/styles/dwz/js/dwz.regional.zh.js" type="text/javascript"></script>
<%-- 自定义JS --%>
<script src="${contextPath}/static/styles/dwz/js/customer.js" type="text/javascript"></script>
<%-- 自定义时间框架 --%>
<script src="${contextPath}/static/styles/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
<!-- 
<script src="${contextPath}/static/styles/My97DatePicker/config.js" type="text/javascript"></script>
<script src="${contextPath}/static/styles/My97DatePicker/calendar.js" type="text/javascript"></script>
 -->
<%-- upload --%>
<script src="${contextPath}/static/styles/uploadify/scripts/jquery.uploadify.min.js" type="text/javascript"></script>
<%-- zTree --%>
<script src="${contextPath}/static/styles/ztree/js/jquery.ztree.all-3.5.min.js" type="text/javascript"></script>

<script type="text/javascript">
$(function(){
	DWZ.init("${contextPath}/static/styles/dwz/dwz.frag.xml", {
		loginUrl:"${contextPath}/management/login/timeout", loginTitle:"登录",	// 弹出登录对话框
//		loginUrl:"login.html",	// 跳到登录页面
		statusCode:{ok:200, error:300, timeout:301}, //【可选】
		pageInfo:{pageNum:"pageNum", numPerPage:"numPerPage", orderField:"orderField", orderDirection:"orderDirection"}, //【可选】
		debug:false,	// 调试模式 【true|false】
		callback:function(){
			initEnv();
			$("#themeList").theme({themeBase:"${contextPath}/static/styles/dwz/themes"});
		}
	});
});
</script>

<script type="text/javascript">
	$(function(){
	  var len=$(window).height();
	  $("#left").css({"height": + len - 0});
	  $(".left_menu ul li ol li a").click(function(){
			$(".left_menu ul li ol li a").removeClass("current");
			$(this).addClass("current");
		});
	});  
</script> 
</head>

<body scroll="no">
	<div id="layout">
		<div id="header">
			<div class="headerNav ico">
           		<span class="tima">
		    		<div>
					    <span>
					    	<span style="color: white; font-size: 10pt;">欢迎，${currentUser.realname}</span>
			               	<a href="${contextPath}/security/user/changePassword" target="dialog" mask="true" width="500" height="200" class="topLink02">修改密码</a>
			               	<a href="${contextPath}/main/logout" class="topLink04">退出</a>
			            </span>
               		</div>
               	</span>
      		</div>
		</div>

		<div id="leftside">
			<div id="sidebar_s">
				<div class="collapse">
					<div class="toggleCollapse"><div></div></div>
				</div>
			</div>
			<div id="sidebar">
			<div class="toggleCollapse"><h2>菜单</h2><div>collapse</div></div>
			<div class="accordion" fillSpace="sideBar">
				<div class="accordionHeader">
					<h2><span>Folder</span>企业秀</h2>
				</div>
				<div class="accordionContent">
					<ul class="tree treeFolder">
						<li><a href="${contextPath}/post/list" target="navTab" rel="post">帖子管理</a></li>
					</ul>
				</div>
				<div class="accordionHeader">
					<h2><span>Folder</span>安全管理</h2>
				</div>
				<div class="accordionContent">
					<ul class="tree treeFolder">
						<li><a href="${contextPath}/security/user/list" target="navTab" rel="user">用户管理</a></li>
						<li><a href="${contextPath}/security/role/list" target="navTab" rel="role">角色管理</a></li>
					</ul>
				</div>			
			</div>
		</div>
		</div>
		<div id="container">
			<div id="navTab" class="tabsPage">
			<div class="tabsPageHeader">
				<div class="tabsPageHeaderContent"><!-- 显示左右控制时添加 class="tabsPageHeaderMargin" -->
					<ul class="navTab-tab">
						<li tabid="main" class="main"><a href="javascript:void(0);"><span><span class="home_icon">主页</span></span></a></li>
					</ul>
				</div>
				<div class="tabsLeft">left</div><!-- 禁用只需要添加一个样式 class="tabsLeft tabsLeftDisabled" -->
				<div class="tabsRight">right</div><!-- 禁用只需要添加一个样式 class="tabsRight tabsRightDisabled" -->
				<div class="tabsMore">more</div>
			</div>
			<ul class="tabsMoreList">
				<li><a href="javascript:void(0);">主页</a></li>
			</ul>
			<div class="navTab-panel tabsPageContent layoutBox">
				<div class="page unitBox">
					<div class="accountInfo">
						<div class="right">
							<p><fmt:formatDate value="<%=new Date() %>" pattern="yyyy-MM-dd EEEE"/></p>
						</div>
						<p><span>欢迎, ${currentUser.realname}.</span></p>
					</div>
					<div class="pageFormContent" layouth="80">
						<fieldset>
							<legend>基本信息</legend>
							<dl>
								<dt>用户名：</dt>
								<dd><span class="unit">${currentUser.username}</span></dd>
							</dl>
							<dl>
								<dt>真名：</dt>
								<dd><span class="unit">${currentUser.realname}</span></dd>
							</dl>
						</fieldset>
					</div>
				</div>
			</div>
		</div>
		</div>

	</div>

	<div id="footer">版权所有：</div>
	
</body>
</html>
