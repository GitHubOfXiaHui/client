<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>

<div class="pageContent">
	<form method="post" action="${contextPath }/security/user/changePassword" class="required-validate pageForm" onsubmit="return validateCallback(this, dialogReloadNavTab);">
	<div class="pageFormContent" layoutH="58">
		<p>
			<label>原密码：</label>
			<input type="password" class="validate[required,minSize[6],maxSize[20]] required" name="oldPassword" size="20"/>
		</p>
		<p>
			<label>新密码：</label>
			<input type="password" id="password" class="validate[required,minSize[6],maxSize[20]] required" name="newPassword" size="20"/>
		</p>
		<p>
			<label>确认密码：</label>
			<input type="password" class="validate[required,equals[password]] required" name="confirm" size="20"/>
		</p>
	</div>
			
	<div class="formBar">
		<ul>
			<li><div class="button"><div class="buttonContent"><button type="submit">确定</button></div></div></li>
			<li><div class="button"><div class="buttonContent"><button type="button" class="close">关闭</button></div></div></li>
		</ul>
	</div>
	</form>
</div>