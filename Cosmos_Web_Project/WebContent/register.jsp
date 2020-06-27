<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="${pageContext.request.contextPath}/cssFolder/register.css">
<meta charset="UTF-8">
<title>Cosmos Registration</title>
</head>
<body  onload="document.fform.reset();">
<button onclick="topFunction()" id="myBtn" title="Go to top"><ion-icon size="large" name="arrow-up-circle"></ion-icon></button>
	<div class="container1">
		<form method="post" action="RegisterServlet" onSubmit = "return checkPassword(this)" name="fform">
		
			<input type="hidden" name="requestType" value="Insert" />	
			
			<h1 style="color:#475a57">Cosmos Εγγραφή</h1>
			
			<label style="color:maroon">${reg_error}</label>
			
			<div class="tooltip"><h1 style="color:#475a57">Ιδιότητα<ion-icon name="help-circle"></ion-icon></h1>
				<span class="tooltiptext">Η ιδιότητα μπορεί να είναι μόνο<br>Client,Seller ή Admin.</span>
			</div>
			
			<br><br>
			
			<input class="InputTexts" type="text" style="width:300px" name="type" id ="type" placeholder = "Client,Seller,Admin" onkeyup ="disableFields(this)" required  ><br><br>			
			
			<h1 style="color:#475a57">Όνομα σύνδεσης χρήστη</h1>
			
			<br><br>
			
			<input class="InputTexts" type="text" id ="username" name="username" required>
			
			<h1 style="color:#475a57">Κωδικός</h1>
			
			<br><br>
			
			<input class="InputTexts" type="password" id = "password1" name="password1" required>
			
			<h1 style="color:#475a57">Επανάληψη κωδικού</h1>
			
			<br><br>
			
			<input class="InputTexts" type="password" id="password2" name="password2" required>
			
			<h1 style="color:#475a57">Email</h1>
			
			<br><br>
			
			<input class="InputTexts" style="width:300px" type="email" id = "email" name="email" placeholder ="example@gmail.com" required>
			
			<h1 style="color:#475a57">Κινητό/Σταθερό τηλέφωνο</h1>
			
			<br><br>
			
			<input class="InputTexts" style="width:300px" type="tel" pattern="[0-9]{10}" maxlength = "10" id = "number" name="number" required>
			
			<h1 style="color:#475a57">Όνομα</h1>
			
			<br><br>
			
			<input class="InputTexts" type="text" id ="fname" name="fname" required>
			
			<h1 style="color:#475a57">Επώνυμο</h1>
			
			<br><br>
			
			<input class="InputTexts" type="text" id="lname" name="lname" required>	
			
			<h1 style="color:#475a57">Α.Φ.Μ(για ιδιώτες)</h1>
			
			<br><br>
			
			<input class="InputTexts" style="width:300px" type="text" id ="afm" name="afm"  maxlength = "9" pattern="[0-9]{9}"  required>
			
			<br>
			
			<div class="tooltip"><h1 style="color:#475a57" >Ειδικός κωδικός<ion-icon name="help-circle"></ion-icon></h1>
				<span class="tooltiptext">Ο ειδικός κωδικός αφορά μόνο τους Sellers και τους Admins.</span>
			</div>
			
			<br><br>
			
			<input class="InputTexts" style="width:300px" type="password" id = "SPcode" name="SPcode" maxlength = "15" required>
			
			<br><br>
			
			<button class="MoreButton">Εγγραφή<ion-icon name="enter"></ion-icon></button>
			
			<br><br>
			
		</form>
	</div>
<script>

//Get the button
var mybutton = document.getElementById("myBtn");

// When the user scrolls down 20px from the top of the document, show the button
window.onscroll = function() {scrollFunction()};

function scrollFunction() {
  if (document.body.scrollTop > 20 || document.documentElement.scrollTop > 20) {
    mybutton.style.display = "block";
  } else {
    mybutton.style.display = "none";
  }
}

// When the user clicks on the button, scroll to the top of the document
function topFunction() {
  document.body.scrollTop = 0;
  document.documentElement.scrollTop = 0;

}

// Check if given passwords are applicable.
function checkPassword(form) { 
    password1 = form.password1.value; 
    password2 = form.password2.value; 
    
    if (password1 == '') {
        alert ("Εισάγετε κωδικό.");
        return false;
    }
    else if (password2 == '') {
        alert ("Επιβεβαιώστε τον κωδικό."); 
        return false;
    }
    else if (password1 != password2) { 
        alert ("Οι κωδικοί δεν ταιριάζουν, προσπαθήστε ξανά.") 
        return false; 
    } 
    else{ 
        return true; 
    }     
}

function disableFields(form){
		
	if(document.getElementById("type").value == "Client"){
		document.getElementById("SPcode").disabled = true;
		document.getElementById("number").disabled = false;
		document.getElementById("afm").disabled = false;
		document.getElementById("password1").disabled = false;
		document.getElementById("password2").disabled = false;
		document.getElementById("email").disabled = false;
		document.getElementById("fname").disabled = false;
		document.getElementById("lname").disabled = false;
		document.getElementById("username").disabled = false;
	}
	else if(document.getElementById("type").value == "Seller" || document.getElementById("type").value == "Admin"){
		document.getElementById("username").disabled = false;
		document.getElementById("SPcode").disabled = false;
		document.getElementById("number").disabled = true;
		document.getElementById("afm").disabled = true;
		document.getElementById("email").disabled = false;
		document.getElementById("fname").disabled = false;
		document.getElementById("lname").disabled = false;
		document.getElementById("password1").disabled = false;
		document.getElementById("password2").disabled = false;
	}
	else{
		document.getElementById("username").disabled = true;
		document.getElementById("password1").disabled = true;
		document.getElementById("password2").disabled = true;
		document.getElementById("email").disabled = true;
		document.getElementById("number").disabled = true;
		document.getElementById("afm").disabled = true;
		document.getElementById("SPcode").disabled = true;
		document.getElementById("fname").disabled = true;
		document.getElementById("lname").disabled = true;
		
	}
}
</script>	
<script src="https://unpkg.com/ionicons@5.0.0/dist/ionicons.js"></script>
</body>
</html>