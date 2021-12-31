<html>
<head>
	<meta charset="utf-8">
	<title>Register</title>
	<!-- Mobile Specific Metas -->
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<!-- Font-->
	<link rel="stylesheet" type="text/css" href="/css/roboto-font.css">
	<link rel="stylesheet" type="text/css" href="/fonts/font-awesome-5/css/fontawesome-all.min.css">
	<!-- Main Style Css -->
    <link rel="stylesheet" href="owner-register/css/style.css"/>
</head>
<body class="form-v5">
	<div class="page-content">
		<div class="form-v5-content">
			<form class="form-detail" id="registerForm" method="post">
				<h2>Register Account Form</h2>
				<div class="form-row">
					<label for="full-name">First Name</label>
					<input type="text" name="firstName" id="firstName" class="input-text" placeholder="Enter your first name">
					<i class="fas fa-user"></i>
				</div>
            <div class="form-row">
                    <label for="last-name">Last Name</label>
                    <input type="text" name="lastName" id="lastName" class="input-text" placeholder="Enter your last name">
                    <i class="fas fa-user"></i>
                </div>

				<div class="form-row">
					<label for="your-email">Email</label>
					<input type="email" name="email" id="email" class="input-text" placeholder="Please enter your email">
					<i class="fas fa-envelope"></i>
				</div>

				<div class="form-row">
                <label for="your-email">Mobile</label>
                <input type="number" name="mobile" id="mobile" class="input-text" placeholder="Please enter your mobile">
                <i class="fas fa-envelope"></i>
            </div>

            <div class="form-row">
                <label for="password">Password</label>
                <input type="password" name="password" id="password" class="input-text" placeholder="Please enter your password">
                <i class="fas fa-lock"></i>
            </div>
				<div class="form-row">
                    <label for="password">Confirm Password</label>
                    <input type="password" name="confirmPassword" id="confirmPassword" class="input-text" placeholder="Please enter your confirm password">
                    <i class="fas fa-lock"></i>
                </div>

				<div class="form-row-last">
					<input type="submit" name="register" class="register" value="Register">
				</div>
			</form>
		</div>
	</div>
	  <script src="/admin/js/jquery.min.js"></script>
  <script src="/admin/js/validate.js"></script>
  <script src="/owner-register/js/register.js"></script>

</body>
</html>