package com;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import Misc.Program;
import People.Seller;

@WebServlet("/Seller_Delete_Program")
public class Seller_Delete_Program extends HttpServlet {
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
       
    public Seller_Delete_Program() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		try {
			Connection con = datasource.getConnection();
			Statement stmt = con.createStatement();
			Seller seller = (Seller)request.getSession().getAttribute("Seller");
			seller.Delete_Program(stmt, request, request.getParameter("subname"));
			Program.All_Subs = new ArrayList<List<String>>();
			Program.Gather_Subs(stmt);
			con.close();
			stmt.close();
			request.getRequestDispatcher("/Seller_Subscriptions.jsp").forward(request, response);
		}
		catch(Exception e){ e.printStackTrace();}
	}

}
