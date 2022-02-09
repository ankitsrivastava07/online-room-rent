$("#search").click(function() {
      state =  $("#state").val();
      city = $("#city").val();
      locality =  $("#locality").val(),
      createdAt = formatDate($("#createdAt").val()),
      minPrice =  $("#minPrice").val(),
      maxPrice =  $("#maxPrice").val(),
      propertyCategory =  $("#propertyCategory").val(),
      propertyCategoryId = $("#propertyCategoryId").val(),
      upcomingProperty = $("#upcomingProperty").val(),
      roomSet =  $("#roomSet").val()

if($('#state').val()){
let searchParams = new URLSearchParams(window.location.search)
urlWithNoParameter = window.location.href.split('?')[0];
url = urlWithNoParameter + "search-by-filter?state="  +  state + "&city=" + city + "&locality=" + locality + "&minPrice=" + minPrice + "&maxPrice=" + maxPrice + "&propertyCategory=" + propertyCategory + "&propertyCategoryId=" + propertyCategoryId + "&upcomingProperty=" + upcomingProperty + "&roomSet=" + roomSet + "&createdAt=" + createdAt
 openTab(url);
 }

if($('#city').val()){
  urlWithNoParameter = window.location.href.split('?')[0];
  url = urlWithNoParameter + "search-by-filter?state="  +  state + "&city=" + city + "&locality=" + locality + "&minPrice=" + minPrice + "&maxPrice=" + maxPrice + "&propertyCategory=" + propertyCategory + "&propertyCategoryId=" + propertyCategoryId + "&upcomingProperty=" + upcomingProperty + "&roomSet=" + roomSet + "&createdAt=" + createdAt
  openTab(url);
 }

if($('#locality').val()){
   urlWithNoParameter = window.location.href.split('?')[0];
   url = urlWithNoParameter + "search-by-filter?state="  +  state + "&city=" + city + "&locality=" + locality + "&minPrice=" + minPrice + "&maxPrice=" + maxPrice + "&propertyCategory=" + propertyCategory + "&propertyCategoryId=" + propertyCategoryId + "&upcomingProperty=" + upcomingProperty + "&roomSet=" + roomSet + "&createdAt=" + createdAt
   openTab(url);
 }

if($('#createdAt').val()){
   urlWithNoParameter = window.location.href.split('?')[0];
   url = urlWithNoParameter + "search-by-filter?state="  +  state + "&city=" + city + "&locality=" + locality + "&minPrice=" + minPrice + "&maxPrice=" + maxPrice + "&propertyCategory=" + propertyCategory + "&propertyCategoryId=" + propertyCategoryId + "&upcomingProperty=" + upcomingProperty + "&roomSet=" + roomSet + "&createdAt=" + createdAt
   openTab(url);
 }

if($('#roomSet').val()){
   urlWithNoParameter = window.location.href.split('?')[0];
   url = urlWithNoParameter + "search-by-filter?state="  +  state + "&city=" + city + "&locality=" + locality + "&minPrice=" + minPrice + "&maxPrice=" + maxPrice + "&propertyCategory=" + propertyCategory + "&propertyCategoryId=" + propertyCategoryId + "&upcomingProperty=" + upcomingProperty + "&roomSet=" + roomSet + "&createdAt=" + createdAt
   openTab(url);
 }

if($('#minPrice').val()){
   urlWithNoParameter = window.location.href.split('?')[0];
   url = urlWithNoParameter + "search-by-filter?state="  +  state + "&city=" + city + "&locality=" + locality + "&minPrice=" + minPrice + "&maxPrice=" + maxPrice + "&propertyCategory=" + propertyCategory + "&propertyCategoryId=" + propertyCategoryId + "&upcomingProperty=" + upcomingProperty + "&roomSet=" + roomSet + "&createdAt=" + createdAt
   openTab(url);
 }

if($('#maxPrice').val()){
   urlWithNoParameter = window.location.href.split('?')[0];
   url = urlWithNoParameter + "search-by-filter?state="  +  state + "&city=" + city + "&locality=" + locality + "&minPrice=" + minPrice + "&maxPrice=" + maxPrice + "&propertyCategory=" + propertyCategory + "&propertyCategoryId=" + propertyCategoryId + "&upcomingProperty=" + upcomingProperty + "&roomSet=" + roomSet + "&createdAt=" + createdAt
   openTab(url);
 }
 return false;
});

function openTab(url){
var win = window.open(url);
if (win) {
    //Browser has allowed it to be opened
    win.focus();
} else {
    //Browser has blocked it
    alert('Please allow popups for this website');
}
}

function formatDate(date) {
 if(!$('#createdAt').val()){
  return ""
 }
    var d = new Date(date),
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

$(function () {
    $('.carousel').carousel({interval: 20})
})