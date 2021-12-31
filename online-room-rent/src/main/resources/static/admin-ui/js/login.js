$(document).ready(function() {
	$("#loginForm").validate({
		rules: {
			emailOrMobile: {
				required: true,
			},
			password: {
				required: true,
			},
		},
		messages: {
			emailOrMobile: {
				required: "Please enter your email/mobile number",
			},
			password: {
				required: "Please enter your password",
			},
		},
		submitHandler: function(form) {
			var formData = {
				"emailOrMobile": $("#emailOrMobile").val(),
				"password": $("#password").val()
			}
			login(formData);
		}
	})
})

function login(formData) {
	if ($("#loginForm").valid() && checkConnection()) {
		$.ajax({
			type: "POST",
			url: "/api/v1/admin/login",
			contentType: "application/json",
			data: JSON.stringify(formData),
			success: function(response) {
				$(".alert").remove();
                setTimeout(function() {
                if ($(".alert").length == 0) {
                     $('h3').after('<div class="alert alert-danger">'+response.message+'</div>');
                    } else{
                     $(".alert").html(response.message);
             }
            }, 500);
            if(response.status==true){
            if (!!$.cookie('session_Token')) {
 	    	$.cookie("session_Token",response.accessToken)
 	    	$.cookie("browser",response.browser)
 	    	}
 	    	else{
 	    	$.cookie("session_Token",response.accessToken)
 	    	$.cookie("browser",response.browser)
 	    	}
           	location.reload();
       	     }
			},
		error: function(error) {
		$(".alert").remove("");
		setTimeout(function() {
		if ($(".alert").length == 0) {
                $('h3').after('<div class="alert alert-danger">'+error.responseJSON.message+'</div>');
            } else{
                $(".alert").html(error.responseJSON.message);
     }
	}, 500);
     }
      })
}
	return false;
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
