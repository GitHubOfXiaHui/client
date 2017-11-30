<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<keta:paginationForm action="${contextPath }/security/role/list" page="${page}" onsubmit="return navTabSearch(this);">
	<input type="hidden" name="rolename" value="${rolename}"/>
</keta:paginationForm>

<form method="post" action="${contextPath }/security/role/list" onsubmit="return navTabSearch(this);">
	<div class="pageHeader">
		<div class="searchBar">
			<ul class="searchContent">
				<li>
					<label>角色名：</label>
					<input type="text" name="rolename" value="${rolename}"/>
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
			<shiro:hasPermission name="role:create">
				<li><a class="add" target="dialog" mask="true" width="400" height="300" href="${contextPath}/security/role/create"><span>新建角色</span></a></li>
			</shiro:hasPermission>
			<shiro:hasPermission name="role:privilege:update">
				<li><a class="edit" target="dialog" mask="true" width="400" height="300" href="${contextPath}/security/role/update/{slt_uid}"><span>设置权限</span></a></li>
			</shiro:hasPermission>
			<shiro:hasPermission name="user:delete">
				<li><a class="delete" target="ajaxTodo" href="${contextPath}/security/role/delete/{slt_uid}" title="确认要删除选定的角色?"><span>删除角色</span></a></li>
			</shiro:hasPermission>
		</ul>
	</div>
	
	<table class="table" layoutH="137" width="100%">
		<thead>
			<tr>
				<th width="22">#</th>
				<th>角色编号</th>
				<th>角色名</th>
				<th>角色详情</th>
				<th>角色权限</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="role" items="${roles}" varStatus="i">
			<tr target="slt_uid" rel="${role.id}">
				<td>${i.count}</td>
				<td>${role.code}</td>
				<td>${role.name}</td>
				<td>${role.value}</td>
				<td>
				<c:forEach var="permission" items="${role.permissions}" varStatus="i">
					${permission.value}
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