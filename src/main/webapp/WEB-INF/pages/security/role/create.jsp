<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>

<div class="pageContent">
	<form method="post" action="${contextPath }/security/role/create" class="required-validate pageForm" onsubmit="return validateCallback(this, dialogReloadNavTab);">
	<div class="pageFormContent" layoutH="58">
		<p>
			<label>角色编号：</label>
			<input type="text" class="validate[required,digits] required" name="code" size="30"/>
		</p>
		<p>
			<label>角色名：</label>
			<input type="text" class="validate[required,maxSize[30]] required" name="name" size="30"/>
		</p>
		<p>
			<label>角色值：</label>
			<input type="text" class="validate[required,maxSize[30]] required" name="value" size="30"/>
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