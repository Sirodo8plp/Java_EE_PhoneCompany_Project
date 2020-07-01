<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" import="People.Client"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="${pageContext.request.contextPath}/cssFolder/client.css">
<meta charset="utf-8">
<title>Πληροφορίες</title>
</head>
<body>

<div style="text-align:center">
	<h3 style="text-align:center;font-size:42px;color:#475a57;"> <strong>Πληροφορίες Πελάτη</strong></h3>
</div>

<div class="row">
	
	<div class="column">
		<div style="text-align:center">
			<h1 style="color:#475a57;"> <strong>Πληροφορίες Profile</strong></h1>
			<label style="text-align:center;color:#475a57">Username: </label><label style="color:#4CAF50;">${username}</label><br><br>
			<label style="text-align:center;color:#475a57">Email: </label><label style="color:#4CAF50;">${email}</label><br><br>
			<label style="text-align:center;color:#475a57">Α.Φ.Μ: </label><label style="color:#4CAF50;">${afm}</label><br><br>
			<label style="text-align:center;color:#475a57">Αριθμός: </label><label style="color:#4CAF50;">${number}</label><br><br>
			<label style="text-align:center;color:#475a57">Όνομα: </label><label style="color:#4CAF50;">${first_name}</label><br><br>
			<label style="text-align:center;color:#475a57">Επώνυμο: </label><label style="color:#4CAF50;">${surname}</label>
		</div>
	</div>
	
	<div class="column">
		<div style="text-align:center">
			<h1 style="color:#475a57;"> <strong>Πληροφορίες Προγράμματος</strong></h1>
			<label style="text-align:center;color:#475a57">Όνομα Προγράματος: </label><label style="color:#4CAF50;">${subscription}</label><br><br>
			<label style="text-align:center;color:#475a57">Πάγιο: </label><label style="color:#4CAF50;">${charge}</label><br><br>
			<label style="text-align:center;color:#475a57">Λεπτά Ομιλίας: </label><label style="color:#4CAF50;">${minutes}</label><br><br>
			<label style="text-align:center;color:#475a57">Δεδομένα: </label><label style="color:#4CAF50;">${mb}</label><br><br>
			<label style="text-align:center;color:#475a57">Μηνύματα: </label><label style="color:#4CAF50;">${sms}</label>
		</div>
	</div>
	
	<div class="column">
		<div style="text-align:center">
			<h1 style="color:#475a57;"> <strong>Πληροφορίες Λογαριασμού</strong></h1>
			<label style="text-align:center;color:#475a57">Πάγιο Προγράμματος: </label><label style="color:#4CAF50;">${charge}</label><br><br>
			<label style="text-align:center;color:#475a57">Προηγούμενες Οφειλές: </label><label style="color:#4CAF50;">${debt}</label><br><br>
			<label style="text-align:center;color:#475a57">Συνολικό Ποσό Πληρωμής: </label><label style="color:#4CAF50;">${bill}</label><br><br>
			<label style="text-align:center;color:#475a57">Ημερομηνία Λογαριασμού: </label><label style="color:#4CAF50;">${bill_date}</label><br><br>
			<label style="text-align:center;color:#475a57">Αριθμός Τηλεφώνου: </label><label style="color:#4CAF50;">${number}</label>
		</div>
	</div>
	
</div>
<br><br>
<div style="text-align:center">
	<h1 style="color:#475a57;"> <strong>Ιστορικό Κλήσεων</strong></h1><br>
	<%Client client = (Client)request.getSession().getAttribute("Client"); 
		for(int i =0;i<client.bill.CustomerCalls.size();i++){%>
			<label style="text-align:center;color:#475a57">Αριθμός Τηλεφώνου: <%=client.bill.CustomerCalls.get(i).getReceiver() %>
			</label><label style="color:#4CAF50;margin-left:5em;">Ημερομηνία: <%=client.bill.CustomerCalls.get(i).getCall_date() %></label>
			<br><br>
		<%} %>
</div>
<br><br>
<div style="text-align:center">
	<form action="Return_To_Client" method="post">
		<button class="MoreButton">Επιστροφή</button>
	</form>
</div>

</body>
</html>