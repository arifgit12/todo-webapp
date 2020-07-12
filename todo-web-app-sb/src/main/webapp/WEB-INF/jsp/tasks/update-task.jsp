<%@ include file="../common/header.jspf"%>
<%@ include file="../common/navigation.jspf"%>
<div class="container">
    <div class="row">
        <div class="col-md-6 col-md-offset-3 ">
            <br />
            <div class="card">
                <c:choose>
                    <c:when test="${task != null}">
                        <div class="card-header">Update TASK</div>
                        <div class="card-body">
                            <form:form action="/update-task/${task.id}" method="post" modelAttribute="task">
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
                                <button type="submit" class="btn btn-success">Update</button>
                                <a role="button" class="btn btn-warning" href="<c:url value="/list-tasks"/>">Cancel</a>
                            </form:form>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="card-header">TASK NOT FOUND</div>
                    </c:otherwise>
                </c:choose>
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