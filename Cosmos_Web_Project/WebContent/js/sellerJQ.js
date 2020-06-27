var initial_profile , initial_button;
var options_profile , options_buttons;
var x = 1;

var initial_sub , initial_sub_button;
var change_sub , change_sub_buttons;
var y = 1;

var pay_sub , pay_sub_buttons;
var z=1;
function cancel_options(){
	$("#initial_profile").html(initial_profile);
	$("#initial_button").html(initial_button);
}

function show_profile_options(){
	if( x==1){
		options_profile = $("#options_profile").html();
		options_buttons = $("#options_buttons").html();
		initial_profile = $("#initial_profile").html();
		initial_button = $("#initial_button").html();
		$("#initial_profile").html(options_profile);
		$("#initial_button").html(options_buttons);
		x=2;
	}
	else{
		$("#initial_profile").html(options_profile);
		$("#initial_button").html(options_buttons);
	}
}

function show_sub_mini_info(){
	if( y==1){
		initial_sub = $("#initial_sub").html();
		initial_sub_button = $("#initial_sub_button").html();
		change_sub = $("#sub_attempt").html();
		change_sub_buttons = $("#change_sub_options").html();
		$("#initial_sub").html(change_sub);
		$("#initial_sub_button").html(change_sub_buttons);
		y=2;
	}
	else{
		$("#initial_sub").html(change_sub);
		$("#initial_sub_button").html(change_sub_buttons);
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
