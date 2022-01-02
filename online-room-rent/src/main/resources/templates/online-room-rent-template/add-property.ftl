<html lang="en"><head>
    <meta charset="utf-8">
    <meta name="robots" content="noindex, nofollow">
    <title> Sell or Rent your Property </title>
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
    .dropdown-menu{
    display:block;
    position: inherit;
    }
      label.col-md-3.control-label {
         padding-right: inherit;
        }
        .property_location {
            text-align: center;
        }
        </style>
        <script src="/admin-ui/js/jquery.min.js"></script>
</head>
<body>
     <div class="container">
        <div id="signupbox" style="display:block; margin-top:50px" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
        <form id="registerForm" class="form-horizontal" name="registerForm">
                    <div class="panel panel-info">
                        <div class="panel-heading">
                            <div class="panel-title">Sell or Rent your Property</div>
                        </div>
                        <div class="panel-body">
                                <div id="signupalert" style="display:none" class="alert alert-danger">
                                    <p>Error:</p>
                                    <span></span>
                                </div>
                             <div class="form-group">
                             <label class="col-md-3 control-label">I am</label>
                             <div class="col-md-9">
                              <input id="owner" style="opacity: 3;" name="owner" type="radio"> Owner
                              <input id="agent" style="opacity: 3;" name="agent"  type="radio"> Agent
                              <input id="builder" style="opacity: 3;" name="builder" type="radio"> Builder
                             </div>
                          </div>
                          <div class="form-group">
                              <label for="lastname" class="col-md-3 control-label">Property Type</label>
                              <div class="col-md-9">
                              <input id="iamO" style="opacity: 3;" name="rent" value="Owner" type="radio"> Rent
                              <input id="iamO" style="opacity: 3;" name="resale" value="Owner" type="radio"> Resale
                              <input id="iamO" style="opacity: 3;" name="pg/hotel" type="radio"> PG/Hotel
                              <input id="iamO" style="opacity: 3;" name="flatMates" type="radio"> FlatMates
                              </div>
                            </div>
                        <div class="form-group">
                            <label for="email" class="col-md-3 control-label">Property Category</label>
                            <div class="col-md-9">
                       <select class="dropdown-menu" aria-invalid="false">
                         <#list categoryList as category>
                           <option value="${category.id}" data-slug="${category.slugName}">${category.categoryName}</option>
                         </#list>
                       </select>
                               </div>
                             </div>
                                <div class="property_location">
                                <div class="control-label">Property Location</div>
                                </div>
                                <div class="form-group">
                                    <label for="icode" class="col-md-3 control-label">City</label>
                                    <div class="col-md-8">
                                    <select name="city" class="dropdown-menu" aria-invalid="false">
                                        <option value="a">New Delhi</option>
                                        <option value="a">New Delhi</option>
                                        <option value="a">New Delhi</option>
                                        <option value="a">New Delhi</option>
                                        <option value="a">New Delhi</option>
                                        <option value="a">New Delhi</option>
                                        <option value="a">New Delhi</option>
                                    </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                <label for="icode" class="col-md-3 control-label">State</label>
                                <div class="col-md-8">
                                <select name="city" class="dropdown-menu" aria-invalid="false">
                                    <option value="a">New Delhi</option>
                                    <option value="a">New Delhi</option>
                                    <option value="a">New Delhi</option>
                                    <option value="a">New Delhi</option>
                                    <option value="a">New Delhi</option>
                                    <option value="a">New Delhi</option>
                                    <option value="a">New Delhi</option>
                                </select>
                                </div>
                            </div>
                        <div class="form-group">
                        <label for="icode" class="col-md-3 control-label">Land Mark</label>
                         <div class="col-md-8">
                        <select name="city" class="dropdown-menu" aria-invalid="false">
                            <option value="a">New Delhi</option>
                            <option value="a">New Delhi</option>
                            <option value="a">New Delhi</option>
                            <option value="a">New Delhi</option>
                            <option value="a">New Delhi</option>
                            <option value="a">New Delhi</option>
                            <option value="a">New Delhi</option>
                        </select>
                        </div>
                    </div>
                                <div class="form-group">
                                    <!-- Button -->
                                    <div class="col-md-offset-3 col-md-9">
                                        <input type="submit" id="btn-signup" class="btn btn-info">
                                        <span style="margin-left:8px;">Or</span>
                                    </div>
                                </div>
                                </form>
                                <div style="border-top: 1px solid #999; padding-top:20px" class="form-group">
                                 <div class="col-md-offset-3 col-md-9" style="color: gray;">
                                    Already have an account?<a href="/property/login">Skip Here</a>
                                </div>
                             </div>
<script src="/online-room-rent/assets/vendor/validate.js"></script>
<script src="/online-room-rent/assets/vendor/register.js"></script>
</body>
</html>