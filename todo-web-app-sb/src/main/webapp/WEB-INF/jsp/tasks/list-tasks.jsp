<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../common/header.jspf"%>
<%@ include file="../common/navigation.jspf"%>

<div class="container">
	<br />
	<div>
		<a role="button" class="btn btn-primary btn-md" href="/add-task">Add Task</a>
	</div>
	<br>
	<div class="panel panel-primary">
		<div class="panel-heading">
			<h3>List of TASK's</h3>
		</div>
		<div class="panel-body">
			<table class="table table-striped">
				<thead>
					<tr>
						<th>Name</th>
						<th>Description</th>
						<th>Action</th>
						<th>Add Todo</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${tasks}" var="task">
						<tr>
							<td>${task.taskName}</td>
							<td>${task.description}</td>
							<td>
								<a role="button" class="btn btn-warning" href="#">Update</a>
							</td>
							<td>
								<a role="button" class="btn btn-success" href="/task-add-todo?taskId=${task.id}">Add</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</div>
<%@ include file="../common/footer.jspf"%>