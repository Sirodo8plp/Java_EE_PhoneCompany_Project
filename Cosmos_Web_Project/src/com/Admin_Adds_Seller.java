package com;

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

import People.Seller;

/**
 * Servlet implementation class Admin_Adds_Seller
 */
@WebServlet("/Admin_Adds_Seller")
public class Admin_Adds_Seller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	private DataSource datasource = null;	
	
 	public void init() throws ServletException{
		try {
	
			InitialContext ctx = new InitialContext();
			datasource = (DataSource)ctx.lookup("java:comp/env/jdbc/LiveDataSource");
		} catch(Exception e) {
			throw new ServletException(e.toString());
		}
	}
	
	
	
    public Admin_Adds_Seller() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String username = request.getParameter("username");
		String password = request.getParameter("password1");
		String email = request.getParameter("email");
		String scode = request.getParameter("seller_code");
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		
		try {
			
			Connection con = datasource.getConnection();
		    Statement stmt = con.createStatement();
		    
		  //  String error = Users.Register("", username, "seller", email, "", scode, stmt);
		    
		    //if(!error.equals("")) 
		    //{
		    	//stmt.close();				
				//con.close();
				//request.setAttribute("infooo", error);
				//request.getRequestDispatcher("/People/AdminPage.jsp").forward(request, response);
				

			    Seller seller = new Seller(username , fname , lname , 
		        		"seller" ,scode, email , password);
		    	seller.Register(stmt , request);
		    	request.getSession().setAttribute("infooo", "Επιτυχής προσθήκη πωλητή.");
		    	request.getRequestDispatcher("/People/AdminPage.jsp").forward(request, response);
				
				
		} catch(Exception e) {e.printStackTrace();}
		
		
		
	}

}
