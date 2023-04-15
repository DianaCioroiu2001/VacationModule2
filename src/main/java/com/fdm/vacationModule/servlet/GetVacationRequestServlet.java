package com.fdm.vacationModule.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fdm.vacationModule.dao.entities.UserAccountEntity;
import com.fdm.vacationModule.dao.entities.VacationRequestEntity;
import com.fdm.vacationModule.service.UserAccountService;
import com.fdm.vacationModule.service.VacationRequestService;

/**
 * Servlet implementation class GetVacationRequestServlet
 */
@WebServlet("/getList")
public class GetVacationRequestServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String otp = request.getParameter("otp");
		UserAccountService.getInstance().verifyCredentials(response, request, otp);
		int id = UserAccountService.getInstance().getUser(otp).getId();
		List<VacationRequestEntity> listVacationRequest = UserAccountService.getInstance().getVacationRequest(id);
		int size = listVacationRequest.size();
		int nrCrt = 1;
		 response.setContentType("text/html");
		    PrintWriter out = response.getWriter();
		    out.println("<html>");
		    out.println("<head>");
		    out.println("<title>Titlu colorat</title>");
		    out.println("<style>");
		    out.println("h1 { color: red; text-align: center; font-size: 50px; }");
		    out.println("</style>");
		    out.println("<style>");
		    out.println("table td { text-align: center; }");
		    out.println("</style>");
		    out.println("</head>");
		    out.println("<body>");
		    out.println("<table>");
		    out.println("<h1>You have " + size + " leave requests created</h1>");
		    for (VacationRequestEntity s : listVacationRequest) {
		    	 out.println("<tr><td><font size='10'>"+ nrCrt + ".  from date  " +s.getStartDate() + " to date   " + s.getEndDate() + "</font></td></tr><br>");
		    
		    nrCrt++;}
		    out.println("</table>");
		    out.println("</body>");
		    out.println("</html>");
		
	   }
}
