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

/**
 * Servlet implementation class getAvailableVacation
 */
@WebServlet("/getAvailableVacation")
public class getAvailableVacation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getAvailableVacation() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String otp = request.getParameter("otp");
		UserAccountEntity user = UserAccountService.getInstance().getUser(otp);
		int days = user.getVacationDays();
		PrintWriter out = response.getWriter();
		out.print("you have " +  days +" more days of vacation");
	}

}
