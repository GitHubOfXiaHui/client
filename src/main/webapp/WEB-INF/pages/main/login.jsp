<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>接入服务平台</title>
<link href="${contextPath}/static/styles/dwz/themes/css/login.css" rel="stylesheet" type="text/css" media="screen"/>

<style>
  html, body {width:100%;	height:100%; overflow:scroll; overflow:hidden}
</style>
<!-- form验证 -->
<link rel="stylesheet" href="${contextPath}/static/styles/validationEngine/css/validationEngine.jquery.css" type="text/css"/>
<script src="${contextPath}/static/styles/jquery/jquery-1.7.2.min.js" type="text/javascript"></script>
<script src="${contextPath}/static/styles/validationEngine/js/languages/jquery.validationEngine-zh_CN.js" type="text/javascript" charset="utf-8"></script>
<script src="${contextPath}/static/styles/validationEngine/js/jquery.validationEngine-2.6.4.js" type="text/javascript" charset="utf-8"></script>
<script>
    jQuery(document).ready(function(){
        jQuery("#formID").validationEngine();
    });
</script>

</head>
<body>
<div id="wrapper">
  <img src="${contextPath}/static/styles/dwz/themes/newcard/images/login/loginbg.jpg" alt="" class="imgWrap" />
  <div class="wrapper">
     <div class="wrap">
     <form method="post" action="${contextPath}/main/login" id="formID" >
       <div class="loginWrap">
         <div class="loginForm">
           <p><input type="text" name="username" class="text" maxlength="15" placeholder="用户名"/></p>
           <p class="pad_T_25"><input type="password" name="password" class="text1" maxlength="15" placeholder="密码" /></p>
           <p class="pad_T_25"><input type="submit" class="btn01" value="登 录" /></p>
           <c:if test="${error != null }">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<p style="color: red; margin-left: 80px;">${error }</p>
			</c:if>
				
         </div>

       </div>
       </form>
     </div>
  </div>
</div>
</body>
</html>
