<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="${pageContext.request.contextPath}/cssFolder/seller.css">
<meta charset="utf-8">
<title>Πληροφορίες</title>
</head>
<body>

<table>
	<tr>
		
		<td><img src = "${pageContext.request.contextPath}/images/seller_image.jpg" style="float:left;"></td>
		
		<td>
			<label style="text-align:center;color:#475a57">Username: </label><label style="color:#4CAF50;">${username}</label><br>
			<label style="text-align:center;color:#475a57">Email: </label><label style="color:#4CAF50;">${email}</label><br>
			<label style="text-align:center;color:#475a57">Α.Φ.Μ: </label><label style="color:#4CAF50;">${afm}</label><br>
			<label style="text-align:center;color:#475a57">Αριθμός: </label><label style="color:#4CAF50;">${number}</label><br>
			<label style="text-align:center;color:#475a57">Όνομα: </label><label style="color:#4CAF50;">${first_name}</label><br>
			<label style="text-align:center;color:#475a57">Επώνυμο: </label><label style="color:#4CAF50;">${surname}</label><br><br>
		</td>
		
	</tr>
</table>

<table>
	<tr>
		
		<td>
			<label style="text-align:center;color:#475a57">Όνομα Προγράματος: </label><label style="color:#4CAF50;">${subscription}</label><br>
			<label style="text-align:center;color:#475a57">Πάγιο: </label><label style="color:#4CAF50;">${charge}</label><br>
			<label style="text-align:center;color:#475a57">Λεπτά Ομιλίας: </label><label style="color:#4CAF50;">${minutes}</label><br>
			<label style="text-align:center;color:#475a57">Δεδομένα: </label><label style="color:#4CAF50;">${mb}</label><br>
			<label style="text-align:center;color:#475a57">Μηνύματα: </label><label style="color:#4CAF50;">${sms}</label><br>
		</td>
		
	</tr>
</table>

<table>
	<tr>
		
		<td>
			<label style="text-align:center;color:#475a57">Πάγιο Προγράμματος: </label><label style="color:#4CAF50;">${charge}</label><br>
			<label style="text-align:center;color:#475a57">Προηγούμενες Οφειλές: </label><label style="color:#4CAF50;">${debt}</label><br>
			<label style="text-align:center;color:#475a57">Συνολικό Ποσό Πληρωμής: </label><label style="color:#4CAF50;">${bill}</label><br>
			<label style="text-align:center;color:#475a57">Ημερομηνία Λογαριασμού: </label><label style="color:#4CAF50;">${bill_date}</label><br>
			<label style="text-align:center;color:#475a57">Αριθμός Τηλεφώνου: </label><label style="color:#4CAF50;">${number}</label><br>
		</td>
		
	</tr>
</table>

<table>
	<tr>
		
		<td>
			<label style="text-align:center;color:#475a57">Δέκτης: </label><label style="color:#4CAF50;">${charge}</label><br>
			<label style="text-align:center;color:#475a57">Ημερομηνία Κλήσης: </label><label style="color:#4CAF50;">${debt}</label><br>
		</td>
		
	</tr>
</table>

</body>
</html>