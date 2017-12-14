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
			<%-- <shiro:hasPermission name="user:view">
				<li><a class="icon" target="dialog" mask="true" width="400" height="350" href="${contextPath}/security/user/view/{slt_uid}"><span>查看用户</span></a></li>
			</shiro:hasPermission> --%>
			<shiro:hasPermission name="user:create">
				<li><a class="add" target="dialog" mask="true" width="400" height="420" href="${contextPath}/security/user/create"><span>新建用户</span></a></li>
			</shiro:hasPermission>
			<shiro:hasPermission name="user:role:update">
				<li><a class="edit" target="dialog" mask="true" width="400" height="350" href="${contextPath}/security/user/update/{slt_uid}"><span>设置角色</span></a></li>
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
				<th>所在部门</th>
				<th>职位</th>
				<th>工号</th>
				<th>电话</th>
				<th>邮箱</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="user" items="${users}" varStatus="i">
			<tr target="slt_uid" rel="${user.id}">
				<td>${i.count}</td>
				<td><c:if test="${user.supervisor}">★</c:if></td>
				<td>${user.username}</td>
				<td>${user.nickname}</td>
				<td>${user.department}</td>
				<td>${user.jobTitle}</td>
				<td>${user.jobNumber}</td>
				<td>${fn:substring(user.phone, 0, 3)}****${fn:substring(user.phone, 7, 11)}</td>
				<td>${user.email}</td>
				<td>
				<c:forEach var="role" items="${user.roles}" varStatus="i">
					${role.value}
					<c:if test="${!i.last}">
						,&nbsp;
					</c:if>
				</c:forEach>
				</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	<!-- 分页 -->
	<keta:pagination page="${page}"/>
</div>