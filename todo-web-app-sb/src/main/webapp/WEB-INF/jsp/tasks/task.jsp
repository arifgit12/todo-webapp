<%@ include file="../common/header.jspf"%>
<%@ include file="../common/navigation.jspf"%>
<div class="container">
	<br />
	<div class="row">
		<div class="col-md-6 col-md-offset-3 ">
			<div class="card">
				<div class="card-header text-primary">Add TASK</div>
				<div class="card-body">
					<form:form method="post" modelAttribute="task">
						<form:hidden path="id" />
						<fieldset class="form-group">
							<form:label path="taskName">Task Name</form:label>
							<form:input path="taskName" type="text" class="form-control"
										required="required" />
							<form:errors path="taskName" cssClass="text-warning" />
						</fieldset>

						<fieldset class="form-group">
							<form:label path="description">Description</form:label>
							<form:textarea path="description" type="text" class="form-control"
										required="required" />
							<form:errors path="description" cssClass="text-warning" />
						</fieldset>
						<button type="submit" class="btn btn-success">Save</button>
						<a class="btn btn-warning" href="/list-tasks">Cancel</a>
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