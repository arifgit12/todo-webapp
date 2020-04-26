<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>

<div class="container">
	<br />

	<br>
	<div class="card">
		<div class="card-header">
			<h3>List of all TODO's</h3>
		</div>
		<div class="card-body">
			<table class="table table-striped">
				<thead>
					<tr>
						<th style="padding-left: 30px;">#</th>
						<th>Description</th>
						<th>Target Date</th>
						<th>Completed</th>
						<th>Action</th>
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
								<a role="button" class="btn btn-warning" href="/todo?id=${todo.id}">View</a>
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