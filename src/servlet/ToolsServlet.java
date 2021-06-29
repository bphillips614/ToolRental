package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.ToolRentalService;

public class ToolsServlet extends HttpServlet {

	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("in the ToolsServlet");
		
		String code = request.getParameter("code");
		Long checkoutDate = Long.parseLong(request.getParameter("checkoutDate"));
		int rentalDays = Integer.parseInt(request.getParameter("rentalDays"));
		double discount = Double.parseDouble(request.getParameter("discount"));
		
		System.out.println("codeParam = " + code);
		System.out.println("checkoutDateParam = " + checkoutDate);
		System.out.println("rentalDaysParam = " + rentalDays);
		System.out.println("discountParam = " + discount);
		
		ToolRentalService trs = new ToolRentalService();
		String rentalAgreement = trs.generateRentalAgreement(code, checkoutDate, rentalDays, discount);
		
		System.out.println("rentalAgreement = " + rentalAgreement);
		
		PrintWriter out = response.getWriter();
		out.write(rentalAgreement);
		out.close();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
}
