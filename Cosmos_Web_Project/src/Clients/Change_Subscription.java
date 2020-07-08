package Clients;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import People.Client;

@WebServlet("/Change_Subscription")
public class Change_Subscription extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private DataSource datasource = null;

	public void init() throws ServletException{
		try {
	
			InitialContext ctx = new InitialContext();
			datasource = (DataSource)ctx.lookup("java:comp/env/jdbc/LiveDataSource");
		} catch(Exception e) {
			throw new ServletException(e.toString());
		}

	}
    public Change_Subscription() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		try {
			Connection con = datasource.getConnection();
			Statement stmt = con.createStatement();
			
			String selected_sub = (String)request.getParameter("selected_sub");
			double bill = Double.parseDouble((String)request.getParameter("billl"));
			double debt = Double.parseDouble((String)request.getParameter("billl1"));
			
			Client client = (Client)request.getSession().getAttribute("Client");
			if(selected_sub.equals(client.program.getSubscriptionName())) {
				request.getSession().setAttribute("infooo", "Είστε ήδη εγγεγραμένοι σε αυτό<br>το πρόγραμμα.");
			}
			else if( Double.compare(bill, 0.0)>0 && Double.compare(debt,0.0)>0) {
				request.getSession().setAttribute("infooo", "Πρέπει να ξεχρεώσετε τις προηγούμενες οφειλές<br> σας"
						+ "πριν αλλάξετε πρόγραμμα.");
			}
			else {
				client.program.change_subscription(stmt, request, selected_sub , client.phonenumber.getPhoneNum());
				client.bill.setCharge(client.program.getCharge());
				client.bill.setCurrent_bill(client.program.getCharge());
				client.bill.setPrevious_debt(0);
			}
			request.getSession().setAttribute("quick_access", "");
			request.getRequestDispatcher("/People/Client.jsp").forward(request, response);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
