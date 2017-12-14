<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>

<div class="pageContent">
	<form>
	<div class="pageFormContent" layoutH="58">
		<p>
			<label>用户名：</label>
			<input type="text" class="validate[maxSize[20]]" value="${user.username}" size="20"/>
		</p>
		<p>
			<label>昵称：</label>
			<input type="text" class="validate[maxSize[20]]" value="${user.nickname}" size="20"/>
		</p>
		<p>
			<label>所在部门：</label>
			<input type="text" class="validate[maxSize[20]]" value="${user.department}" size="20"/>
		</p>
		<p>
			<label>职位：</label>
			<input type="text" class="validate[maxSize[20]]" value="${user.jobTitle}" size="20"/>
		</p>
		<p>
			<label>工号：</label>
			<input type="text" class="validate[maxSize[20]]" value="${user.jobNumber}" size="20"/>
		</p>
		<p>
			<label>电话：</label>
			<input type="text" class="validate[maxSize[20]]" value="${user.phone}" size="20"/>
		</p>
		<p>
			<label>邮箱：</label>
			<input type="text" class="validate[maxSize[45]]" value="${user.email}" size="20"/>
		</p>
		<p>
			<label>&nbsp;</label>
			<c:if test="${user.supervisor}">超级管理员</c:if>
		</p>
	</div>
			
	<div class="formBar">
		<ul>
			<li><div class="button"><div class="buttonContent"><button type="button" class="close">关闭</button></div></div></li>
		</ul>
	</div>
	</form>
</div>