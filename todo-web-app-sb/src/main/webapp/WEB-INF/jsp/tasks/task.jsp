<%@ include file="../common/header.jspf"%>
<%@ include file="../common/navigation.jspf"%>
<div class="container">
	<div class="row">
		<div class="col-md-6 col-md-offset-3 ">
			<div class="panel panel-primary">
				<div class="panel-heading">Add TASK</div>

				<div class="panel-body">

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