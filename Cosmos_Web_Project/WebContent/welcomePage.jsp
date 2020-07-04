<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="cssFolder/homepage.css">
<meta charset="ISO-8859-1">
<title>www.cosmos.gr</title>
</head>
<body>
<label id="prof" style="visible:hidden"></label>
<button onclick="topFunction()" id="myBtn" title="Go to top"><ion-icon size="large" name="arrow-up-circle"></ion-icon></button>
	
	<div class = "content">
		<div class="header">
			<div class="topnav">
			  	<a href="Show_Subscriptions">Προγράμματα</a>
			  	<a href="#log">Είσοδος</a>
			  	<a href="register.jsp">Δημιουργία Λογαριασμού</a>
			</div>
		</div>
	</div>
	
	<br><br><br>
	
	<div class="container">
		<img src="images/sales1.jpg" alt = "45% Έκπτωση σε Online αγορές.">
		<form action="Show_Subscriptions"><button type="submit" class="btn" onClick="document.getElementById('prof').value='default'">Μάθετε περισσότερα</button></form>
	</div>
	
	<br><br>
	
	<h1 style="color:#585858;text-align:center;">Βρες Άμεσα</h1>
	
	<br><br>
	
	<header>
		<div class="scrollermenu">
			<a href="#log" onclick="return quick_access(1);"><ion-icon name="wallet"></ion-icon><br>Πληρωμή<br>Λογαριασμού</a>
			<a href="#log" onclick="return quick_access(2);"><ion-icon name="finger-print"></ion-icon><br>Ανανέωση<br>Υπολοίπου Καρτοκινητής</a>
			<a href="#log" onclick="return quick_access(3);"><ion-icon name="document-text"></ion-icon><br>Ανανέωση<br>Συμβολαίου Καρτοκινητής</a>
		</div>
	</header>
	
	<br><br><br><br>
	
	<h1 style="color:#585858;text-align:center;">Δημοφιλή Προγράμματα</h1><br><br>
	
	<table style="text-align:center;margin:0px auto;">
		<tr>
			<td><h1 style="color:#475a57">Cosmos Mobile L</h1></td>
			<td style="padding-left:25px;padding-right:25px;"><h1 style="color:#475a57">Cosmos Green Max</h1></td>
			<td><h1 style="color:#475a57">Cosmos Green Final</h1></td>
		</tr>
		<tr>
			<td><p>Cosmos mobile για απίστευτα<br>περισσότερες δυνατότητες.</p></td>
			<td style="padding-left:25px;padding-right:25px;"><p>Cosmos Green Max = GB για ΠΑΝΤΑ.<br>Για να έχεις πραγματικά πολλά data.</p></td>
			<td><p>Αναβάθμισε την επικοινωνία σου με απίστευτες<br>ταχύτητες internet και μοναδικές παροχές!</p></td>
		</tr>
		<tr>
			<td><p>Σταθερό Πάγιο </p></td>
			<td style="padding-left:25px;padding-right:25px;"><p>Σταθερό Πάγιο </p></td>
			<td><p>Σταθερό Πάγιο </p></td>
		</tr>
		<tr>
			<td><p style="font-size:35px;color:green"><del>30.90€</del></p></td>
			<td style="padding-left:25px;padding-right:25px;"><p style="font-size:35px;color:green"><del>47.48€</del></p></td>
			<td><p style="font-size:35px;color:green"><del>49.64€</del></p></td>
		</tr>
		<tr>
			<td><p style="font-size:35px;color:green">17.00 €</p></td>
			<td style="padding-left:25px;padding-right:25px;"><p style="font-size:35px;color:green">26.00 €</p></td>
			<td><p style="font-size:35px;color:green">27.30 €</p></td>
		</tr>
		<tr>
			<td>
				<form method="get" action="Show_Subscriptions">
					<button class="MoreButton">Περισσότερα<ion-icon name="arrow-redo-circle"></ion-icon></button>
					<input name="selected_sub" value="Cosmos Mobile L" style="display:none">
				</form>
			</td>
			<td style="padding-left:25px;padding-right:25px;">
				<form method="get" action="Show_Subscriptions">
					<button class="MoreButton">Περισσότερα<ion-icon name="arrow-redo-circle"></ion-icon></button>
					<input name="selected_sub" value="Cosmos Green Max" style="display:none">
				</form>
			</td>
			<td>
				<form method="get" action="Show_Subscriptions">
					<button class="MoreButton">Περισσότερα<ion-icon name="arrow-redo-circle"></ion-icon></button>
					<input name="selected_sub" value="Cosmos Green Final" style="display:none">
				</form>
			</td>
		</tr>
	</table>
	
	<div id="log" class="container1">
			<form method="post" action="${pageContext.request.contextPath}/Login">
				<h1 style="color:#475a57">Σύνδεση</h1>
				<h3 style="color:#475a57">Username</h3>
				<input class="InputTexts" type="text" name="usrnm">
				<h3 style="color:#475a57">Password</h3>
				<input class="InputTexts" type="password" name="pswrd">
				<br><br>
				<button type="submit" class="MoreButton">Σύνδεση <ion-icon name="enter"></ion-icon></button>
				<br><br>
				<a href="register.jsp">Δεν έχετε λογαριασμό;</a>
				<br><br>
				<label style="color:maroon;font-size:30px;" id="target">${error_message}</label>
				<input style="display:none" id="quick_access" name="quick_access" value="">
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

function quick_access(i){
	if (i == 1) document.getElementById("quick_access").value = "pay_charge";
	else if (i==2) document.getElementById("quick_access").value = "refresh_account";
	else document.getElementById("quick_access").value = "change_subscription";
}
</script>	
<script src="https://unpkg.com/ionicons@5.0.0/dist/ionicons.js"></script>	
</body>
</html>