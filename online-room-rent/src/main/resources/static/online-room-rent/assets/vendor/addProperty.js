$(document).ready(function() {
	$("#addProperty").validate({
		rules: {
            propertyTypes: {
                required: true,
            },
            productCategory: {
                required: true,
            },
			city: {
				required: true,
			},
			state: {
				required: true,
			},
            landMark: {
                required: true,
            },
            propertyTitle:{
            required: true,
            },
            propertyDescription:{
            required: true,
            },
            furnished:{
            required:true,
            },
            roomSet:{
            required:true,
            },
            alternateMobile:{
            required:true,
            },
            sqrFit:{
            required:true,
            },
            address:{
            required:true,
            },
            floor:{
            required:true,
            },
            outOf:{
            required:true,
            },
            rentPrice:{
            required:true,
            }
      },
		messages: {
		outOf:{
		required: "Please select outOf floor",
		},
		floor:{
		required: "Please select floor",
		},
		sqrFit:{
		required: "Please enter sqrt fit ",
		},
		address:{
		required: "Please enter address",
		},
		propertyTitle:{
		required: "Please enter property title",
		},
		propertyDescription:{
		required: "Please enter property description",
		},
		furnished:{
		required: "Please select furnished",
		},
		roomSet:{
		required: "Please enter room set",
		},
		alternateMobile:{
		required: "Please enter alternate contact numebr",
		},
       propertyTypes:{
        required: "Please select property type",
       },
       productCategory:{
        required: "Please select property category",
       },
      city: {
       required: "Please select city",
        },
      state: {
       required: "Please select state",
      },
      landMark: {
       required: "Please enter your land mark",
      },
      rentPrice:{
      required: "Please enter rent price",
      },
},
		submitHandler: function(form) {
        var formTag = $("#addProperty")[0];
        var formData = new FormData(formTag);
        formData.append('image1', $('input#image1')[0].files[0]);
        formData.append('image2', $('input#image2')[0].files[0]);
        formData.append('createdAt', formatDate());
		saveProperty(formData);
		}
	})
})

function saveProperty(formData){
	if ($("#addProperty").valid() && checkConnection()) {
		$.ajax({
           type: "POST",
           url: "/api/v1/property/save-property",
           data: formData,
           contentType: false,
           processData: false,
           headers: {"Authentication": $.cookie('session_Token')},
     		success: function(response) {
				$(".alert").remove();
                setTimeout(function() {
                if ($(".alert").length == 0) {
                      $('h2').after('<div class="alert alert-danger">'+response.message+'</div>');
                    } else{
                $(".alert").html(response.message);
             }
            }, 500);
            if(response.status){
           window.location.href="/property-owner"+response.redirectUri;
            }
			},
		error: function(error) {
		$(".alert").remove("");
		$(".error").remove("");
       setTimeout(function() {
        if (error.status==400 && !error.responseJSON.isValidFile && $(".alert").length == 0) {
           $('#addProperty').after('<div class="alert alert-danger">'+error.responseJSON.message+'</div>');
        }
        else{
        $('.alert').html(error.responseJSON.message)
        }
    }, 500);

		setTimeout(function() {
        if (error.status==400 && error.responseJSON.validationFailed && $(".alert").length == 0) {
			jQuery.each(error.responseJSON.error, function(index, item) {
              $('#'+item.fieldName).after('<span class="error">'+item.message+'</span>');
			});
    	}
	}, 500);
     }
})
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

function formatDate() {
    var d = new Date(),
        month = '' + (d.getMonth() + 1),
        day = '' + d.getDate(),
        year = d.getFullYear();
    if (month.length < 2)
        month = '0' + month;
    if (day.length < 2)
        day = '0' + day;
    return [year, month, day].join('-');
    console.log(formatDate('Sun May 11,2014'));
}