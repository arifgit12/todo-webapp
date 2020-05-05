<%@ include file="../common/header.jspf"%>
<%@ include file="../common/navigation.jspf"%>
<div class="container">
    <br />
    <div class="row">
        <div class="col-md-6 col-md-offset-3 ">
            <div class="card">
                <div class="card-header">Update Password</div>
                <div class="card-body">
                    <form:form method="post" action="update-password" modelAttribute="pwd">
                        <div class="form-group ${status.error ? 'has-error' : ''}">
                            <form:label path="currentPassword">Current Password</form:label>
                            <form:input type="password" path="currentPassword" class="form-control" required="required" />
                            <form:errors path="currentPassword" cssClass="text-warning"></form:errors>
                        </div>

                        <div class="form-group ${status.error ? 'has-error' : ''}">
                            <form:label path="newPassword">Password</form:label>
                            <form:input type="password" path="newPassword" class="form-control" required="required"/>
                            <form:errors path="newPassword" cssClass="text-warning"></form:errors>
                        </div>

                        <div class="form-group ${status.error ? 'has-error' : ''}">
                            <form:label path="confirmPassword">Confirm Password</form:label>
                            <form:input type="password" path="confirmPassword" class="form-control" required="required"/>
                            <form:errors path="confirmPassword" cssClass="text-warning"></form:errors>
                        </div>
                        <button type="submit" class="btn btn-success">Update</button>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>
