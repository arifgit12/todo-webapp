<%@ include file="../common/header.jspf"%>
<%@ include file="../common/navigation.jspf"%>

<div class="container">
	<br />
	<table class="table table-striped">
		<caption>Your Todos are</caption>
		<thead class="thead-light">
			<tr>
				<th>#</th>
				<th>Description</th>
				<th>Due Date</th>
				<th>Completed</th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${todos}" var="todo">
				<tr>
					<td style="padding-left: 30px;" >
						<c:choose>
					 		<c:when test="${todo.done}">
							   <input type="checkbox" id="chk" name="chkComplete" onClick="isComplete(this)" value="${todo.id}" checked="checked"/>
						 	</c:when>
						 	<c:otherwise>
					 			<input type="checkbox" id="chk" name="chkComplete" onClick="isComplete(this)" value="${todo.id}"/>
						 	</c:otherwise>
					  	</c:choose>
					</td>
					<td>${todo.desc}</td>
					<td><fmt:formatDate pattern="dd/MM/yyyy" value="${todo.targetDate}" /></td>
					<td>${todo.done}</td>
					<td>
						<a type="button" class="btn btn-primary" href="<c:url value="/update-todo?id=${todo.id}"/>">Edit</a>
						<a type="button" class="btn btn-warning" href="<c:url value="/delete-todo?id=${todo.id}"/>">Delete</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div>
		<a type="button" class="btn btn-success" href="<c:url value="/add-todo"/>">Add</a>
	</div>
</div>

<%@ include file="../common/footer.jspf"%>
<script>
	function isComplete(completeCheckBox, value) {
		var isChecked = false;
		if (completeCheckBox.checked == true) {
			isChecked = true;
	  	} else {
			 isChecked = false;
	  	}
		//$('#isCompleteCheck').prop('checked', isChecked);
		$.ajax({
			type: "get",
			url: "/checked",
			data:'id=' + completeCheckBox.value + '&complete=' + isChecked,
			success: function(msg) {
				console.log(msg.value);
			}
		});
	}
</script>