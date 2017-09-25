<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>

<div class="pageContent">
	<form method="post" action="${contextPath}/post/update" class="required-validate pageForm" onsubmit="return validateCallback(this, dialogReloadNavTab);">
	<input type="hidden" name="id" value="${post.id}" />
	<input type="hidden" name="author" value="${post.author}" />
	<input type="hidden" name="createTime" value="${post.createTime}" />
	<div class="pageFormContent" layoutH="58">
		<p>
			<label>标题：</label>
			<input type="text" class="validate[required,maxSize[20]] required" name="title" value="${post.title}" size="20"/>
		</p>
		<p>
			<label>内容：</label>
			<textarea class="validate[required,maxSize[255]] required" name="content" cols="32" rows="10" maxlength="255">${post.content}</textarea>
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