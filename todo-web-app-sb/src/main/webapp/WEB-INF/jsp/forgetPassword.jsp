<%@ include file="common/header.jspf"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<div class="container">
    <br />
    <div class="panel panel-primary">
        <div class="panel-body">
            <form:form method="POST" modelAttribute="userForm" class="form-signin">
                <h2 class="form-signin-heading">Forget your Password?</h2>
                <br />

                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input type="text" path="email" class="form-control" placeholder="Email" required="required" autofocus="true" />
                    <form:errors path="email" cssClass="text-warning"></form:errors>
                </div>

                <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
                <h4 class="text-center"><a href="${contextPath}/login">Have account? Login</a></h4>
            </form:form>
        </div>
    </div>
</div>
<%@ include file="common/footer.jspf"%>