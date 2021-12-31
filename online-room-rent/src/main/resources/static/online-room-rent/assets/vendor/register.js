$(document).ready(function() {
	$("#registerForm").validate({
		rules: {
            firstName: {
                required: true,
            },
            lastName: {
                required: true,
            },
			email: {
				required: true,
			},
			password: {
				required: true,
			},
            mobile: {
                required: true,
            },
           confirmPassword: {
           required: true,
           equalTo: "#password"
      },
    },
		messages: {
		   firstName:{
		   	required: "Please enter your first name",
		   },
		   lastName:{
   		   	required: "Please enter your last name",
	       },
			email: {
				required: "Please enter your email",
			},
            mobile: {
                required: "Please enter your mobile number",
                },
			password: {
				required: "Please enter your password",
			},
			confirmPassword: {
			required: "Please enter your confirm password",
            equalTo: "Password not matched"
            },
		},
		submitHandler: function(form) {
			var formData = {
			"firstName": $("#firstName").val(),
			"lastName":  $("#lastName").val(),
			"email":     $("#email").val(),
			"mobile":    $("#mobile").val(),
			"password":  $("#password").val(),
			"confirmPassword" : $("#confirmPassword").val()
			}
			register(formData);
		}
	})
})

function register(formData) {
	if ($("#registerForm").valid() && checkConnection()) {
		$.ajax({
			type: "POST",
			url: "/api/v1/property/register",
			contentType: "application/json",
			data: JSON.stringify(formData),
			success: function(response) {
				$(".alert").remove();
                setTimeout(function() {
                if ($(".alert").length == 0) {
                        $('h2').after('<div class="alert alert-danger">'+response.message+'</div>');
                    } else{
                        $(".alert").html(response.message);
             }
            }, 500);
			},
		error: function(error) {
		$(".alert").remove("");
		setTimeout(function() {
		if (error.status==400 && $(".alert").length == 0) {
                $('email').after('<div class="alert alert-danger">'+error.responseJSON.message+'</div>');
            } else{
                $(".email").html(error.responseJSON.message);
     }
	}, 500);
     }
})
		//return true;
	}
	return false;
}

function checkConnection(){
$.ajax('/api/v1/admin/check-connection', {
  statusCode: {
    0: function() {
      alert(" We can’t connect to the server please check your internet connection or the page which you are looking for has been removed.");
      return false
    }
  }
});
return true;
}