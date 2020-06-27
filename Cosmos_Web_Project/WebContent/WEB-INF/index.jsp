<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="cssFolder/homepage.css">
<meta charset="ISO-8859-1">
<title>www.cosmos.gr</title>
</head>
<body>
<button onclick="topFunction()" id="myBtn" title="Go to top"><ion-icon size="large" name="arrow-up-circle"></ion-icon></button>
	<header>
		<div class="scrollmenu">
			<a href="#home">Ιδιώτες</a>
  			<a href="#news">Είσοδος</a>
  			<a href="#contact">Ανανέωση</a>
  		</div>
	</header>
	<br><br><br>
	
	<div class="container">
		<img src="images/sales1.jpg" alt = "45% Έκπτωση σε Online αγορές.">
		<button class="btn">Μάθετε περισσότερα</button>
	</div>
	<br><br>
	<h1 style="color:#585858;text-align:center;">Βρες Άμεσα</h1>
	<br><br>
	<header>
		<div class="scrollermenu">
			<a href="pay"><ion-icon name="wallet"></ion-icon><br>Πληρωμή<br>Λογαριασμού</a>
			<a href="refresh"><ion-icon name="finger-print"></ion-icon><br>Ανανέωση<br>Υπολοίπου Καρτοκινητής</a>
			<a href="refresh1"><ion-icon name="document-text"></ion-icon><br>Ανανέωση<br>Συμβολαίου Καρτοκινητής</a>
			<a href="refresh2"><ion-icon name="call"></ion-icon><br>Αναβάθμηση<br>Σταθερής</a>
		</div>
	</header>
	
	<br><br>
	<h1 style="color:#585858;text-align:center;">Δημοφιλή Προγράμματα</h1>
	<br><br>
		<div class="row center1">
			<div class="column">
				<div class="header">
					<h1 style="color:#475a57">Cosmos Double Play 50 L</h1>
					<p>Cosmos fiber για απίστευτα περισσότερες δυνατότητες</p>
					<p>Σταθερό Πάγιο :</p>
					<p style="font-size:35px;color:green">34,50€</p>
					<button class="MoreButton">Περισσότερα</button>
				</div>
			</div>
			<div class="column">
				<div class="header">
					<h1 style="color:#475a57">Cosmos GIGA Max 10GB</h1>
					<p>Cosmos GIGA Max διπλάσια GB για ΠΑΝΤΑ</p><p>για να έχεις πραγματικά πολλά data</p>
					<p>Σταθερό Πάγιο :</p>
					<p style="font-size:35px;color:green">25,32€</p>
					<button class="MoreButton">Περισσότερα</button>
				</div>
			</div>
		</div>
		<br><br><br><br>
		<div class="container1">
			<button class="MoreButton">Όλα τα cosmos προγράμματα <ion-icon name="enter"></ion-icon></button>
		</div>
		
		<br>
		
		<div class="container1">
			<form action="/profile">
				<h1 style="color:#475a57">Σύνδεση</h1>
				<h3 style="color:#475a57">Username</h3>
				<input class="InputTexts" type="text">
				<h3 style="color:#475a57">Password</h3>
				<input class="InputTexts" type="text">
				<br><br>
				<button class="MoreButton">Σύνδεση <ion-icon name="enter"></ion-icon></button><br><br>
				<a href="register.jsp">Δεν έχετε λογαριασμό;</a>
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
</script>	
<script src="https://unpkg.com/ionicons@5.0.0/dist/ionicons.js"></script>	
</body>
</html>