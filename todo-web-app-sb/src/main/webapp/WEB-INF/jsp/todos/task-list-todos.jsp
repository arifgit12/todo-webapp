<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../common/header.jspf"%>
<%@ include file="../common/navigation.jspf"%>

<div class="container">
	<br />
	<c:if test="${taskId != null}">
		<div>
			<a role="button" class="btn btn-primary btn-md" href="/add-todo?taskId=${taskId}">Add Todo</a>
		</div>
		<br>
		<div class="panel panel-primary">
			<div class="panel-heading">
				<h3>${taskname} TODO's</h3>
			</div>
			<div class="panel-body">
				<table class="table table-striped">
					<thead>
					<tr>
						<th>Description</th>
						<th>Target Date</th>
						<th>Completed</th>
						<th>Action</th>
					</tr>
					</thead>
					<tbody>
					<c:forEach items="${todos}" var="todo">
						<tr>
							<td>${todo.description}</td>
							<td><fmt:formatDate value="${todo.targetDate}" pattern="dd/MM/yyyy" /></td>
							<td>${todo.status}</td>
							<td>
								<a role="button" class="btn btn-success" href="/todo?id=${todo.id}">View</a>
								<c:if test = "${todo.status}">
									<a role="button" class="btn btn-warning disabled" href="/update-todo?id=${todo.id}">Update</a>
									<a role="button" class="btn btn-danger disabled" href="/delete-todo?id=${todo.id}">Delete</a>
								</c:if>
								<c:if test = "${!todo.status}">
									<a role="button" class="btn btn-warning" href="/update-todo?id=${todo.id}">Update</a>
									<a role="button" class="btn btn-danger" href="/delete-todo?id=${todo.id}">Delete</a>
								</c:if>
							</td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</c:if>
	<c:if test="${taskId == null}">
		You are at wrong place
	</c:if>
</div>
<%@ include file="../common/footer.jspf"%>