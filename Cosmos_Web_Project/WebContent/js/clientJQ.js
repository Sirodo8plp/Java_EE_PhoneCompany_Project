var initial_profile , initial_button , options_profile , options_buttons;
var initial_sub , initial_sub_button , change_sub , change_sub_buttons;
var pay_sub , pay_sub_buttons;

window.onload = function () 
{
	options_profile = $("#options_profile").html();
	options_buttons = $("#options_buttons").html();
	initial_profile = $("#initial_profile").html();
	initial_button = $("#initial_button").html();
	initial_sub = $("#initial_sub").html();
	initial_sub_button = $("#initial_sub_button").html();
	change_sub = $("#sub_attempt").html();
	change_sub_buttons = $("#change_sub_options").html();
	
	if(document.getElementById("q_a").value == "pay_charge"  &&
	document.getElementById("client_debt").value !="0.0")
		pay_sub();
	
	else if (document.getElementById("q_a").value == "refresh_account")
	{
		pay_sub();
		document.getElementById("pay_sub_bill").action = "Refresh_Balance";
		document.getElementById("pay_what").value = "Ανανέωση Υπολοίπου";
	}
	else if (document.getElementById("q_a").value == "change_subscription") show_sub_mini_info();
}

function cancel_options(){
	$("#initial_profile").html(initial_profile);
	$("#initial_button").html(initial_button);
}

function show_profile_options(){
	$("#initial_profile").html(options_profile);
	$("#initial_button").html(options_buttons);
}

function show_sub_mini_info(){
	$("#initial_sub").html(change_sub);
	$("#initial_sub_button").html(change_sub_buttons);
	if(document.getElementById("client_debt").value=="0.0"){
		document.getElementById("warning_change_sub").style.display = "none";
		document.getElementById("pay_charge_button").style.display = "none";
	}
}

function cancel_sub_options(){
	$("#initial_sub").html(initial_sub);
	$("#initial_sub_button").html(initial_sub_button);
}

function pay_sub(){
	$("#initial_sub").html( $("#pay_bill").html() );
	$("#initial_sub_button").html( $("#pay_bill_buttons").html() );
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

function close_req(){
	document.getElementById("requests").style.display = "none";
	document.getElementById("requestsbutton").style.display = "none";
	window.scrollTo(0,0);
}
