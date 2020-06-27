<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" session="true"%>
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/cssFolder/seller.css">
	<meta charset="utf-8">
	<title>Κεντρική σελίδα Πωλητή</title>
</head>

<body style = "background-color:color:#475a57;">

	<div class = "content">
		<div class="header">
			<div class="topnav">
			  	<a href="#main">Αρχική</a>
			  	<a href="#requests">Αιτήματα</a>
			  	<a href="#info">Πληροφορίες</a>
			  	<a href="#logout">Αποσύνδεση</a>
			</div>
		</div>
	</div>
	
	<h3 style="text-align:center;font-size:42px;color:#475a57;"> <strong> Καλώς Ήρθατε ${username}
	</strong></h3>
		
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
	
	<br><br><br><br><br><br>
		
	<div class ="row">
		<div class = "column">			
			<div class="container2">
				<ion-icon name="add-circle-sharp"></ion-icon>
				<p style = "font-size:20px;color:#475a57;"> Προσθήκη νέου πελάτη.</p>
			</div>	
		</div>
		<div class = "column">
			<div class="container2">
				<a href="SubsServlet">  
				<ion-icon name="list-circle-sharp"></ion-icon>
				</a>
				<p style = "font-size:20px;color:#475a57;"> Διαθέσιμα προγράμματα.</p>
			</div>
		</div>
		<div class = "column">
			<div class="container2">
				<ion-icon name="card-sharp"></ion-icon>
				<p style = "font-size:20px;color:#475a57;"> Έκδοση λογαριασμού πελάτη.</p>
			</div>
		</div>
	</div>
	
	<br>
	
	<div class="container2">
		<h3 style="text-align:center;font-size:26px;color:#475a57;"> <strong> Το κατάστημά σας βρίσκεται εδώ: </strong> </h3>
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
		
	
	
	
	
	
<script src="https://unpkg.com/ionicons@5.0.0/dist/ionicons.js"></script>
</body>
</html>