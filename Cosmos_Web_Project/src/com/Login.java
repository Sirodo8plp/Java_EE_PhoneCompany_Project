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

import People.*;

/**
 * Servlet implementation class profile
 */

@WebServlet("/Login")
public class Login extends HttpServlet {
	
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
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	@SuppressWarnings("resource")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			Connection con = datasource.getConnection();
			Statement stmt = con.createStatement();
			
			String username = request.getParameter("usrnm");
			String password = request.getParameter("pswrd");
			StringBuilder user_type = new StringBuilder();
			
			//for quick access buttons in homepage
			String past = request.getParameter("quick_access");
			request.getSession().setAttribute("quick_access", past);
			
			String proceed = Users.login(username, password, stmt , user_type);
			
			if(proceed.equals("")) 
			{
				switch(user_type.toString()) 
				{
					case("client"):
						
						Client client = new Client(username , stmt);
						client.set_profile_sv(request);
						client.program.set_Program_sv(stmt, request);
						client.bill.setPrevious_debt(client.getDebt());
						client.bill.set_bill_sv(request , past);
						client.program.set_Remaining_Program_SV(request);
						request.getSession().setAttribute("Client", client); // add to session
						request.getRequestDispatcher("/People/Client.jsp").forward(request, response);
						break;
					case("sellers"):
						
						Seller seller = new Seller(username , stmt);
						seller.set_profile_values(request);
						request.getSession().setAttribute("Seller", seller); // add to session
						request.getRequestDispatcher("/People/SellerPage.jsp").forward(request, response);
						break;
						
					case("admins"):
						
						Admin admin = new Admin(username , stmt);
						admin.set_profile_values(request);
						request.getSession().setAttribute("Admin", admin); // add to session
						break;
				}
			}
			else 
				{
					request.setAttribute("error_message", proceed);
					request.getRequestDispatcher("/welcomePage.jsp").forward(request, response);
				}
			con.close();
		} catch(Exception e) {
			request.setAttribute("error_message", "exc");
			e.printStackTrace();
			request.getRequestDispatcher("/welcomePage.jsp").forward(request, response);
		}
	}
}

