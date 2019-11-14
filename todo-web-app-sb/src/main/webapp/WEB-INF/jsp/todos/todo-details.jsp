<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../common/header.jspf"%>
<%@ include file="../common/navigation.jspf"%>
<div class="container">
	<div class="row">
		<div class="col-md-6 col-md-offset-3 ">
			<br />
			<h1>Task Name: ${taskname} </h1>
			<h2>Details - ${todo.description}</h2>
			<h3>Target date - ${todo.targetDate}</h3>
			<h3>Status - ${todo.status}</h3>
			<br />
			<c:if test="${todo.comments.size() > 0}">
				<h3>View Comments</h3>
				<table>
					<c:forEach items="${todo.comments}" var="comment">
						<tr>
							<td>${comment.id}</td>
							<td>${comment.content}</td>
						</tr>
					</c:forEach>
				</table>
			</c:if>
			<br />
			<div class="panel panel-primary">
				<div class="panel-heading">Add Comment</div>
				<div class="panel-body">

				</div>
			</div>
		</div>
	</div>
</div>
<%@ include file="../common/footer.jspf"%>