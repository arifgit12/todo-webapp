<%@ include file="../common/header.jspf"%>
<%@ include file="../common/navigation.jspf"%>

<div class="container">
	<br />
	<div>
		<a role="button" class="btn btn-success" href="<c:url value="/add-todo"/>">Add</a>
	</div>
	<br />
	<div class="card card-body" id="duetodos_table">
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
			<tbody id="todosDueBody">
				<c:forEach items="${todos}" var="todo">
					<c:if test="${!todo.status}">
						<tr>
							<td style="padding-left: 30px;" >
								<input type="checkbox" id="chk" name="chkComplete" onClick="isComplete(this)" value="${todo.id}"/>
							</td>
							<td>${todo.desc}</td>
							<td><fmt:formatDate pattern="dd/MM/yyyy" value="${todo.targetDate}" /></td>
							<td>${todo.status}</td>
							<td>
								<a role="button" class="btn btn-primary" href="<c:url value="/update-todo?id=${todo.id}"/>">Edit</a>
								<a role="button" class="btn btn-warning" href="<c:url value="/delete-todo?id=${todo.id}"/>">Delete</a>
							</td>
						</tr>
					</c:if>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<br />

	<div>
		<button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#collapseComplete" aria-expanded="false" aria-controls="collapseComplete">
			Show Completed Task
		</button>
	</div>
	<br />
	<div class="collapse" id="collapseComplete">
		<div class="card card-body" id="todos_table">
			<table class="table table-striped">
				<thead class="thead-light">
				<tr>
					<th>#</th>
					<th>Description</th>
					<th>Due Date</th>
					<th>Completed</th>
				</tr>
				</thead>
				<tbody id="todosCompleteBody">
					<c:forEach items="${todos}" var="todo">
						<c:if test="${todo.status}">
							<tr>
								<td style="padding-left: 30px;" >
									<input type="checkbox" id="chk" name="chkComplete" onClick="isComplete(this)" value="${todo.id}" checked="checked"/>
								</td>
								<td>${todo.desc}</td>
								<td><fmt:formatDate pattern="dd/MM/yyyy" value="${todo.targetDate}" /></td>
								<td><fmt:formatDate pattern="dd/MM/yyyy" value="${todo.updatedDate}" /></td>
							</tr>
						</c:if>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</div>

<%@ include file="../common/footer.jspf"%>
<script>
	function isComplete(completeCheckBox) {
		var isChecked = false;
		if (completeCheckBox.checked == true) {
			isChecked = true;
	  	} else {
			 isChecked = false;
	  	}

		$.ajax({
			type: "get",
			url: "/checked",
			data:'id=' + completeCheckBox.value + '&complete=' + isChecked,
			success: function(response) {
				getDueTodos(response);
				getCompleteTodos(response);
			}
		});
	}
	
	function getDueTodos(response) {
		var numComplete = 0;
		var todosTableHTML = '';
		if (response.length > 0) {
				$.each(response, function (key,value) {
				 	if(!value.status) {
				 		numComplete = numComplete + 1;
						todosTableHTML +=
							'<tr>' +
								'<td>' + '<input type="checkbox" id="chkx" name="chkxComplete" onClick="isComplete(this)" value=' + value.id + ' />' + '</td>' +
  							 	'<td>' + value.desc + '</td>' +
  							 	'<td>' + formatDate(new Date(value.targetDate)) + '</td>' +
  							 	'<td>' + value.status + '</td>' +
  							 	'<td>' +
								'<a role="button" class="btn btn-primary" href="<c:url value="/update-todo?id=' + value.id + '"/>"> Edit </a> ' +
								'<a role="button" class="btn btn-warning" href="<c:url value="/delete-todo?id=' + value.id + '"/>"> Delete </a> ' +
							'</td>' +
						'</tr>';
				 	}
			 });
		}

		$('#todosDueBody').empty();

		if(numComplete > 0) {
			$("#todosDueBody").append( todosTableHTML );
		} else {
			$("#todosDueBody").append( '<br /> <p> All Task Completed </p>' );
		}
	}

	function getCompleteTodos(response) {
		var numComplete = 0;
		var todosTableHTML = '';
		if (response.length > 0) {
				$.each(response, function (key,value) {
				 	if(value.status) {
				 		numComplete = numComplete + 1;
						todosTableHTML +=
							'<tr>' +
								'<td>' + '<input type="checkbox" id="chkx" name="chkxComplete" onClick="isComplete(this)" value=' + value.id + ' checked="checked" />' + '</td>' +
  							 	'<td>' + value.desc + '</td>' +
  							 	'<td>' + formatDate(new Date(value.targetDate)) + '</td>' +
  							 	'<td>' + formatDate(new Date(value.updatedDate)) + '</td>' +
						'</tr>';
				 	}
			 });
		}

		$('#todosCompleteBody').empty();

		if(numComplete > 0) {
			$("#todosCompleteBody").append( todosTableHTML );
		} else {
			$("#todosCompleteBody").append( '<br /> <p> Complete Todos not available </p>'  );
		}
	}

	function formatDate(date) {
		  var monthNames = [
		    "January", "February", "March",
		    "April", "May", "June", "July",
		    "August", "September", "October",
		    "November", "December"
		  ];

		  var monthNumbers = [
			    "1", "2", "3",
			    "4", "5", "6", "7",
			    "8", "9", "10",
			    "11", "12"
			  ];

		  var day = date.getDate();
		  var monthIndex = date.getMonth();
		  var year = date.getFullYear();

		  return day + '/' + monthNumbers[monthIndex] + '/' + year;
	}
</script>