package Admins;

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

@WebServlet("/Delete_Client")
public class Delete_Client extends HttpServlet {
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
	
	
	
	
    public Delete_Client() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		
		String clientid = request.getParameter("client_id");
		String acode = request.getParameter("admin_code");
		
		try {
			
			Connection con = datasource.getConnection();
		    Statement stmt = con.createStatement();
		    
		    Admin admin = new Admin("","","","admin",acode,"","");
			admin.Delete_Customer(stmt , request,clientid);
	    	request.getRequestDispatcher("/People/AdminPage.jsp").forward(request, response);
			
			
		}catch(Exception e) {e.printStackTrace();}
		
		
		
		
	}

}
