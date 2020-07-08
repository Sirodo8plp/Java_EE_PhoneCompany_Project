package Sellers;

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
import People.Users;

@WebServlet("/Seller_Ads_Client")
public class Seller_Ads_Client extends HttpServlet {
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
   
   	public Seller_Ads_Client() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String username = request.getParameter("username");
		String password = request.getParameter("password1");
		String email = request.getParameter("email");
		String number = request.getParameter("number");
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		String afm = request.getParameter("afm");
		
		try 
		{
			Connection con = datasource.getConnection();
		    Statement stmt = con.createStatement();
		    
		    String error = Users.Register(afm, username, "client", email, number, "", stmt);
		    
		    if(!error.equals("")) 
		    {
		    	stmt.close();				
				con.close();
				request.setAttribute("infooo", error);
				request.getRequestDispatcher("/People/SellerPage.jsp").forward(request, response);
		    }
		    
		    Client client = new Client(afm , username , fname , lname , 
	        		"client" , Long.parseLong(number),
	        		email , password);
	    	client.Register(stmt , request);
	    	request.getSession().setAttribute("infooo", "Επιτυχής καταχώρηση πελάτη.");
	    	request.getRequestDispatcher("/People/SellerPage.jsp").forward(request, response);
		}
		catch(Exception e) {e.printStackTrace();}
	}

}
