<%@ include file="../common/header.jspf"%>
<%@ include file="../common/navigation.jspf"%>
<div class="container">
    <div class="row">
        <div class="col-md-6 col-md-offset-3 ">
            <div class="panel panel-primary">
                <br />
                <c:if test="${task != null}">
                    <div class="panel-heading">Update TASK</div>
                    <div class="panel-body">
                        <form:form action="update-task?taskId=${task.id}" method="post" modelAttribute="task">
                            <form:hidden path="id" />
                            <fieldset class="form-group">
                                <form:label path="taskName">Task Name</form:label>
                                <form:input path="taskName" type="text" class="form-control"
                                            required="required" />
                                <form:errors path="taskName" cssClass="text-warning" />
                            </fieldset>

                            <fieldset class="form-group">
                                <form:label path="description">Description</form:label>
                                <form:input path="description" type="text" class="form-control"
                                            required="required" />
                                <form:errors path="description" cssClass="text-warning" />
                            </fieldset>
                            <a role="button" class="btn btn-warning" href="<c:url value="/list-tasks"/>">Cancel</a>
                            <button type="submit" class="btn btn-success">Update</button>
                        </form:form>
                    </div>
                </c:if>
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