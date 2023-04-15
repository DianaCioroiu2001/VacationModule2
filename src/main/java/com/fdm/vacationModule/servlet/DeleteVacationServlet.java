package com.fdm.vacationModule.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fdm.vacationModule.dao.entities.UserAccountEntity;
import com.fdm.vacationModule.dao.entities.VacationRequestEntity;
import com.fdm.vacationModule.service.UserAccountService;
import com.fdm.vacationModule.service.VacationRequestService;

/**
 * Servlet implementation class DeleteVacationServlet
 */
@WebServlet("/deleteVacationServlet")
public class DeleteVacationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteVacationServlet() {
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
		String startData = start_day + "-" + start_month + "-" + start_year;
		UserAccountService.getInstance().verifyCredentials(response, request, otp);
		int id = UserAccountService.getInstance().getUser(otp).getId();
		List<VacationRequestEntity> listVacationRequest = UserAccountService.getInstance().getVacationRequest(id);
		UserAccountEntity userAccountEntity = UserAccountService.getInstance().getUser(otp);
		List<LocalDate> listHolidays = VacationRequestService.getInstance().getNationalHolidays();
		Boolean verification = false;
		int index = 0;
		for(int i=0; i<listVacationRequest.size(); i++)
			if(listVacationRequest.get(i).getStartDate().equals(startData)) {
				verification = true;
				index++;
			}
		List<LocalDate> listVacationRequestDays = VacationRequestService.getInstance().getListVacationRequestDays(listVacationRequest.get(index));
		if(index>0) {
			int days = VacationRequestService.getInstance().getNumberDaysVacation(userAccountEntity, listVacationRequestDays.get(0), listVacationRequestDays.get(listVacationRequestDays.size()-1), listHolidays);
			userAccountEntity.setVacationDays(userAccountEntity.getVacationDays() + days);
			userAccountEntity = UserAccountService.getInstance().update(userAccountEntity); 
		}
			if(verification) {	
		try {
				VacationRequestService.getInstance().deleteVacationrequest(startData);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		RequestDispatcher rd = request.getRequestDispatcher("deletedRequest.html"); 
		rd.forward(request,response);
			}
			else {
				request.getRequestDispatcher("/invalidDateModify.html").forward(request, response);
			}
	}

}
