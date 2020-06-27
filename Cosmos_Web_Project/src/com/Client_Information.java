package com;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import People.Client;

@WebServlet("/Client_Information")
public class Client_Information extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Client_Information() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		Client client = (Client)request.getSession().getAttribute("Client");
		request.getSession().setAttribute("username", client.getUsername());
		request.getSession().setAttribute("email", client.getEmail());
		request.getSession().setAttribute("afm", client.getAfm());
		request.getSession().setAttribute("number", client.phonenumber.getPhoneNum());
		request.getSession().setAttribute("first_name", client.getName());
		request.getSession().setAttribute("surname", client.getSurname());
		request.getSession().setAttribute("subscription", client.program.getSubscriptionName());
		request.getSession().setAttribute("charge" , client.bill.getCurrent_bill());
		request.getSession().setAttribute("minutes", client.program.getMinutes());
		request.getSession().setAttribute("mb", client.program.getMb());
		request.getSession().setAttribute("sms", client.program.getSms());
		request.getSession().setAttribute("debt", client.bill.getPrevious_debt());
		request.getSession().setAttribute("bill", client.bill.getCharge());
		request.getSession().setAttribute("bill_date", client.bill.getBill_date());
		
		request.getRequestDispatcher("/client_information.jsp").forward(request, response);
	}
}
