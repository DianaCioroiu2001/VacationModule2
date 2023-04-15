package com.fdm.vacationModule.servlet;

import java.io.IOException;
import java.io.PrintWriter;
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
 * Servlet implementation class availbaleDays
 */
@WebServlet("/availableDays")
public class AvailbaleDays extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AvailbaleDays() {
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
		int year = Integer.parseInt(request.getParameter("year"));
		UserAccountEntity userAccountEntity = UserAccountService.getInstance().getUser(otp);
		List<LocalDate> listHolidays = VacationRequestService.getInstance().getNationalHolidays();
		LocalDate dateStart = LocalDate.of(year, 1, 1);
		LocalDate dateEnd = LocalDate.of(year, 12, 31);
		if(UserAccountService.getInstance().verifyCredentials(otp)) {
			HttpSession session = request.getSession();
			session.setAttribute("otp", otp);
		} else {
			request.setAttribute("message", "Invalid user credentials");
			request.getRequestDispatcher("/error.html").forward(request, response);
		}	
		int id = userAccountEntity.getId();
		List<VacationRequestEntity> listVacationRequest = UserAccountService.getInstance().getVacationRequest(id);
		System.out.println(dateStart + " " + dateEnd);
		int number = VacationRequestService.getInstance().getNumberOfVacationDays(listVacationRequest, dateStart, dateEnd, listHolidays);
		int days = 25 - number;
		request.setAttribute("days", days);
		RequestDispatcher rd = request.getRequestDispatcher("getAvailableDays.jsp" );
		rd.forward(request, response);
	//	PrintWriter out = response.getWriter();
		//out.write("You still have " + (25-number) +" days of vacation in the year");
	}

}
