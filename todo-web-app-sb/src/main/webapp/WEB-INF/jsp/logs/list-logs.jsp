<%@ include file="../common/header.jspf"%>
<%@ include file="../common/navigation.jspf"%>
<div class="container">
	<br />
	<div class="row">
		<div class="col-md-6 col-md-offset-3 ">
			<div class="card">
				<div class="card-header text-primary">Logs</div>
				<div class="card-body">
					<table class="table table-striped">
						<thead>
						<tr>
							<th>Serial</th>
							<th>Log IP</th>
							<th>Log Content</th>
						</tr>
						</thead>
						<tbody>
							<c:forEach items="${logs}" var="log">
								<tr>
									<td>#</td>
									<td>${log.ipAddress}</td>
									<td>${log.content}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>
<%@ include file="../common/footer.jspf"%>