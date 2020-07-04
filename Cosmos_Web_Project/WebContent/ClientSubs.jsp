<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"
    import="Misc.Program"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="cssFolder/homepage.css">
<meta charset="ISO-8859-1">
<title>Cosmos Προγράμματα</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
<input style="display:none" id="prog" value="${sub_selected}">
<h3 style="text-align:center;font-size:42px;color:#475a57;"> <strong>Cosmos Προγράμματα</strong></h3>

<div class="row">

	<div class="column">
		<%
			for(int i = 0; i < Program.All_Subs.size(); i ++){
				String newC = String.valueOf(Double.parseDouble(Program.All_Subs.get(i).get(4)) -0.45 * Double.parseDouble(Program.All_Subs.get(i).get(4)));
		%>
		<button class="collapsible" id="<%=Program.All_Subs.get(i).get(0)%>"><%=Program.All_Subs.get(i).get(0)%></button>
			<div class="content_collapsible">
					<div style="text-align:center">
						<br><br><br>
						<label style="color:#4CAF50;">Λεπτά Ομιλίας:</label>
						<label style="text-align:center;color:#475a57;margin-left:5em;"><%=Program.All_Subs.get(i).get(1) %></label><br>
						<label style="color:#4CAF50;">Μηνύματα:</label>
						<label style="text-align:center;color:#475a57;margin-left:5em;"><%=Program.All_Subs.get(i).get(2) %></label><br>
						<label style="color:#4CAF50;">MB:</label>
						<label style="text-align:center;color:#475a57;margin-left:5em;"><%=Program.All_Subs.get(i).get(3) %></label><br>
						<label style="color:#4CAF50;">Πάγιο:</label>
						<label style="text-align:center;color:#475a57;margin-left:5em;"><del><%=Program.All_Subs.get(i).get(4) %></del></label><br>
						<label style="color:#4CAF50;">Νέο Πάγιο:</label>
						<label style="text-align:center;color:#475a57;margin-left:5em;"><%=newC.substring(0,4) %></label>
						<br><br><br>
					</div>
			</div>
			<% } %>
	</div>
	
	<div class="column">
		<div style="float:right;text-align:center;width:400px;">
				<p>Για να επιλέξετε/αλλάξετε κάποιο πρόγραμμα πρέπει<br>
				πρώτα να συνδεθείτε με τον λογαριασμό σας.</p><br><br>
				<form method="post" action="${pageContext.request.contextPath}/register.jsp">
					<br><br><button type="submit" class="MoreButton">Δημιουργία Λογαριασμού</button>
				</form>
				<br><br>
				<form method="post" action="Return_To_Homepage">
					<button type="submit" class="MoreButton">Επιστροφή</button>
				</form>
			</div>
	</div>

</div>
	
<script>
var coll = document.getElementsByClassName("collapsible");
var i;

for (i = 0; i < coll.length; i++) {
  coll[i].addEventListener("click", function() {
    this.classList.toggle("active");
    var content = this.nextElementSibling;
    if (content.style.maxHeight){
      content.style.maxHeight = null;
    } else {
      content.style.maxHeight = content.scrollHeight + "px";
    } 
  });
}

jQuery(document).ready(function () {
	var x = document.getElementById("prog").value;
	if ( x != "" ) document.getElementById(x).click();
});
</script>
</body>
</html>