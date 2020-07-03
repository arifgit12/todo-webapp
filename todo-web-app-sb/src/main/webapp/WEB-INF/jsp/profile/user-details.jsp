<%@ include file="../common/header.jspf"%>
<%@ include file="../common/navigation.jspf"%>
<div class="container">
    <br />
    <div class="row">
        <div class="col-md-6 col-md-offset-3 ">
            <div class="card">
                <div class="card-header">
                    <h4>Profile Details</h4>
                </div>
                <div class="card-body">
                    <a class="btn btn-warning float-right" href="<c:url value="/profile/edit"/>">Edit</a>
                    Name: ${profile.firstName} ${profile.lastName}
                    <br />
                    Email: ${profile.email}
                    <br />
                    Description: ${profile.description}
                    <br />
                    Password: <a href="<c:url value="/update-password"/>" class="btn btn-success">Change Password</a>
                    <br />
                    <br />
                    Avatar
                    <img src="/image/${profile.avatar}" class="img-fluid" />
                </div>
            </div>
        </div>
    </div>
</div>
<%@ include file="../common/footer.jspf"%>