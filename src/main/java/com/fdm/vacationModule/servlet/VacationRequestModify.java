package com.fdm.vacationModule.servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.RequestDispatcher;
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
 * Servlet implementation class VacationRequestModify
 */
@WebServlet("/vacationRequestModify")
public class VacationRequestModify extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VacationRequestModify() {
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
			int start_month = Integer.parseInt(request.getParameter("start_month"));
			int start_day = Integer.parseInt(request.getParameter("start_day"));
			int start_year = Integer.parseInt(request.getParameter("start_year"));
			int end_month = Integer.parseInt(request.getParameter("end_month"));
			int end_day = Integer.parseInt(request.getParameter("end_day"));
			int end_year = Integer.parseInt(request.getParameter("end_year"));
			String startData = start_day + "-" + start_month + "-" + start_year;
			String endData = end_day + "-" + end_month + "-" + end_year;
			LocalDate start_date = LocalDate.of(start_year, start_month, start_day);
	        LocalDate end_date = LocalDate.of(end_year, end_month, end_day);
	        if(UserAccountService.getInstance().verifyCredentials(otp)) {
				HttpSession session = request.getSession();
				session.setAttribute("otp", otp);
			} else {
				request.setAttribute("message", "Invalid user credentials");
				request.getRequestDispatcher("/error.html").forward(request, response);
			}	
	        List<LocalDate> list = VacationRequestService.getInstance().getNationalHolidays();
			
	        

			Boolean message = VacationRequestService.getInstance().checkDate(start_date, end_date);
			UserAccountEntity userAccountEntity = UserAccountService.getInstance().getUser(otp);
			if(!message) {
				System.out.println("sunt in if message si message= " + message);
				  RequestDispatcher rd = request.getRequestDispatcher("/invalidDate"); 
				  rd.forward(request,response);
			}
			else {
			int days = VacationRequestService.getInstance().getNumberDaysVacation(userAccountEntity, start_date, end_date, list);
			if(days>=0){
				System.out.println("sunt in if days si days= " + days);
			       VacationRequestEntity vacationRequestEntity = new VacationRequestEntity(startData, endData, userAccountEntity);
					  VacationRequestService vacationRequestService = VacationRequestService.getInstance();
					 VacationRequestEntity upVacationRequestEntity = vacationRequestService.update(vacationRequestEntity, otp);
					 userAccountEntity.setVacationDays(days);
					userAccountEntity = UserAccountService.getInstance().update(userAccountEntity);
					RequestDispatcher rd = request.getRequestDispatcher("decreaseDays?message="+message + "&otp="+otp); 
					  rd.forward(request,response);
			}
			else {
						  RequestDispatcher rd = request.getRequestDispatcher("/generateError"); 
						  rd.forward(request,response);
			}
			}
		}
	}
	
			




