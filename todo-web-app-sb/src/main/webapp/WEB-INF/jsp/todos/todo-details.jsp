<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../common/header.jspf"%>
<%@ include file="../common/navigation.jspf"%>
<div class="container">
	<div class="row">
		<div class="col-md-6 col-md-offset-3 ">
			<br />
			<h1>Task Name: ${taskname} </h1>
			<h2>Details - ${todo.description}</h2>
			<h3>Target date - <fmt:formatDate pattern="dd/MM/yyyy" value="${todo.targetDate}" /></h3>
			<h3>Status - ${todo.status}</h3>
			<br />
			<c:if test="${todo.comments.size() > 0}">
				<h3>View Comments</h3>
				<table>
					<c:forEach items="${todo.comments}" var="comment">
						<tr>
							<td>#</td>
							<td>${comment.content}</td>
						</tr>
					</c:forEach>
				</table>
			</c:if>
			<br />
			<c:if test = "${!todo.status}">
				<div class="panel panel-primary">
					<div class="panel-heading">Add Comment</div>
					<br />
					<div class="panel-body">

						<form:form method="post" id="commentForm" modelAttribute="commentDTO">
							<input type=hidden id="todoId" name="todoId"
								   value="${todo.id}"/>
							<fieldset class="form-group">
								<form:textarea id="content" placeholder="comment..." path="content" class="form-control"
											   required="required" />
								<form:errors path="content" cssClass="text-warning" />
							</fieldset>
							<button type="submit" class="btn btn-success">Comment</button>
						</form:form>
					</div>
				</div>
			</c:if>
		</div>
	</div>
</div>
<%@ include file="../common/footer.jspf"%>
<script>
	$("#commentForm").submit(function (event) {
		// Stop form from submitting normally
        event.preventDefault();

        // Get some values from elements on the page:
        var jsonData = JSON.stringify({
            content: $('#content').val(),
            todoId: $('#todoId').val()
        });
        console.log(jsonData);
        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "<c:url value="/comment"/>",
            data: jsonData,
            dataType: "json",
            success: function (data) {
                //console.log(data);
                location.reload();
            }
		});
	})
</script>