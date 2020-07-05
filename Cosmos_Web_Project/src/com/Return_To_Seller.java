package com;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Return_To_Seller")
public class Return_To_Seller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Return_To_Seller() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		request.getRequestDispatcher("/People/SellerPage.jsp").forward(request, response);
	}
}
