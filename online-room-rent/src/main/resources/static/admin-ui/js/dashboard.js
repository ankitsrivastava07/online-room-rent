$(document).ready(function() {
    $("#addCategory a").click(function() {
        window.location.href=window.location+"/add-category"
    });
});

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
