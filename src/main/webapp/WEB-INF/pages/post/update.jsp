<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>

<div class="pageContent">
	<form method="post" action="${contextPath}/post/update" class="required-validate pageForm" onsubmit="return validateCallback(this, dialogReloadNavTab);">
	<input type="hidden" name="id" value="${post.id}" />
	<div class="pageFormContent" layoutH="58">
		<p>
			<label>标题：</label>
			<input type="text" class="validate[required,maxSize[20]] required" name="title" value="${post.name}" size="20"/>
		</p>
		<p>
			<label>内容：</label>
			<textarea class="validate[required,maxSize[255]] required" name="content" value="${post.content}" cols="32" rows="10" maxlength="255"></textarea>
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