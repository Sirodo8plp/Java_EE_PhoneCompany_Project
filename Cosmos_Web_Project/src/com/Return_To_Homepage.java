package com;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Misc.Program;

@WebServlet("/Return_To_Homepage")
public class Return_To_Homepage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Return_To_Homepage() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		Program.All_Subs = new ArrayList<List<String>>();
		request.getRequestDispatcher("/welcomePage.jsp").forward(request, response);
	}

}
