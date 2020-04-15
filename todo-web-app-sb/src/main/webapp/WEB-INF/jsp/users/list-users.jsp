<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../common/header.jspf"%>
<%@ include file="../common/navigation.jspf"%>

<div class="container">
	<br />
	<br />
	<div class="panel panel-primary">
		<div class="panel-heading">
			<h3>List of USER's</h3>
		</div>
		<div class="panel-body">
			<table class="table table-striped">
				<thead>
					<tr>
						<th>Serial</th>
						<th>Name</th>
						<th>User Name</th>
						<th>Action</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${users}" var="user">
						<tr>
							<td>#</td>
							<td>${user.firstName} ${user.lastName}</td>
							<td>${user.email}</td>
							<td></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</div>
<%@ include file="../common/footer.jspf"%>