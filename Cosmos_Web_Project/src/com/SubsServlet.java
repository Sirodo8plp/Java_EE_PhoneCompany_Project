package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;


import javax.sql.DataSource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.naming.InitialContext;

/**
 * Servlet implementation class SubsServlet
 */
@WebServlet("/SubsServlet")
public class SubsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	  
	private DataSource datasource = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
	
	
	public void init() throws ServletException{
		try {
	
			InitialContext ctx = new InitialContext();
			datasource = (DataSource)ctx.lookup("java:comp/env/jdbc/LiveDataSource");
		} catch(Exception e) {
			throw new ServletException(e.toString());
		}

	}
	
	
	
    public SubsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());		
		
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		
		out.println("<head><meta charset=utf-8> <title>Cosmos Προγράμματα</title><link rel=\"stylesheet\" href=\"cssFolder/subs.css\"></head>");
	
		
		out.println("<body style = \"background-color:#F7F9F9\"  ; >");
		out.println("<h2 style = text-align:center;color:#475a57>'Ολα τα διαθέσιμα προγράμματα μας: </h2>");

		try {
		Connection con = datasource.getConnection();
		Statement stmt = con.createStatement();
		
		out.println("<table class=\"tg\"> ");
		out.println("<thead>");
		out.println("<tr>");
		out.println("<th class=\"tg-0pky\">Πρόγραμμα</th>");
		out.println("<th class=\"tg-0pky\">Λεπτά Ομιλίας</th>");
		out.println("<th class=\"tg-0pky\">SMS</th>");
		out.println("<th class=\"tg-0pky\">MB</th>");
		out.println("<th class=\"tg-0pky\">Κόστος Λεπτών Ομιλίας</th>");
		out.println("<th class=\"tg-0pky\">Κόστος SMS</th>");
		out.println("<th class=\"tg-0pky\">Κόστος ΜΒ</th>");
		out.println("<th class=\"tg-0pky\">Πάγιο</th>");
		out.println("<th class=\"tg-0pky\" style=\"text-align:center\">Ενέργεια</th>");
		out.println("</tr>");
		
		ResultSet rs = stmt.executeQuery("SELECT * FROM subs");
		
		while(rs.next()) {
			String subname= rs.getString("subname");
			int minutes = rs.getInt("minutes");
			int mb = rs.getInt("mb") / 1000;
			int sms = rs.getInt("sms");
			double costmin = rs.getDouble("costmin");
			double costmb = rs.getDouble("costmb");
			double costsms = rs.getDouble("costsms");
			double charge = rs.getDouble("charge");
			
			String htmlRow = createHTMLRow(subname,minutes,mb,sms,costmin,costmb,costsms,charge);
			out.println(htmlRow);

		}
		rs.close();
		
		con.close();
		}catch(Exception e) {
			out.println("Database connection problem");
		}
		
		out.println("</tbody></table");
		
		out.println("<br><br><div class=\"container1\"><button type=\"submit\" class=\"MoreButton\" style=\"font-size:25px;\" onclick=\"NewSub()\">Προσθήκη Προγράμματος</button>");
		out.println("<br><br><ion-icon name=\"arrow-back-circle-sharp\"></ion-icon><p style = \"font-size:20px;color:#475a57;\"><a href=\"Return_To_Seller\">Επιστροφή</a></p>");
		
		out.println("<table class=\"tg\" style=\"visibility:hidden\" id=\"nc\" >");
		out.println("<thead>");
		out.println("<tr>");
		out.println("<th class=\"tg-0pky\" style=\"text-align:center;\">Πρόγραμμα</th>");
		out.println("<th class=\"tg-0pky\" style=\"text-align:center;\">Λεπτά Ομιλίας</th>");
		out.println("<th class=\"tg-0pky\" style=\"text-align:center;\">SMS</th>");
		out.println("<th class=\"tg-0pky\" style=\"text-align:center;\">MB</th>");
		out.println("<th class=\"tg-0pky\" style=\"text-align:center;\">Κόστος Λεπτών Ομιλίας</th>");
		out.println("<th class=\"tg-0pky\" style=\"text-align:center;\">Κόστος SMS</th>");
		out.println("<th class=\"tg-0pky\" style=\"text-align:center;\">Κόστος ΜΒ</th>");
		out.println("<th class=\"tg-0pky\" style=\"text-align:center;\">Πάγιο</th>");
		out.println("</tr>");
		out.println("<tr>");
		for(int i = 0; i < 8; i++) out.println("<td class=\"tg-0pky\" style=\"text-align:center\" contenteditable=\"true\" name=\"insert"+Integer.toString(i)+"\"></td>");
		
		out.println("</tr>");
		out.println("</tbody></table>");
		
		out.println("<br><div class=\"container1\"><button type=\"submit\" class=\"MoreButton\" style=\"font-size:25px;visibility:hidden\" id=\"insrt\" onclick=\"CheckINS()\" >Εισαγωγή</button></div>");
		
		out.println("<script src=\"https://unpkg.com/ionicons@5.0.0/dist/ionicons.js\"></script>");
		
		
			out.println("<script>");
				out.println(" function NewSub() { ");
					out.println("var elem = document.getElementById(\"nc\");");
					out.println("if (elem.style.visibility == \"hidden\"){ elem.style.visibility = \"visible\"; document.getElementById(\"insrt\").style.visibility = \"visible\";}");
					out.println("else { elem.style.visibility = \"hidden\"; document.getElementById(\"insrt\").style.visibility = \"hidden\";}");
				out.println("}");
				
			out.println("</script>");
		
		
		out.println("</body>");
		out.println("</html>");
	}	
		
		private String createHTMLRow(String subname,int minutes,int mb,int sms,double costmin, double costmb, double costsms, double charge) {
			String row = "<tr>";
			row  += "<td  class=\"tg-0pky\">" + subname + "</td>";
			
			if(minutes==-1) row  += "<td  class=\"tg-0pky\">Απεριόριστα</td>";
			else row  += "<td  class=\"tg-0pky\">" + minutes + "</td>";
			
			row  += "<td  class=\"tg-0pky\">" + sms + "</td>";
			
			row  += "<td  class=\"tg-0pky\">" + mb + " GB</td>";
			
			row  += "<td  class=\"tg-0pky\">" + costmin + " €</td>";
			row  += "<td  class=\"tg-0pky\">" + costmb + " €</td>";
			row  += "<td  class=\"tg-0pky\">" + costsms + " €</td>";
			row  += "<td  class=\"tg-0pky\">" + charge + " €</td>";
			row  += "<td  class=\"tg-0pky\"><button type=\"submit\" class=\"MoreButton\">Αλλαγή </button> <button type=\"submit\" class=\"MoreButton1\">Διαγραφή</button></td>";
			row +="</tr>";
			return row;
		}
}

