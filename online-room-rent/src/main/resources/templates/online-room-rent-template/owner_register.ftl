<html lang="en"><head>
    <meta charset="utf-8">
    <meta name="robots" content="noindex, nofollow">
    <title> Signup </title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="//netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <style>
    .form-control{
    height: 42px;
    }
    .container {
        margin-right: auto;
        margin-left: auto;
    }
    .form-horizontal .control-label {
        text-align: inherit;
        color: gray;
    }
    label {
    display: inline-block;
    margin-bottom: 5px;
    font-weight: normal;
    }
    .error{
        font-size: 13px;
        color: #da534d;
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
        <script src="/admin-ui/js/jquery.min.js"></script>
</head>
<body>
     <div class="container">
        <div id="signupbox" style="display:block; margin-top:50px" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
        <form id="registerForm" class="form-horizontal" name="registerForm" novalidate="novalidate">
                    <div class="panel panel-info">
                        <div class="panel-heading">
                            <div class="panel-title">Sign Up</div>
                        </div>
                        <div class="panel-body">
                                <div id="signupalert" style="display:none" class="alert alert-danger">
                                    <p>Error:</p>
                                    <span></span>
                                </div>
                             <div class="form-group">
                             <label for="firstname" class="col-md-3 control-label">First Name</label>
                             <div class="col-md-9">
                              <input type="text" class="form-control" id="firstName" name="firstName" placeholder="First Name">
                             </div>
                          </div>
                          <div class="form-group">
                              <label for="lastname" class="col-md-3 control-label">Last Name</label>
                              <div class="col-md-9">
                               <input type="text" class="form-control" id="lastName" name="lastName" placeholder="Last Name">
                              </div>
                            </div>
                        <div class="form-group">
                            <label for="email" class="col-md-3 control-label">Email</label>
                            <div class="col-md-9">
                             <input type="email" class="form-control error" id="email" name="email" placeholder="Email Address" aria-invalid="true"><label id="email-error" class="error" for="email">Please enter a valid email address.</label>
                               </div>
                             </div>
                                <div class="form-group">
                                    <label for="password" class="col-md-3 control-label">Password</label>
                                    <div class="col-md-9">
                                     <input type="password" class="form-control valid" id="password" name="passwd" placeholder="Password" aria-invalid="false">
                                 </div>
                                </div>
                                <div class="form-group">
                                <label for="confirmPassword" class="col-md-3 control-label">Confirm Password</label>
                                <div class="col-md-9">
                                 <input type="password" class="form-control" id="confirmPassword" name="confirmPassword" placeholder="Confirm Password">
                             </div>
                            </div>
                        <div class="form-group">
                            <label for="icode" class="col-md-3 control-label">I am</label>
                            <div class="col-md-9">
                           <#list userRoles as role>
                              <input style="opacity: 3;" value="${role.id}" name="iam" type="radio" required>${role.name}
                          </#list>
                         </div>
                          </div>
                                <div class="form-group">
                                    <label for="icode" class="col-md-3 control-label">Mobile number</label>
                                    <div class="col-md-9">
                                     <input type="number" class="form-control" id="mobile" name="mobile" placeholder="Mobile number">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <!-- Button -->
                                    <div class="col-md-offset-3 col-md-9">
                                        <input type="submit" id="btn-signup" class="btn btn-info">
                                        <span style="margin-left:8px;">Or</span>
                                    </div>
                                </div>

                                <div style="border-top: 1px solid #999; padding-top:20px" class="form-group">
                                 <div class="col-md-offset-3 col-md-9" style="color: gray;">
                                    Already have an account?<a href="/property-owner/login">Sign In here</a>
                                </div>
                             </div>
<script src="/online-room-rent/assets/vendor/validate.js"></script>
<script src="/admin-ui/js/cookie.js"></script>
<script src="/online-room-rent/assets/vendor/register.js"></script>

</div></div></form></div></div></body></html>