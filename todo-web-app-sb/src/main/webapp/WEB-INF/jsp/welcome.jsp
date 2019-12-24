<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>
<div class="container">
	<br />
	<div class="panel panel-primary">
		<div class="panel-body">
			<c:if test="${name == null}">
				Welcome!! <a href="/login">Login</a> to manage your
				todo's.
			</c:if>
			<c:if test="${name != null}">
				Welcome ${name}!! <a href="/list-todos">Click here</a> to manage your
				todo's.
			</c:if>
		</div>
	</div>
</div>
<%@ include file="common/footer.jspf"%>