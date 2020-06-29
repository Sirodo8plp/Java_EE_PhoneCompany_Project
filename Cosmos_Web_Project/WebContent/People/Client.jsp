<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" session="true"%>
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/cssFolder/seller.css">
	<meta charset="utf-8">
	<title>Κεντρική σελίδα Πωλητή</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script type="module" src="https://unpkg.com/ionicons@5.0.0/dist/ionicons/ionicons.esm.js"></script>
	<script nomodule="" src="https://unpkg.com/ionicons@5.0.0/dist/ionicons/ionicons.js"></script>
</head>

<body style = "background-color:color:#475a57;">
<!-- 
					SESSION VARIABLES
					
			Table : subs | subname , submins , submb , subsms , subcharge
			
			Table : client | username , email , firstname , lastname
			
			Table : client_subs | minleft , mbleft , smsleft
-->

<input type="text" style="display:none" id="q_a" value="${quick_access}">
<input type="text" style="display:none" id="client_charge" value="${currentBill}">
<input type="text" style="display:none" id="client_debt" value="${debtt}">

<div style="position:absolute;top:0;left:0;font-size:35px;color:#4CAF50">
	<p style="font-size:40;">${infooo} </p>
</div>
	<div class = "content">
		<div class="header">
			<div class="topnav">
			  	<a href="#main">Αρχική</a>
			  	<a href="#requests">Αιτήματα</a>
			  	<a href="Client_Information">Πληροφορίες</a>
			  	<a href="logout">Αποσύνδεση</a>
			</div>
		</div>
	</div>
	
	<h3 style="text-align:center;font-size:42px;color:#475a57;"> <strong> Καλώς Ήρθατε ${username}
	
	</strong></h3>
		
	<div class="row">
		<div class="column">
			<div class="header">
				<div style="text-align:center" id="initial_sub">
					<h1 style="text-align:center;font-size:26px;color:#475a57;">Είστε εγγεγραμένοι στο πρόγραμμα: <br>${subname}</h1>
					<label style="text-align:center;color:#475a57">Λεπτά Ομιλίας: </label><label style="color:#4CAF50;">${submins}</label><br><br>
					<label style="text-align:center;color:#475a57">Δεδομένα: </label><label style="color:#4CAF50;">${submb}</label><br><br>
					<label style="text-align:center;color:#475a57">Μηνύματα: </label><label style="color:#4CAF50;">${subsms}</label><br><br>
					<label style="text-align:center;color:#475a57">Πάγιο: </label><label style="color:#4CAF50;">${subcharge}</label><br><br>
				</div>
			</div>
		</div>
		<div class="column">
			<div class="container" id="initial_profile">
				<table style="margin: 0px auto;">
					<tr>
						<td><img src = "${pageContext.request.contextPath}/images/seller_image.jpg" style="float:left;"></td>
						<td>
							<label style="margin-left:5em;color:#475a57">Όνομα Εισόδου χρήστη : </label><label style="color:#4CAF50;">${username}</label><br><br>
							<label style="margin-left:5em;color:#475a57"> Email : </label><br><label style="margin-left:5em;color:#4CAF50;">${email}</label><br><br>
							<label style="margin-left:5em;color:#475a57">Όνομα : </label><label style="color:#4CAF50;">${firstname}</label><br><br>
							<label style="margin-left:5em;color:#475a57">Επώνυμο : </label><label style="color:#4CAF50;">${lastname}</label><br>
						</td>
					</tr>
				</table>
			</div>
		</div>
		<div class="column">
			<div style="text-align:center">
				<h1 style="text-align:center;font-size:26px;color:#475a57;">Αντιμετωπίζετε κάποιο πρόβλημα;</h1>
				<form method="post" action="Insert_Request" id="insrt">
					<textarea name="problem" placeholder="Περιγράψτε το πρόβλημα σας." cols="45" rows="5" style="resize:none"></textarea>
				</form><br><br>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="column">
			<div style="text-align:center" id="initial_sub_button">
				<button class="MoreButton" id="change_sub" onclick="show_sub_mini_info()">Αλλαγή Συμβολαίου</button>
			</div>
		</div>
		<div class="column">
			<div style="text-align:center" id="initial_button">
				<button class="MoreButton" id="change_account" onclick="show_profile_options()">Ρυθμίσεις Λογαριασμού</button>
			</div>
		</div>
		<div class="column">
			<div style="text-align:center">
				<button class="MoreButton" type="submit" form="insrt">Υποβολή Αιτήματος</button>
			</div>
		</div>
	</div>
	<br><br><br><br><br><br>
		
	<div class ="row">
		<div class = "column">			
			<div class="container2">
				<ion-icon name="call-sharp"></ion-icon>
				<p style = "font-size:20px;color:#475a57;">Διαθέσιμα λεπτά ομιλίας:<br>${minleft}</p>
			</div>	
		</div>
		<div class = "column">
			<div class="container2">
				<ion-icon name="globe-sharp"></ion-icon>
				<p style = "font-size:20px;color:#475a57;">Διαθέσιμα δεδομένα:<br>${mbleft}</p>
			</div>
		</div>
		<div class = "column">
			<div class="container2">
				<ion-icon name="chatbox-ellipses-sharp"></ion-icon>
				<p style = "font-size:20px;color:#475a57;">Διαθέσιμα μηνύματα:<br>${smsleft}</p>
			</div>
		</div>
	</div>
	
	<br>
	
	<div class="container2">
		<h3 style="text-align:center;font-size:26px;color:#475a57;"> <strong>Βρείτε μας εδώ: </strong> </h3>
	</div>
	
	
		<div class="mapouter">
		<div class="gmap_canvas">
			<p style="text-align:text;"><iframe width="450" height="450" id="gmap_canvas" 
			src="https://maps.google.com/maps?q=university%20of%20piraeus&t=&z=15&ie=UTF8&iwloc=&output=embed" 
			frameborder="0" scrolling="no" marginheight="0" marginwidth="0">
			</iframe></p>
			<style>
				.mapouter{height:450px;width:450px;margin:auto;}.gmap_canvas {overflow:hidden;background:none!important;height:450px;width:450px;}
			</style>
		</div>
		</div>
		
	<!-- divs for replacement -->
	
	<div class="container" id="options_profile" style="display:none">
		<table style="margin: 0px auto;">
			<tr>
				<td><img src = "${pageContext.request.contextPath}/images/seller_image.jpg" style="float:left;">
				</td>
				<td>
					<form id="account" method="post" action="Update_Account">
						<label style="margin-left:5em;color:#475a57">Όνομα Εισόδου χρήστη : </label>
						<input name="newUsername" style="margin-left:3em;" class="InputTexts" type="text" id ="username" name="username" placeholder="<%= session.getAttribute("username") %>">
						<br><br>
						<label style="margin-left:5em;color:#475a57"> Email : </label><br>
						<input name="newEmail" style="margin-left:3em;" class="InputTexts" type="text" id ="username" name="username" placeholder="<%= session.getAttribute("email") %>">
						<br><br>
						<label style="margin-left:5em;color:#475a57">Όνομα : </label>
						<input name="newFname" class="InputTexts" type="text" id ="username" name="username" placeholder="<%= session.getAttribute("firstname") %>">
						<br><br>
						<label style="margin-left:5em;color:#475a57">Επώνυμο : </label>
						<input name="newLname" class="InputTexts" type="text" id ="username" name="username" placeholder="<%= session.getAttribute("lastname") %>">
						<br>
					</form>
				</td>
			</tr>
		</table>
	</div>	
	
	<div style="text-align:center;display:none;" id="options_buttons">
		<button class="MoreButton" form="account" type="submit">Αποθήκευση Ρυθμίσεων</button><br><br>
		<button class="MoreButton" id="cancel_profile" onclick="cancel_options()">Ακύρωση</button><br><br>
	</div>	
	
	<div id="sub_attempt" style="display:none;">
		<form method="post" action="Change_Subscription" id = "account">
			<div style="text-align:center">
				<h1 style="text-align:center;font-size:26px;color:#475a57;">Αυτήν την στιγμή οφείλετε: <br>
				<label style="color:#4CAF50;">${currentBill} €</label></h1>
				<label style="text-align:center;color:#475a57">Προηγούμενες Οφειλές:</label><label style="color:#4CAF50;">${debtt} €</label><br><br>
				<label style="text-align:center;color:#475a57">Έχετε ακόμα:</label><br>
				<label style="color:#4CAF50;">${minleft}</label><label style="text-align:center;color:#475a57">	Λεπτά Ομιλίας</label><br>
				<label style="color:#4CAF50;">${mbleft}</label><label style="text-align:center;color:#475a57">	ΜΒ</label><br>
				<label style="color:#4CAF50;">${smsleft}</label><label style="text-align:center;color:#475a57">	sms</label><br>	
				<br><label style="color:#FF0000">Αν αλλάξετε πρόγραμμα τα υπόλοιπα λεπτά ομιλίας,<br>δεδομένα και sms θα χαθούν.</label><br>
				<br><label style="color:#8B0000" id="warning_change_sub">Δεν μπορείται να αλλάξετε πρόγραμμα αν δεν έχετε<br>πληρώσει τον τρέχων λογαριασμό ή<br>
				αν έχετε προηγούμενες οφειλές.</label>
				<br>
				<button class="MoreButton" onclick="pay_sub()" id="pay_charge_button">Πληρωμή Λογαριασμού</button>
				<br><br><br>
				<h1 style="text-align:center;font-size:26px;color:#475a57;">Επιλογή προγράμματος</h1>
				<div class="box">
				  <select name="selected_sub">
				    <option value="Cosmos Mobile L">Cosmos Mobile L</option>
				    <option value="Cosmos Mobile XL">Cosmos Mobile XL</option>
				    <option value="Cosmos Mobile S">Cosmos Mobile S</option>
				    <option value="Cosmos Mobile M">Cosmos Mobile M</option>
				    <option value="Cosmos Green Max">Cosmos Green Max</option>
				    <option value="Cosmos Green Start">Cosmos Green Start</option>
				    <option value="Cosmos Green Plus">Cosmos Green Plus</option>
				    <option value="Cosmos Green Final">Cosmos Green Final</option>
				  </select>
				</div>	
				<input style="display:none" name="billl" value="${currentBill}">
				<input style="display:none" name="billl1" value="${debtt}">
			</div>
		</form>
	</div>
	
	<div style="text-align:center;display:none;" id="change_sub_options">
		<button class="MoreButton" form="account" type="submit">Επιλογή Προγράμματος</button><br><br>
		<button class="MoreButton" id="cancel_profile" onclick="cancel_sub_options()">Ακύρωση</button><br><br>
	</div>	
	
	<div  id="pay_bill" style="display:none">
		<div style="text-align:center">
			<form method="post" action="Pay_Account" id="pay_sub_bill">
				<h1 style="text-align:center;font-size:26px;color:#475a57;" id="pay_what">Πληρωμή Λογαριασμού</h1><br>
				<h1 style="text-align:center;font-size:26px;color:#475a57;">Αριθμός Κάρτας</h1><br>
				<img id="bank" width="50" height="50" style="visibility:hidden"><br>
				<input name="card_number" class="InputTexts1" type="text" id ="card_number" maxlength = "16" pattern="[0-9]{16}" onkeyup="define_card(this)"><br><br>
				<h1 style="text-align:center;font-size:26px;color:#475a57;">Κωδικός Ασφαλείας Κάρτας</h1><br>
				<input name="card_sc" class="InputTexts" type="text" id ="card_sc" maxlength = "3" pattern="[0-9]{3}" type="password">
			</form>
		</div>
	</div>
	
	<div style="text-align:center;display:none;" id="pay_bill_buttons">
		<button class="MoreButton" form="pay_sub_bill" type="submit">Πληρωμή</button><br><br>
		<button class="MoreButton" id="cancel_profile" onclick="cancel_sub_options()">Ακύρωση</button><br><br>
	</div>

