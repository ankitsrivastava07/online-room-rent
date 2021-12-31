<!doctype html>
<html lang="en">
  <head>
  	<title>Login</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<link href="https://fonts.googleapis.com/css?family=Lato:300,400,700&display=swap" rel="stylesheet">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
	<link rel="stylesheet" href="/admin-ui/css/style.css">
<style>
.error{
    font-size: 14px;
    color: #da534d;
}
.alert-danger {
    color: #842029;
    font-size: 14px;
    background-color: #f8d7da;
    border-color: #f5c2c7;
    text-align: center;
    text-size-adjust: auto;
}
</style>
	</head>
	<body>
	<section class="ftco-section">
		<div class="container">
			<div class="row justify-content-center">
				<div class="col-md-6 col-lg-5">
					<div class="login-wrap p-4 p-md-5">
		      	<div class="icon d-flex align-items-center justify-content-center">
		      		<span class="fa fa-user-o"></span>
		      	</div>
		      	<h3 class="text-center mb-4">Have an account?</h3>
		      	<#if sessionExpired?has_content>
		      	<div class="alert alert-danger">
                    <span>${sessionExpired}</span>
                  </div>
                  </#if>
						<form action="" id="loginForm" class="login-form">
		      		<div class="form-group">
		      		<label>Email or Mobile</label>
		      			<input type="text" class="form-control rounded-left" placeholder="Enter your email/mobile" id="emailOrMobile" name="emailOrMobile">
		      		</div>
	            <div class="form-group">
	            <label>Password</label>
	              <input type="password" class="form-control rounded-left" placeholder="Password" id="password" name="password">
	            </div>
	            <div class="form-group">
	            	<button type="submit" class="btn btn-primary rounded submit p-3 px-5">Login</button>
	            </div>
	          </form>
	        </div>
    		</div>
		</div>
		</div>
	</section>
  <script src="/admin-ui/js/jquery.min.js"></script>
  <script src="/admin-ui/js/popper.js"></script>
  <script src="/admin-ui/js/bootstrap.min.js"></script>
  <script src="/admin-ui/js/validate.js"></script>
  <script src="/admin-ui/js/cookie.js"></script>
  <script src="/admin-ui/js/login.js"></script>
	</body>
</html>

