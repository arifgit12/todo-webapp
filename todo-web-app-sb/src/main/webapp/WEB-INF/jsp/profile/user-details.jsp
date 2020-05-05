<%@ include file="../common/header.jspf"%>
<%@ include file="../common/navigation.jspf"%>
<div class="container">
    <br />
    <div class="row">
        <div class="col-md-6 col-md-offset-3 ">
            <div class="card">
                <div class="card-header">Profile Details</div>
                <div class="card-body">
                    Name: ${profile.firstName} ${profile.lastName}
                    <br />
                    Email: ${profile.email}
                    <br />
                    Password: <a href="<c:url value="/update-password"/>" class="btn btn-success">Change Password</a>
                </div>
            </div>
        </div>
    </div>
</div>
<%@ include file="../common/footer.jspf"%>