<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" import="Misc.Program"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Προγράμματα</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/cssFolder/subs.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="module" src="https://unpkg.com/ionicons@5.0.0/dist/ionicons/ionicons.esm.js"></script>
<script nomodule="" src="https://unpkg.com/ionicons@5.0.0/dist/ionicons/ionicons.js"></script>
</head>
<body style ="background-color:#F7F9F9">

<div style="position:absolute;top:0;left:0;color:#4CAF50">
	<p style="font-size:30;">${infooo}</p>
</div>
<h2 style = text-align:center;color:#475a57 id="top">'Ολα τα διαθέσιμα προγράμματα μας: </h2>

<table class="tg"> 
	<thead>
		<tr>
			<th class="tg-0pky">Πρόγραμμα</th>
			<th class="tg-0pky">Λεπτά Ομιλίας</th>
			<th class="tg-0pky">SMS</th>
			<th class="tg-0pky">MB</th>
			<th class="tg-0pky">Πάγιο</th>
			<th class="tg-0pky">Κόστος Λεπτών Ομιλίας</th>
			<th class="tg-0pky">Κόστος SMS</th>
			<th class="tg-0pky">Κόστος ΜΒ</th>
			<th class="tg-0pky" style="text-align:center"></th>
			<th class="tg-0pky" style="text-align:center"></th>
		</tr>
	</thead>
	<%
		for(int i = 0; i < Program.All_Subs.size(); i ++){
	%>
	<tr>
		<%
			for(int j =0; j < 8; j++){
		%>
			<td class="tg-0pky"><%=Program.All_Subs.get(i).get(j) %></td>
		<%} %>
		<td>
		<form method="post" action="Seller_Delete_Program">
		<button type="submit" class="MoreButton">Διαγραφή</button>
		<input name="subname" style="display:none" value="<%=Program.All_Subs.get(i).get(0) %>">
		</form>
		</td>
	</tr>
	<%} %>
</table>

<br><br>

<div class="container1">
	<button type="button" class="MoreButton" style="font-size:25px;" onclick="NewSub();location.href='#bottom'">Προσθήκη Προγράμματος</button>
	<br><br>
	<ion-icon name="arrow-back-circle-sharp"></ion-icon>
	<p style = "font-size:20px;color:#475a57;"><a href="Return_To_Seller">Επιστροφή</a></p>
</div>

<div id="nc" style="text-align:center;display:none">
	<form method="post" action="Seller_Add_Subscription">
		<label>Πρόγραμμα</label>
		<br><br>
		<input required name="sub_name" id="sub_name" class="InputTexts"><br>
		<label>Λεπτά Ομιλίας</label>
		<br><br>
		<input required type="number" step="any" min="0" name="minutes" id="minutes" class="InputTexts"><br>
		<label>Μηνύματα</label>
		<br><br>
		<input required type="number" step="any" min="0" name="sms" id="sms" class="InputTexts"><br>
		<label>Δεδομένα</label>
		<br><br>
		<input required type="number" step="any" min="0" name="mb" id="mb" class="InputTexts"><br>
		<label>Κόστος λεπτού ομιλίας</label>
		<br><br>
		<input required type="number" step="any" min="0" name="costmin" id="costmin" class="InputTexts"><br>
		<label>Κόστος μηνύματος</label>
		<br><br>
		<input required type="number" step="any" min="0" name="costsms" id="costsms" class="InputTexts"><br>
		<label>Κόστος megabyte</label>
		<br><br>
		<input required type="number" step="any" min="0" name="costmb" id="costmb" class="InputTexts"><br>
		<label>Πάγιο</label>
		<br><br>
		<input required type="number" step="any" min="0" name="charge" id="charge" class="InputTexts"><br><br>
		<button class="MoreButton" type="submit" id="bottom">Προσθήκη</button><button class="MoreButton" onclick="Clear()" type="button" style="margin-left:3em">Καθαρισμός</button>
	</form>
	<br>
	<button type="button" id="cancel_add" class="MoreButton" onclick="Cancel_Add();location.href='#top'">Ακύρωση</button>
</div>

<script src="${pageContext.request.contextPath}/js/subs.js"></script>	
</body>
</html>