<script src="${pageContext.request.contextPath}/js/sellerJQ.js"></script>
<script>
	function define_card(form){
		if(document.getElementById("card_number").value.startsWith("0") || document.getElementById("card_number").value.startsWith("1") ||
				document.getElementById("card_number").value.startsWith("2") || document.getElementById("card_number").value.startsWith("3"))
			{
				document.getElementById("bank").src = '${pageContext.request.contextPath}/images/alphabank.jpg';
				document.getElementById("bank").style.visibility = "visible";
			}
		
		else if (document.getElementById("card_number").value.startsWith("4") || document.getElementById("card_number").value.startsWith("5") ||
				document.getElementById("card_number").value.startsWith("6"))
			{
				document.getElementById("bank").src = '${pageContext.request.contextPath}/images/eurobank.png';
				document.getElementById("bank").style.visibility = "visible";
			}
		
		else if(document.getElementById("card_number").value.startsWith("7") || document.getElementById("card_number").value.startsWith("8") ||
				document.getElementById("card_number").value.startsWith("9"))
		{
			document.getElementById("bank").src = '${pageContext.request.contextPath}/images/piraeusbank.png';
			document.getElementById("bank").style.visibility = "visible";
		}
		else {
			document.getElementById("bank").style.visibility = "hidden";
		}
	}
</script>
</body>
</html>