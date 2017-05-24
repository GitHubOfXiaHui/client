<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>

<div class="pageContent">
	<form method="post" action="${contextPath }/security/user/create" class="required-validate pageForm" onsubmit="return validateCallback(this, dialogReloadNavTab);">
	<div class="pageFormContent" layoutH="58">
		<p>
			<label>用户名：</label>
			<input type="text" class="validate[required,maxSize[10]] required" name="username" size="20"/>
		</p>
		<p>
			<label>真名：</label>
			<input type="text" class="validate[required,maxSize[10]] required" name="realname" size="20"/>
		</p>
		<p>
			<label>密码：</label>
			<input type="password" id="password" class="validate[required,minSize[6],maxSize[20]] required" name="password" size="20"/>
		</p>
		<p>
			<label>确认密码：</label>
			<input type="password" class="validate[required,equals[password]] required" name="confirm" size="20"/>
		</p>
		<p>
			<label>&nbsp;</label>
			<input type="checkbox" name="isAdmin" value="true">是否为管理员
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