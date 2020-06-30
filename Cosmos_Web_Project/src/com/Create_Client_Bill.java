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

@WebServlet("/Create_Client_Bill")
public class Create_Client_Bill extends HttpServlet {
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
    
	public Create_Client_Bill() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		try {
			Connection con = datasource.getConnection();
			Statement stmt = con.createStatement();
			Seller seller = (Seller)request.getSession().getAttribute("Seller");
			seller.Create_Payment(request.getParameter("client_phonenumber"), request.getParameter("bill_date"),
					request.getParameter("bill_price") , stmt , request);
			request.getSession().setAttribute("Seller", seller); // add to session
			request.getRequestDispatcher("/People/SellerPage.jsp").forward(request, response);
		}
		catch(Exception e) {e.printStackTrace();}
	}

}
