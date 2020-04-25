<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>

<div class="container">
    <br />
    <br>
    <div class="card">
        <div class="card-header">
            <div class="card-title float-left">
                <h4>List of Notification's</h4>
            </div>
            <div class="card-title float-right">
                <a role="button" class="btn btn-danger" href="/remove-list">Viewed All Notifications</a>
            </div>
            <div class="clearfix"></div>
        </div>
        <div class="card-body">
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>Date</th>
                        <th>Message</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${notifications}" var="notify">
                        <tr>
                            <td><fmt:formatDate value="${notify.localDateTime}" pattern="dd/MM/yyyy" /></td>
                            <td>${notify.message}</td>
                            <td>
                                <a role="button" class="btn btn-warning" href="/todo?id=${notify.link}">View</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    <br />
</div>
<%@ include file="common/footer.jspf"%>