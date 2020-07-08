<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" session="true"
    import="People.Admin"%>
   <%
response.setHeader("Cache-Control", "no-cache");
response.setHeader("Cache-Control", "no-store");
response.setHeader("Pragma", "no-cache");
response.setDateHeader("Expires", 0);
%>
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/cssFolder/Admin.css">
	<meta charset="utf-8">
	<title>Κεντρική σελίδα Διαχειριστή</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>

<body style = "background-color:color:#475a57;">


	<div style="position:absolute;top:0;left:0;font-size:35px;color:#4CAF50">
		<p style="font-size:40;">${infooo} </p>
	</div>
	
	<div class = "content">
		<div class="header">
			<div class="topnav">
			  	<a href="logout">Αποσύνδεση</a>
			</div>
		</div>
	</div>
	
	<h3 style="text-align:center;font-size:42px;color:#475a57;" id="top"> <strong> Καλώς Ήρθατε ${username}</strong></h3>
		
	<div id="initial_profile">
		<table style="margin: 0px auto;">
			<tr>
				<td><img src = "${pageContext.request.contextPath}/images/seller_image.jpg" style="float:left;"></td>
				<td>
					<label style="margin-left:5em;color:#475a57">Όνομα Εισόδου χρήστη : </label><label style="color:#4CAF50;">${username}</label><br><br>
					<label style="margin-left:5em;color:#475a57"> Email : </label><label style="color:#4CAF50;">${email}</label><br><br>
					<label style="margin-left:5em;color:#475a57">Όνομα : </label><label style="color:#4CAF50;">${firstname}</label><br><br>
					<label style="margin-left:5em;color:#475a57">Επώνυμο : </label><label style="color:#4CAF50;">${lastname}</label><br>
				</td>
			</tr>
		</table>
	</div>
	
	<br><br>

	<div style="text-align:center" id="initial_profile_button">
		<button class="MoreButton" onclick="account_options()">Ρυθμίσεις Λογαριασμού</button>
	</div>

	<br><br><br><br><br><br>

	<div class="row" style="margin:0px auto;text-align:center;">
	
		<div class = "column">			
			<div class="container2" id="add_c_button">
				<a onclick="return add_c();">
				<ion-icon name="add-circle-sharp"></ion-icon>
				</a>
				<p style = "font-size:20px;color:#475a57;"> Προσθήκη νέου πελάτη.</p>
			</div>	
		</div>
		
		<div class = "column">
			<div class="container2" id="add_s_button">
				
				<a onclick="return add_s();">
				<ion-icon name="person-add-sharp"></ion-icon>
				</a>
				
				<p style = "font-size:20px;color:#475a57;"> Προσθήκη νέου πωλητή.</p>
			</div>
		</div>
		
	</div>
	
	<div class="row" style="margin:0px auto;">
	
		<div class = "column">			
			<div class="container2" id="delete_client_button">
				<a onclick="return delete_c();">
				<ion-icon name="close-sharp"></ion-icon>
				</a>
				<p style = "font-size:20px;color:#475a57;"> Διαγραφή πελάτη.</p>
			</div>	
		</div>
		
		<div class = "column">
			<div class="container2">
				<a onclick="return CreateBill();">
					<ion-icon name="trash-sharp"></ion-icon>
				</a>
				<p style = "font-size:20px;color:#475a57;"> Διαγραφή πωλητή.</p>
			</div>
		</div>
	
	</div>
	
	<div class="container2">
		<h3 style="text-align:center;font-size:26px;color:#475a57;"> <strong>Το κατάστημα σας βρίσκεται εδώ: </strong> </h3>
	</div>
	
	
		<div class="gmap_canvas">
			<iframe width="450" height="450" id="gmap_canvas" 
			src="https://maps.google.com/maps?q=university%20of%20piraeus&t=&z=15&ie=UTF8&iwloc=&output=embed" 
			frameborder="0" scrolling="no" marginheight="0" marginwidth="0">
			</iframe>
			<style>
				.gmap_canvas {margin:auto;overflow:hidden;background:none!important;height:450px;width:450px;}
			</style>
		</div>





	<!--  DIVS FOR REPLACEMENT -->
	
	
	

	<div class="top_right_corner" style="display:none" id="create_bill">
	
		<form method="post" action="Delete_Seller">
			<h3 style="text-align:center;font-size:42px;color:#475a57;"> <strong> Διαγραφή πωλητή</strong></h3>
			<label style="color:#475a57">Όνομα σύνδεσης πωλητή</label><br><br>
			<input class="InputTexts" name="username" id ="username"  required><br><br>
			<label style="color:#475a57">Κωδικός διαχειριστή</label><br><br>
			<input class="InputTexts" name="admin_code" id ="admin_code" required><br><br>
			
		<button type="submit" class="MoreButton">Διαγραφή</button><br><br>
			<button type="button" onclick="document.getElementById('create_bill').style.display='none';" class="MoreButton">Ακύρωση</button>
		</form>
		
	</div>		
			
			
			
	<div class="top_right_corner" style="display:none" id="delete_client">
	
		<form method="post" action="Delete_Client">
			<h3 style="text-align:center;font-size:42px;color:#475a57;"> <strong> Διαγραφή πελάτη</strong></h3>
			<label style="color:#475a57">ID πελάτη</label><br><br>
			<input class="InputTexts" name="client_id" id ="client_id"  required><br><br>
			<label style="color:#475a57">Κωδικός διαχειριστή</label><br><br>
			<input class="InputTexts" name="admin_code" id ="admin_code" required><br><br>
			
			
			<button type="submit" class="MoreButton">Διαγραφή</button><br><br>
			<button type="button" onclick="document.getElementById('delete_client').style.display='none';" class="MoreButton">Ακύρωση</button>
		</form>
		
	</div>

		<div class="container" id="options_profile" style="display:none">
		<table style="margin: 0px auto;">
			<tr>
				<td><img src = "${pageContext.request.contextPath}/images/seller_image.jpg" style="float:left;">
				</td>
				<td>
					<form id="account" method="post" action="Update_Admin_Account">
						<label style="margin-left:5em;color:#475a57">Όνομα Εισόδου χρήστη : </label>
						<input name="newUsername" class="InputTexts" type="text" id ="username" name="username" placeholder="<%= session.getAttribute("username") %>">
						<br><br>
						<label style="margin-left:5em;color:#475a57"> Email : </label><br>
						<input name="newEmail" style="margin-left:3em;" class="InputTexts1" type="text" id ="username" name="username" placeholder="<%= session.getAttribute("email") %>">
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
		<button class="MoreButton" id="cancel_profile" onclick="show_profile()">Ακύρωση</button><br><br>
	</div>


	<div class="top_left_corner" style="display:none" id="add_client">
		<form method="post" action="Admin_adds_Client">
			<h3 style="text-align:center;font-size:42px;color:#475a57;">Προσθήκη Πελάτη</h3>
		
			<label style="color:maroon">${reg_error}</label>
			
			<h1 style="color:#475a57">Όνομα σύνδεσης χρήστη</h1>
			<br><br>
			<input class="InputTexts" type="text" id ="username" name="username" required>
			<h1 style="color:#475a57">Κωδικός</h1>
			<br><br>
			<input class="InputTexts" type="password" id = "password1" name="password1" required>
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
			<h1 style="color:#475a57">Α.Φ.Μ</h1>
			<br><br>
			<input class="InputTexts" style="width:300px" type="text" id ="afm" name="afm"  maxlength = "9" pattern="[0-9]{9}"  required>	
			<br><br>
			<button class="MoreButton" type="submit">Καταχώρηση</button>
			<button class="MoreButton" type="button" onclick="cancel_add_c();location.href='#top'">Ακύρωση</button>
		</form>
	</div>
	<br><br>

	
		<div class="top_left_corner" style="display:none" id="add_seller" name>
		<form method="post" action="Admin_Adds_Seller">
			<h3 style="text-align:center;font-size:42px;color:#475a57;">Προσθήκη Πωλητή</h3>
		
			<label style="color:maroon">${reg_error}</label>
			
			<h1 style="color:#475a57">Όνομα σύνδεσης χρήστη</h1>
			<br><br>
			<input class="InputTexts" type="text" id ="username" name="username" required>
			<h1 style="color:#475a57">Κωδικός</h1>
			<br><br>
			<input class="InputTexts" type="password" id = "password1" name="password1" required>
			<h1 style="color:#475a57">Email</h1>
			<br><br>
			<input class="InputTexts" style="width:300px" type="email" id = "email" name="email" placeholder ="example@gmail.com" required>
			<h1 style="color:#475a57">Κωδικός πωλητή</h1>
			<br><br>
			<input class="InputTexts" style="width:300px" type="text"  id = "seller_code" name="seller_code" required>
			<h1 style="color:#475a57">Όνομα</h1>
			<br><br>
			<input class="InputTexts" type="text" id ="fname" name="fname" required>
			<h1 style="color:#475a57">Επώνυμο</h1>
			<br><br>
			<input class="InputTexts" type="text" id="lname" name="lname" required>	
			<br><br>
			<button class="MoreButton" type="submit">Καταχώρηση</button>
			<button class="MoreButton" type="button" onclick="cancel_add_s();location.href='#top'">Ακύρωση</button>
		</form>
	</div>
	<br><br>
	
	
	
	
	
	





<script src="${pageContext.request.contextPath}/js/sellerJQ.js"></script>	
<script src="https://unpkg.com/ionicons@5.0.0/dist/ionicons.js"></script>
</body>