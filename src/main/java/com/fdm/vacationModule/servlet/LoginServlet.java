package com.fdm.vacationModule.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fdm.vacationModule.service.UserAccountService;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String otp = request.getParameter("otp");
		if(UserAccountService.getInstance().verifyCredentials(email, password, otp)) {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/menu");
	    dispatcher.forward(request, response);
		} else {
			//PrintWriter out = response.getWriter();
			//out.println("Invalid!");
			RequestDispatcher rd = request.getRequestDispatcher("errorMessageLogIn.jsp");
			rd.forward(request, response);
		}
		 }
}
