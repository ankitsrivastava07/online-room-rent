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

function addCategory(formData){
	if ($("#addCategory").valid() && checkConnection()) {
		$.ajax({
			type: "POST",
			url: "/api/v1/admin/add-category",
			contentType: "application/json",
			data: JSON.stringify(formData),
        	beforeSend: function(request) {
           request.setRequestHeader("Authentication", $.cookie("session_Token"));
           request.setRequestHeader("browser", $.cookie("browser"));
          },
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
           	location.reload();
       	     }
		},
		error: function(error) {
		$(".alert").remove("");
		$("#err-401").remove("");
		 if(error.status==401){
		 	var duration = 500;
			$({to:0}).animate({to:1}, duration, function() {
  			if ($(".err-401").length == 0) {
			$('h3').after('<div class="alert alert-danger" id="err-401">'+error.responseJSON.message+'</div>');
  			}else{
  				$("#err-401").html("");
  			}
			})
       }
		setTimeout(function() {
		 $.each( error.responseJSON.error, function(index, value){
	    	$('h3').after('<div class="alert alert-danger">'+value.message+'</div>');
          });
	}, 500);
       }
})
}
}
$(document).ready(function() {
	$("#addCategory").validate({
		rules: {
			categoryName: {
				required: true,
			},
			description: {
				required: true,
			},
			slugName: {
			required: true,
  			},
		},
		messages: {
			categoryName: {
				required: "Please enter Category Property Name",
			},
			description: {
				required: "Please enter description",
			},
			slugName: {
    		required: "Please enter Slug Name",
  			},
		},
		submitHandler: function(form) {
			var formData = {
				"categoryName": $("#categoryName").val(),
				"slugName": $("#slugName").val(),
				"description" : $("#description").val()
			}
			addCategory(formData);
		}
	})
})
