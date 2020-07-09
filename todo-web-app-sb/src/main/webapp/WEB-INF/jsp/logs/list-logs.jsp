<%@ include file="../common/header.jspf"%>
<%@ include file="../common/navigation.jspf"%>
<div class="container">
	<br />
	<div class="row">
		<div class="col-md-12 col-md-offset-3 ">
			<div class="card">
				<div class="card-header text-primary">
					<h3>Logs</h3>
				</div>
				<div class="card-body">
					<table class="table table-striped">
						<thead>
						<tr>
							<th>Serial</th>
							<th>Log IP</th>
							<th>Log Content</th>
							<th>Date of Created</th>
						</tr>
						</thead>
						<tbody>
							<c:forEach items="${logs}" var="log">
								<tr>
									<td>${log.id}</td>
									<td>${log.ipAddress}</td>
									<td>${log.content}</td>
									<td>${log.createTime}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<nav aria-label="Page navigation">
						<ul class="pagination">
							<c:url value="/logs" var="prev">
								<c:param name="page" value="${page-1}"/>
							</c:url>
							<c:choose>
								<c:when test="${page > 1}">
									<li class="page-item">
										<a class="page-link" href="<c:out value="${prev}"/>" aria-label="Previous">
											<span aria-hidden="true">&laquo;</span>
											<span class="sr-only">Prev</span>
										</a>
									</li>
								</c:when>
								<c:otherwise>
									<li class="page-item disabled">
										<a class="page-link" href="<c:out value="${prev}"/>" aria-label="Previous">
											<span aria-hidden="true">&laquo;</span>
											<span class="sr-only">Prev</span>
										</a>
									</li>
								</c:otherwise>
							</c:choose>
							<c:forEach begin="1" end="${maxPages}" step="1" varStatus="i">
								<c:choose>
									<c:when test="${page == i.index}">
										<li class="page-item active">
											<span class="page-link">${i.index}</span>
										</li>
									</c:when>
									<c:otherwise>
										<li class="page-item">
											<c:url value="/logs" var="url">
												<c:param name="page" value="${i.index}"/>
											</c:url>
											<a class="page-link" href='<c:out value="${url}" />'>
												${i.index}
											</a>
										</li>
									</c:otherwise>
								</c:choose>
							</c:forEach>
							<c:url value="/logs" var="next">
								<c:param name="page" value="${page + 1}"/>
							</c:url>
							<c:choose>
								<c:when test="${page + 1 <= maxPages}">
									<li class="page-item">
										<a class="page-link" href='<c:out value="${next}" />' aria-label="Next">
											<span aria-hidden="true">&raquo;</span>
											<span class="sr-only">Next</span>
										</a>
									</li>
								</c:when>
								<c:otherwise>
									<li class="page-item disabled">
										<a class="page-link" href='<c:out value="${next}" />' aria-label="Next">
											<span aria-hidden="true">&raquo;</span>
											<span class="sr-only">Next</span>
										</a>
									</li>
								</c:otherwise>
							</c:choose>
						</ul>
					</nav>
				</div>
			</div>
		</div>
	</div>
</div>
<%@ include file="../common/footer.jspf"%>