<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page isELIgnored="false"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Signup Page</title>

<%@include file="component/allcss.jsp"%>

<style type="text/css">
.my-card {
	box-shadow: 0px 0px 10px 1px maroon;
}
</style>

</head>
<body>

	<%@include file="component/navbar.jsp"%>

	<div class="container p-5">
		<div class="row">
			<div class="col-md-4 offset-md-4">
				<div class="card my-card">

					<div class="card-header text-center text-white my-bg-color">
						<p class="fs-4 mt-2">
							<i class="fa fa-user-plus"></i> User Register
						</p>
					</div>

					<div class="card-body">

						<!-- SUCCESS MESSAGE -->
						<c:if test="${not empty successMsg}">
							<p class="text-center text-success fs-4">${successMsg}</p>
							<c:remove var="successMsg" scope="session"/>
						</c:if>

						<!-- ERROR MESSAGE -->
						<c:if test="${not empty errorMsg}">
							<p class="text-center text-danger fs-4">${errorMsg}</p>
							<c:remove var="errorMsg" scope="session"/>
						</c:if>

						<!-- 🔥 FIXED FORM -->
						<form action="<%=request.getContextPath()%>/user_register" method="post">

							<div class="mb-3">
								<label class="form-label">Full Name</label>
								<input name="fullName" type="text" required
									placeholder="Enter full name"
									class="form-control">
							</div>

							<div class="mb-3">
								<label class="form-label">Email</label>
								<input name="email" type="email" required
									placeholder="Enter Email"
									class="form-control">
							</div>

							<div class="mb-3">
								<label class="form-label">Password</label>
								<input name="password" type="password" required
									placeholder="Enter password"
									class="form-control">
							</div>

							<button type="submit"
								class="btn my-bg-color text-white col-md-12">
								Register
							</button>

						</form>

					</div>

				</div>
			</div>
		</div>
	</div>

</body>
</html>
