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

import People.Admin;

/**
 * Servlet implementation class Update_Admin_Account
 */
@WebServlet("/Update_Admin_Account")
public class Update_Admin_Account extends HttpServlet {
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
	
	
    public Update_Admin_Account() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	
		try {
			Connection con = datasource.getConnection();
			Statement stmt = con.createStatement();
			
			Admin admin = (Admin)request.getSession().getAttribute("Admin");
			admin.Update_Account(request.getParameter("newUsername"), request.getParameter("newEmail"), 
					request.getParameter("newFname"), request.getParameter("newLname"), stmt, request);
			request.getSession().setAttribute("Seller", admin); // add to session
			request.getRequestDispatcher("/People/AdminPage.jsp").forward(request, response);
		}
		catch(Exception e) {e.printStackTrace();}
			
		
	}

}
