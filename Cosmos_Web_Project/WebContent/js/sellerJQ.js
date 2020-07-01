var initial_profile , initial_profile_button , options_profile , options_profile_button;

window.onload = function() 
{
	initial_profile = $("#initial_profile").html();
	initial_profile_button = $("#initial_profile_button").html();
	options_profile = $("#options_profile").html();
	options_profile_button = $("#options_buttons").html();
}

function account_options()
{
	$("#initial_profile").html(options_profile);
	$("#initial_profile_button").html(options_profile_button);
}

function show_profile()
{
	$("#initial_profile").html(initial_profile);
	$("#initial_profile_button").html(initial_profile_button);
}

function CreateBill()
{
	document.getElementById("create_bill").style.display = "block";
}

function add_c()
{
	document.getElementById("add_client").style.display = "block";
	document.getElementById("add_c_button").style.visibility = "hidden";
}

function cancel_add_c()
{
	document.getElementById("add_client").style.display = "none";
	document.getElementById("add_c_button").style.visibility = "visible";
}

function show_requests()
{
	document.getElementById("requests").style.display = "block";
}

function close_req()
{
	document.getElementById("requests").style.display = "none";
	window.scrollTo(0,0);
}

var coll = document.getElementsByClassName("collapsible");
var i;

for (i = 0; i < coll.length; i++) {
  coll[i].addEventListener("click", function() {
    this.classList.toggle("active");
    var content = this.nextElementSibling;
    if (content.style.maxHeight){
      content.style.maxHeight = null;
    } else {
      content.style.maxHeight = content.scrollHeight + "px";
    } 
  });
}

