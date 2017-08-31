<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<keta:paginationForm action="${contextPath }/post/list" page="${page}" onsubmit="return navTabSearch(this);">
	<input type="hidden" name="keyword" value="${keyword}"/>
</keta:paginationForm>

<form method="post" action="${contextPath }/post/list" onsubmit="return navTabSearch(this);">
	<div class="pageHeader">
		<div class="searchBar">
			<ul class="searchContent">
				<li>
					<label>关键词：</label>
					<input type="text" name="keyword" value="${keyword}"/>
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
			<shiro:hasPermission name="post:create">
				<li><a class="add" target="dialog" mask="true" width="400" height="300" href="${contextPath}/post/create"><span>发帖</span></a></li>
			</shiro:hasPermission>
			<shiro:hasPermission name="post:update">
				<li><a class="edit" target="dialog" mask="true" width="500" height="400" href="${contextPath}/post/update/{slt_uid}"><span>编辑</span></a></li>
			</shiro:hasPermission>
			<shiro:hasPermission name="post:delete">
				<li><a class="delete" target="ajaxTodo" href="${contextPath}/post/delete/{slt_uid}" title="确认要删除选定的帖子?"><span>删除</span></a></li>
			</shiro:hasPermission>
		</ul>
	</div>
	
	<table class="table" layoutH="137" width="100%">
		<thead>
			<tr>
				<th width="22">#</th>
				<th>标题</th>
				<th>内容</th>
				<th>作者</th>
				<th>创建时间</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="post" items="${posts}" varStatus="i">
			<tr target="slt_uid" rel="${post.id}">
				<td>${i.count}</td>
				<td>${post.title}</td>
				<td>${post.content}</td>
				<td>${post.author}</td>
				<td><fmt:formatDate value="${post.createDate}" pattern="yyyy-MM-dd EEEE" /></td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	<!-- 分页 -->
	<keta:pagination page="${page}"/>
</div>