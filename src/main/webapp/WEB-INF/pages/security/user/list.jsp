<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<keta:paginationForm action="${contextPath }/security/user/list" page="${page}" onsubmit="return navTabSearch(this);">
	<input type="hidden" name="username" value="${username}"/>
</keta:paginationForm>

<form method="post" action="${contextPath }/security/user/list" onsubmit="return navTabSearch(this);">
	<div class="pageHeader">
		<div class="searchBar">
			<ul class="searchContent">
				<li>
					<label>用户名：</label>
					<input type="text" name="username" value="${username}"/>
				</li>
			</ul>
			<div class="subBar">
				<ul>						
					<li><div class="button"><div class="buttonContent"><button type="submit">搜索</button></div></div></li>
				</ul>
			</div>
		</div>
	</div>
</form>

<div class="pageContent">

	<div class="panelBar">
		<ul class="toolBar">
			<shiro:hasPermission name="user:create">
				<li><a class="add" target="dialog" mask="true" width="400" height="300" href="${contextPath}/security/user/create"><span>新建用户</span></a></li>
			</shiro:hasPermission>
			<shiro:hasPermission name="privileges:update">
				<li><a class="edit" target="dialog" mask="true" width="400" height="300" href="${contextPath}/security/user/update/{slt_uid}"><span>设置角色</span></a></li>
			</shiro:hasPermission>
			<shiro:hasPermission name="user:delete">
				<li><a class="delete" target="ajaxTodo" href="${contextPath}/security/user/delete/{slt_uid}" title="确认要删除选定的用户?"><span>删除用户</span></a></li>
			</shiro:hasPermission>
		</ul>
	</div>
	
	<table class="table" layoutH="137" width="100%">
		<thead>
			<tr>
				<th width="22">#</th>
				<th width="70">超级管理员</th>
				<th>用户名</th>
				<th>昵称</th>
				<th>角色</th>
				<th>权限</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="user" items="${users}" varStatus="i">
			<tr target="slt_uid" rel="${user.id}">
				<td>${i.count}</td>
				<td al><c:if test="${user.supervisor}">★</c:if></td>
				<td>${user.username}</td>
				<td>${user.realname}</td>
				<td>
				<c:forEach var="role" items="${user.roles}" varStatus="status">
					${role.description}
					<c:if test="${!status.last}">
						,&nbsp;
					</c:if>
				</c:forEach>
				</td>
				<td>
				<c:forEach var="role" items="${user.roles}" varStatus="i">
					<c:forEach var="permission" items="${role.permissions}" varStatus="j">
						${permission.description}
						<c:if test="${!i.last || !j.last}">
							,&nbsp;
						</c:if>
					</c:forEach>
				</c:forEach>
				</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	<!-- 分页 -->
	<keta:pagination page="${page}"/>
</div>