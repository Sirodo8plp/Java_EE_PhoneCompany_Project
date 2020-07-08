
function NewSub() 
{
	 document.getElementById("nc").style.display = "block";
}
 
function Clear()
{
	 document.getElementById("sub_name").value = "";
	 document.getElementById("minutes").value = "";
	 document.getElementById("sms").value = "";
	 document.getElementById("mb").value = "";
	 document.getElementById("costmin").value = "";
	 document.getElementById("costsms").value = "";
	 document.getElementById("costmb").value = "";
	 document.getElementById("charge").value = "";
}
	
function Cancel_Add()
{
	document.getElementById("nc").style.display = "none";
}

function Cancel_Change()
{
	document.getElementById("change_sub").style.display = "none";
}