package com.fdm.vacationModule.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fdm.vacationModule.dao.entities.UserAccountEntity;
import com.fdm.vacationModule.service.UserAccountService;

@WebServlet("/userAccount")
public class UserAccountServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String cnp = request.getParameter("cnp");
		String nume = request.getParameter("nume");		
		if(!UserAccountService.getInstance().verifyEmail(email)) {
			String message = "The email address entered is not in the correct form. This must end with @gmail.com or @yahoo.com"; 
			request.setAttribute("message", message);
			RequestDispatcher rd = request.getRequestDispatcher("errorMessageSignUp.jsp");
		rd.forward(request, response);
		}
		else if(!UserAccountService.getInstance().verifyCnp(cnp)) {

			  String message = "The entered cnp does not have a correct form. It must contain 13 digits"; 
				request.setAttribute("message", message);
				RequestDispatcher rd = request.getRequestDispatcher("errorMessageSignUp.jsp");
				rd.forward(request, response);
		}
		else if(!UserAccountService.getInstance().verifyPassword(password)) {
	
			  String message = "The password must contain at least one number and an uppercase letter"; 
				request.setAttribute("message", message);
				RequestDispatcher rd = request.getRequestDispatcher("errorMessageSignUp.jsp" );
				rd.forward(request, response);
		}
		
		else if(UserAccountService.getInstance().verifyEmail(email) && UserAccountService.getInstance().verifyCnp(cnp) && UserAccountService.getInstance().verifyPassword(password) ) {
		UserAccountEntity userAccountEntity = new UserAccountEntity(email, password, cnp, nume);	
		UserAccountEntity savedUserAccountEntity = UserAccountService.getInstance().save(userAccountEntity);
		UserAccountService.getInstance().generateOTP(email);
		RequestDispatcher rd = request.getRequestDispatcher("accountCreated.html");
		rd.forward(request, response);
		}
	}
}
