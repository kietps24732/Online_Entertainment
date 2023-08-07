<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Register</title>
<%@ include file="/common/head.jsp"%>
</head>
<body>
	<%@ include file="/common/header.jsp"%>

	<div class="container-fluid tm-mt-60">
		<div class="row tm-mb-50">
			<div class="col-lg-12 col-12 mb-5">
				<h2 class="tm-text-secondary mb-5 text-center" style="color: pink;">Đăng Ký</h2>
				<form id="register-form" action="register" method="POST"
					class="tm-contact-form mx-auto">
					<div class="form-group">
						<input type="text" name="username" class="form-control rounded-0"
							placeholder="Username" required />
					</div>
					<div class="form-group">
						<input type="password" name="password" class="form-control rounded-0"
							placeholder="Password" required />
					</div>
					<div class="form-group">
						<input type="password" name="cfmPassword" class="form-control rounded-0"
							placeholder="Confirm Password" required />
					</div>
					<div class="form-group">
						<input type="text" name="email" class="form-control rounded-0"
							placeholder="Email" required />
					</div>
					<div class="form-group tm-text-right">
						<button type="submit" class="btn btn-primary" style="background-color: pink;">Submit</button>
					</div>
				</form>
			</div>
		</div>
	</div>

	<%@ include file="/common/footer.jsp"%>

</body>
</html>