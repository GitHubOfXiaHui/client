<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>

<div class="pageContent">
	<form method="post" action="${contextPath }/security/user/create" class="required-validate pageForm" onsubmit="return validateCallback(this, dialogReloadNavTab);">
	<div class="pageFormContent" layoutH="58">
		<p>
			<label>用户名：</label>
			<input type="text" class="validate[required,maxSize[20]] required" name="username" size="20"/>
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
			<label>昵称：</label>
			<input type="text" class="validate[required,maxSize[20]] required" name="nickname" size="20"/>
		</p>
		<p>
			<label>所在部门：</label>
			<input type="text" class="validate[maxSize[20]]" name="department" size="20"/>
		</p>
		<p>
			<label>职位：</label>
			<input type="text" class="validate[maxSize[20]]" name="jobTitle" size="20"/>
		</p>
		<p>
			<label>工号：</label>
			<input type="text" class="validate[maxSize[20]]" name="jobNumber" size="20"/>
		</p>
		<p>
			<label>电话：</label>
			<input type="text" class="validate[maxSize[20]]" name="phone" size="20"/>
		</p>
		<p>
			<label>邮箱：</label>
			<input type="text" class="validate[maxSize[45]]" name="email" size="20"/>
		</p>
		<p>
			<label>&nbsp;</label>
			<input type="checkbox" name="supervisor" value="true" />是否为超级管理员
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