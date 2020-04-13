<%@ include file="../common/header.jspf"%>
<%@ include file="../common/navigation.jspf"%>
<div class="container">
	<div class="row">
		<div class="col-md-6 col-md-offset-3 ">
			<br />
			Task Name: ${taskname}
			<div class="panel panel-primary">
				<div class="panel-heading">Add TODO</div>
				<div class="panel-body">
					<form:form method="post" modelAttribute="todo">
						<form:hidden path="todoId" />
						<form hidden path="taskId" />
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<form:label path="description">Description</form:label>
							<form:input type="text" path="description" class="form-control" />
							<form:errors path="description" cssClass="text-warning"></form:errors>
						</div>

						<div class="form-group ${status.error ? 'has-error' : ''}">
							<form:label path="targetDate">Target Date</form:label>
							<form:input type="text" path="targetDate" class="form-control" required="required"/>
							<form:errors path="targetDate" cssClass="text-warning"></form:errors>
						</div>

						<button type="submit" class="btn btn-success">Save</button>
						<a role="button" class="btn btn-warning" href="/list-tasks">Cancel</a>
					</form:form>
				</div>
			</div>
		</div>
	</div>
</div>
<%@ include file="../common/footer.jspf"%>
<script>
    $('#targetDate').datepicker({
        format : 'dd/mm/yyyy',
        startDate: '0d',
        todayHighlight: true
    });
</script>