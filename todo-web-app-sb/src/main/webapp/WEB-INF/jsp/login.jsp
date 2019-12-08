<%@ include file="common/header.jspf"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<div class="container">
    <br />
    <div class="panel panel-primary">
        <div class="panel-body">
            <form method="POST" action="${contextPath}/login" class="form-signin">
                <h2 class="form-heading">Log in</h2>
                <div class="form-group ${error != null ? 'has-error' : ''}">
                    <span>${message}</span>
                    <input name="username" type="text" class="form-control" placeholder="Email" />
                    <br />
                    <input name="password" type="password" class="form-control" placeholder="Password"/>
                    <span>${error}</span>
                    <br />
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                    <button class="btn btn-lg btn-primary btn-block" type="submit">Log In</button>
                    <h4 class="text-center"><a href="${contextPath}/register">Create an account</a></h4>
                </div>
            </form>
        </div>
    </div>
</div>
<%@ include file="common/footer.jspf"%>