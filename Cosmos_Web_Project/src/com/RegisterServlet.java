package com;

import java.io.IOException;

import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import People.Admin;
import People.Client;
import People.Seller;
import People.Users;

import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;



/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
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
	
	
    public RegisterServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException {
		String requestType= request.getParameter("requestType");
		
		if (requestType.equalsIgnoreCase("insert")) {
			String username = request.getParameter("username");
			String password = request.getParameter("password1");
			String email = request.getParameter("email");
			String number = request.getParameter("number");
			String fname = request.getParameter("fname");
			String lname = request.getParameter("lname");
			
			String type = "";
			if(request.getParameter("type").equalsIgnoreCase("client")) type = "client";
			else if (request.getParameter("type").equalsIgnoreCase("seller")) type = "sellers";
			else type = "admins";
			
			String afm = request.getParameter("afm");
			String SPcode = request.getParameter("SPcode");
			
			try {
				
				Connection con = datasource.getConnection();
			    Statement stmt = con.createStatement();
			    
			    String error = Users.Register(afm, username, type, email, number, SPcode, stmt);
			    
			    if(!error.equals("")) 
			    {
			    	stmt.close();				
					con.close();
					request.setAttribute("reg_error", error);
					request.getRequestDispatcher("/register.jsp").forward(request, response);
			    }
			    
			    request.setAttribute("usrnm", username);
			    request.setAttribute("pswrd", password);
			    
			    if(type.equals("client")) 
			    {
			    	Client client = new Client(afm , username , fname , lname , 
			        		"client" , Long.parseLong(number),
			        		email , password);
			    	client.Register(stmt , request);
			    	client.set_profile_sv(request);
					client.program.set_Program_sv(stmt, request);
					client.program.set_Remaining_Program_SV(request);
					client.bill.set_bill_sv(request,"");//"" null past-no quick access button was pressed
			    	request.getSession().setAttribute("Client", client);
			    	request.getRequestDispatcher("/People/Client.jsp").forward(request, response);
			    }
			    else if(type.equals("sellers")) 
			    {
			    	Seller seller = new Seller(username , fname , lname , "Seller" , SPcode , email , password);
			    	seller.Register(stmt , request);
			    	seller.set_profile_values(request);
			    	request.getSession().setAttribute("Seller", seller);
			    	request.getSession().setAttribute("Type","seller");
			    	request.getRequestDispatcher("/People/SellerPage.jsp").forward(request, response);
			    }
			    else 
			    {
			    	Admin admin = new Admin(username , fname , lname , "Admin" , SPcode , email , password);
			    	admin.Register(stmt , request);
			    	request.getSession().setAttribute("Admin", admin);
			    	request.getSession().setAttribute("Type","admin");			 
			    	request.getRequestDispatcher("/People/AdminPage.jsp").forward(request, response);
			    }
			    
				stmt.close();				
				con.close();
			}catch(SQLException sqle){
				sqle.printStackTrace();	
				request.setAttribute("reg_error", sqle.toString());
				request.getRequestDispatcher("/register.jsp").forward(request, response);
			}
		}
	}
}
