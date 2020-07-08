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
 * Servlet implementation class Delete_Seller
 */
@WebServlet("/Delete_Seller")
public class Delete_Seller extends HttpServlet {
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
	
	
	
    public Delete_Seller() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String username = request.getParameter("username");
		String acode = request.getParameter("admin_code");
		
		try {
			
			Connection con = datasource.getConnection();
			Statement stmt = con.createStatement();
			
			Admin admin = new Admin("","","","admin",acode,"","");
			admin.Delete_Seller(stmt , request,username,acode);
			request.getRequestDispatcher("/People/AdminPage.jsp").forward(request, response);
			
			
			
			
		}catch(Exception e) {e.printStackTrace();}
		
		
		
		
		
	}

}
