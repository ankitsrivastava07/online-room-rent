<html lang="en"><head>
  	<title>Login</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<link href="https://fonts.googleapis.com/css?family=Lato:300,400,700&amp;display=swap" rel="stylesheet">
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
    input::-webkit-outer-spin-button,
    input::-webkit-inner-spin-button {
      -webkit-appearance: none;
      margin: 0;
    }
    /* Firefox */
    input[type=number] {
      -moz-appearance: textfield;
    }
</style>
	</head>
	<body id="verify">
	<section class="ftco-section">
		<div class="container">
			<div class="row justify-content-center">
				<div class="col-md-6 col-lg-5">
					<div class="login-wrap p-4 p-md-5">
		      	<div class="icon d-flex align-items-center justify-content-center">
		      		<span class="fa fa-user-o"></span>
		      	</div>
		      	<h3 class="text-center mb-4">Have an account?</h3>
    			<form action="" id="verifyOTP">
		      		<div class="form-group">
		      		<label>Enter OTP </label>
		      			<input type="number" class="form-control rounded-left valid" placeholder="Enter your otp" id="otp" name="otp" aria-invalid="false">
		      		</div>
	            <div class="form-group">
	            	<button type="submit" class="btn btn-primary rounded submit p-3 px-5">Submit</button>
	            </div>
	          </form>
	        </div>
    		</div>
		</div>
   	</div>
  </section>
  <script src="/admin-ui/js/jquery.min.js"></script>
  <script src="/admin-ui/js/validate.js"></script>
  <script src="/admin-ui/js/cookie.js"></script>
  <script src="/admin-ui/js/verify-otp.js"></script>
</body>
</html>