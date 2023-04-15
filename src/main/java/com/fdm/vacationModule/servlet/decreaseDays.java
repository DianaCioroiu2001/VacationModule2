package com.fdm.vacationModule.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fdm.vacationModule.dao.entities.UserAccountEntity;
import com.fdm.vacationModule.service.UserAccountService;
import com.fdm.vacationModule.service.VacationRequestService;

/**
 * Servlet implementation class decreaseDays
 */
@WebServlet("/decreaseDays")
public class decreaseDays extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public decreaseDays() {
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
		
	String message = request.getParameter("message");
		String otp = request.getParameter("otp");
		
		UserAccountEntity user = UserAccountService.getInstance().getUser(otp);
		PrintWriter out = response.getWriter();
	
	
		out.println( "your request has been created, you still have " + user.getVacationDays() +" days of leave available");
	
		out.println("you don't have enough vacation days");
		
	
		
		 

	}
}

