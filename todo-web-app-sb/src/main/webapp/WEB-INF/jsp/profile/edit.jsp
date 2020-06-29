<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="../common/header.jspf"%>
<%@ include file="../common/navigation.jspf"%>
<div class="container">
    <br />
    <div class="row">
        <div class="col-md-6 col-md-offset-3 ">
            <div class="card">
                <div class="card-header">Edit Profile</div>
                <div class="card-body">
                    <form:form method="POST" modelAttribute="profile">
                        <span class="text-warning">${error}</span>
                        <br />
                        <div class="form-group ${status.error ? 'has-error' : ''}">
                            <label>First Name</label>
                            <form:input type="text" path="firstName" class="form-control" required="required" />
                            <form:errors path="firstName" cssClass="text-warning"></form:errors>
                        </div>
                        <div class="form-group ${status.error ? 'has-error' : ''}">
                            <label>Last Name</label>
                            <form:input type="text" path="lastName" class="form-control" required="required" />
                            <form:errors path="lastName" cssClass="text-warning"></form:errors>
                        </div>
                        <div class="form-group ${status.error ? 'has-error' : ''}">
                            <label>Description</label>
                            <form:input type="text" path="description" class="form-control" required="required" />
                            <form:errors path="description" cssClass="text-warning"></form:errors>
                        </div>
                        <br />
                        Avatar:
                        <button type="submit" class="btn btn-success">Update</button>
                        <a role="button" class="btn btn-warning" href="<c:url value="/profile"/>">Cancel</a>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>
<%@ include file="../common/footer.jspf"%>