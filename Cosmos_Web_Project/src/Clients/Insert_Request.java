package Clients;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import People.Client;

/**
 * Servlet implementation class Insert_Request
 */
@WebServlet("/Insert_Request")
public class Insert_Request extends HttpServlet {
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
	
    public Insert_Request() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		try {
			Connection con = datasource.getConnection();
			Statement stmt = con.createStatement();
			Client client = (Client)request.getSession().getAttribute("Client");
			
			client.Add_Request((String)request.getParameter("problem"), stmt, request);
			client.requests = new ArrayList<>();
			client.responses = new ArrayList<>();
			client.Collect_Responses_Requests(stmt);
			request.getSession().setAttribute("Client", client); // add to session
			request.getSession().setAttribute("quick_access", "");
			request.getRequestDispatcher("/People/Client.jsp").forward(request, response);
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
