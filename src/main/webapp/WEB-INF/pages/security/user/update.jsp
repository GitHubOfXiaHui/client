<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>

<div class="pageContent">
	<form method="post" action="${contextPath}/security/user/update" class="required-validate pageForm" onsubmit="return validateCallback(this, dialogReloadNavTab);">
	<input type="hidden" name="id" value="${id}" />
	<div class="pageFormContent" layoutH="58">
		<p>角色：</p>
		<p>
			<c:forEach var="role" items="${allRoles}" varStatus="i">
				<input type="checkbox" name="roles[]" value="${role.code}" <c:if test="${roles.contains(role)}">checked</c:if> />${role.value}&nbsp;&nbsp;&nbsp;
				<c:if test="${i.count % 4 == 0}"><br /><br /></c:if>
			</c:forEach>
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