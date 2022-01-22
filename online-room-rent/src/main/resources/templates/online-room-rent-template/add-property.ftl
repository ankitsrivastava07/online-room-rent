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
        <form id="addProperty" class="form-horizontal" name="addProperty" novalidate="novalidate">
                    <div class="panel panel-info">
                        <div class="panel-heading">
                            <div class="panel-title">Sell or Rent your Property</div>
                        </div>
              <div class="panel-body">
              <div class="form-group">
              <label for="lastname" class="col-md-3 control-label">Property Type</label>
              <div class="col-md-9">
              <input style="opacity: 3;" name="propertyType" value="Rent" type="radio" required="" class="valid"> Rent
              <input style="opacity: 3;" name="propertyType" value="Resale" type="radio" required="" class="valid"> Resale
              <input style="opacity: 3;" name="propertyType" value="PG/Hotel" type="radio" required="" class="valid"> PG/Hotel
              <input style="opacity: 3;" name="propertyType" value="FlatMates" type="radio" required="" class="valid"> FlatMates
              </div>
              </div>
           <div class="form-group">
           <label for="email" class="col-md-3 control-label">Property Category</label>
           <div class="col-md-9">
            <select class="dropdown-menu valid" aria-invalid="false" name="productCategory" id="productCategory">
            <option value="">Select Product Category</option>
            <#list propertyCategory as category>
            <option value="${category.id}" data-slug="${category.name}">${category.name}</option>
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
                                    <select name="city" class="dropdown-menu valid" aria-invalid="false" id="city">
                                     <option value="">Select City</option>
                                      <option value="1" data-slug="New Delhi">New Delhi</option>
                                    </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                <label for="icode" class="col-md-3 control-label">State</label>
                                <div class="col-md-8">
                                <select name="state" class="dropdown-menu valid" aria-invalid="false" id="state">
                               <option value="">Select State</option>
                                 <option value="1" data-slug="Delhi">Delhi</option>
                                </select>
                                </div>
                            </div>

                        <div class="form-group">
                        <label for="icode" class="col-md-3 control-label">Country</label>
                        <div class="col-md-8">
                        <select name="country" class="dropdown-menu valid" aria-invalid="false" id="country">
                       <option value="">Select Country</option>
                         <option value="1" data-slug="Delhi">India</option>
                        </select>
                        </div>
                    </div>

                        <div class="form-group">
                        <label for="icode" class="col-md-3 control-label">Address</label>
                         <div class="col-md-8">
                        <input type="text" class="form-control valid" id="address" name="address" placeholder="Enter address with land mark" aria-invalid="false">
                        </div>
                           </div>

                        <div class="form-group">
                        <label for="icode" class="col-md-3 control-label">Property Title</label>
                         <div class="col-md-8">
                        <input type="text" class="form-control valid" id="propertyTitle" name="propertyTitle" placeholder="Enter Property Title" aria-invalid="false">
                        </div>
                           </div>
                          <div class="form-group">
                          <label for="icode" class="col-md-3 control-label">Floor</label>
                          <div class="col-md-8">
                          <select name="floor" class="dropdown-menu valid" aria-invalid="false" id="floor">
                         <option value="">Select Floor</option>
                          <option value="Ground Floor">Ground Floor</option>
                           <option value="1">1</option>
                           <option value="2">2</option>
                           <option value="3">3</option>
                           <option value="4">4</option>
                           <option value="5">5</option>
                          </select>
                          </div>
                      </div>
                        <div class="form-group">
                        <label for="icode" class="col-md-3 control-label">Out Of</label>
                        <div class="col-md-8">
                        <select name="outOf" class="dropdown-menu valid" aria-invalid="false" id="outOf">
                       <option value="">Select Floor</option>
                         <option value="1">1</option>
                         <option value="2">2</option>
                         <option value="3">3</option>
                         <option value="4">4</option>
                         <option value="5">5</option>
                        </select>
                        </div>
                    </div>
                      <div class="form-group">
                        <label for="icode" class="col-md-3 control-label">Property Description</label>
                         <div class="col-md-8">
                        <input type="textarea" class="form-control valid" id="propertyDescription" name="propertyDescription" placeholder="Enter Property Description" aria-invalid="false">
                        </div>
                           </div>
                      <div class="panel-body">
                      <div class="form-group">
                      <label for="lastname" class="col-md-3 control-label">Furnishing</label>
                      <div class="col-md-9">
                      <input style="opacity: 3;" name="furnished" type="radio" value="Non Furnished" required class="valid"> Non Furnished
                      <input style="opacity: 3;" name="furnished" type="radio" value="Semi Furnished" required class="valid"> Semi Furnished
                      <input style="opacity: 3;" name="furnished" type="radio" value="Furnished" required class="valid"> Furnished
                      </div>
                      </div>
                       <div class="form-group">
                        <label for="icode" class="col-md-3 control-label">Room Set</label>
                         <div class="col-md-8">
                        <input type="text" class="form-control valid" id="roomSet" name="roomSet" placeholder="Please enter room set" aria-invalid="false">
                        </div>
                           </div>
                           <div class="form-group">
                           <label for="icode" class="col-md-3 control-label">Alternate Contact Number</label>
                            <div class="col-md-8">
                           <input type="number" class="form-control valid" id="alternateMobile" name="alternateMobile" placeholder="Please enter Alternate Contact Number" aria-invalid="false">
                           </div>
                       </div>
                      <div class="form-group">
                      <label for="icode" class="col-md-3 control-label">Rent Price</label>
                       <div class="col-md-8">
                      <input type="number" class="form-control valid" id="rentPrice" name="rentPrice" placeholder="Enter rent price" aria-invalid="false">
                      </div>
                         </div>
                           <div class="form-group">
                          <label for="icode" class="col-md-3 control-label">Square Fit</label>
                           <div class="col-md-8">
                          <input type="text" class="form-control valid" id="sqrFit" name="sqrFit" placeholder="Please enter Alternate Contact Number" aria-invalid="false">
                          </div>
                             </div>
                            <div class="form-group">
                           <label for="icode" class="col-md-3 control-label">Upload Image</label>
                            <div class="col-md-8">
                           <input type="file" class="form-control valid" id="image1" name="image1">
                           </div>
                            </div>
                            <div class="form-group">
                           <label for="icode" class="col-md-3 control-label">Upload Image</label>
                            <div class="col-md-8">
                           <input type="file" class="form-control valid" id="image2" name="image2" required>
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
                                    Already have an account?<a href="/property/login">Skip Here</a>
                                </div>
                             </div>
<script src="/online-room-rent/assets/vendor/validate.js"></script>
<script src="/admin-ui/js/cookie.js"></script>
<script src="/online-room-rent/assets/vendor/addProperty.js"></script>
</div>
</div>
</form>
</div>
</div>
</body>
</html>