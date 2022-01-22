$(document).ready(function() {
	$("#verifyOTP").validate({
		rules: {
			otp: {
				required: true,
			},
		},
		messages: {
			otp: {
    			required: "Please enter otp",
			},
		},
		submitHandler: function(form) {
			var formData = {
				"emailOrMobile": $.cookie("user"),
				"otp" : $("#otp").val(),
			}
			VerifyOTP(formData);
		}
	})
})

function VerifyOTP(formData){
if(checkConnection()){
 $.ajax({
    type: "POST",
    url: "/api/v1/admin/otp-verify",
    contentType: "application/json",
    data: JSON.stringify(formData),
    success: function(response) {
       if (!!$.cookie('session_Token')) {
        $.cookie("session_Token",response.accessToken)
        $.cookie("browser",response.browser)
        }
        else{
        $.cookie("session_Token",response.accessToken)
        $.cookie("browser",response.browser)
        }
          location.reload()
    },
    error: function(error) {
    alert("error")
    }
 });
  }
}

function checkConnection(){
$.ajax('/check-connection', {
  statusCode: {
    0: function() {
      alert(" We can’t connect to the server please check your internet connection or the page which you are looking for has been removed.");
      return false
    }
  }
});
return true;
}
