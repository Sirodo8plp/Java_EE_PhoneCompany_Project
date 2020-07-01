package com;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Return_To_Client")
public class Return_To_Client extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Return_To_Client() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		request.getSession().setAttribute("quick_access", "");
		request.getSession().setAttribute("infooo", "");
		request.getRequestDispatcher("/People/Client.jsp").forward(request, response);
	}

}
