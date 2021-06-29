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
		String code = request.getParameter("code");
		Long checkoutDate = Long.parseLong(request.getParameter("checkoutDate"));
		int rentalDays = Integer.parseInt(request.getParameter("rentalDays"));
		double discount = Double.parseDouble(request.getParameter("discount"));
		
		ToolRentalService trs = new ToolRentalService();
		String rentalAgreement = trs.generateRentalAgreement(code, checkoutDate, rentalDays, discount);

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
