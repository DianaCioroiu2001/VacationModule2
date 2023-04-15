package com.fdm.vacationModule.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fdm.vacationModule.service.UserAccountService;

/**
 * Servlet implementation class OTPServlet
 */
@WebServlet("/OTP/generate")
public class OTPServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String OTP = UserAccountService.getInstance().generateOTP(email);
		RequestDispatcher rd = request.getRequestDispatcher("/contCreat");
		rd.forward(request, response);
	}
}
