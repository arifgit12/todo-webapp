<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>

<div class="container">
	<br />

	<br>
	<div class="panel panel-primary">
		<div class="panel-heading">
			<h3>List of all TODO's</h3>
		</div>
		<div class="panel-body">
			<table class="table table-striped">
				<thead>
					<tr>
						<th style="padding-left: 30px;">#</th>
						<th>Description</th>
						<th>Target Date</th>
						<th>Completed</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${todos}" var="todo">
						<tr>
							<td style="padding-left: 30px;" >
								<c:choose>
									<c:when test="${!todo.status}">
										<input type="checkbox" id="chk" name="chkComplete" onClick="isComplete(this)" value="${todo.id}" />
									</c:when>
									<c:otherwise>
										<input type="checkbox" id="chk" name="chkComplete" onClick="isComplete(this)" value="${todo.id}" checked="checked"/>
									</c:otherwise>
								</c:choose>
							</td>
							<td>${todo.description}</td>
							<td><fmt:formatDate value="${todo.targetDate}" pattern="dd/MM/yyyy" /></td>
							<td>${todo.status}</td>
							<td>
								<c:choose>
									<c:when test="${!todo.status}">
										<a role="button" class="btn btn-warning" href="/todo?id=${todo.id}">View</a>
										<a role="button" class="btn btn-success" href="/update-todo?id=${todo.id}">Update</a>
										<a role="button" class="btn btn-warning" href="/delete-todo?id=${todo.id}">Delete</a>
									</c:when>
									<c:otherwise>

									</c:otherwise>
								</c:choose>

							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>

</div>
<%@ include file="common/footer.jspf"%>
<script>
    function isComplete(completeCheckBox) {

        var isChecked = false;
        if (completeCheckBox.checked == true) {
            isChecked = true;
        } else {
            isChecked = false;
        }
		$.ajax({
			url: "/checked/" + completeCheckBox.value,
			type: "GET",
			data: 'complete=' + isChecked,
			success: function () {
				location.reload();
			},
			error: function () {
				//alert('Unable to Call Checked Box');
			}
		});
    }
</script>