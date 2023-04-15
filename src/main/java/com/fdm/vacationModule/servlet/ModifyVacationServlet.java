package com.fdm.vacationModule.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
 * Servlet implementation class ModifyVerificationServlet
 */
@WebServlet("/modifyVacationServlet")
public class ModifyVacationServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String otp = request.getParameter("otp");
		int start_month = Integer.parseInt(request.getParameter("start_month"));
		int start_day = Integer.parseInt(request.getParameter("start_day"));
		int start_year = Integer.parseInt(request.getParameter("start_year"));
		String startData = start_day + "-" + start_month + "-" + start_year;
		UserAccountService.getInstance().verifyCredentials(response, request, otp);
		int id = UserAccountService.getInstance().getUser(otp).getId();
		List<VacationRequestEntity> listVacationRequest = UserAccountService.getInstance().getVacationRequest(id);
		List<LocalDate> listHolidays = VacationRequestService.getInstance().getNationalHolidays();
		UserAccountEntity userAccountEntity = UserAccountService.getInstance().getUser(otp);
		int days = 0;
		int daysHolidays = 0;
		for(int i=0; i<listVacationRequest.size(); i++) {
			if(listVacationRequest.get(i).getStartDate().equals(startData)) {
				VacationRequestEntity vacationRequest = listVacationRequest.get(i);
				 List<LocalDate> listVacationRequestEntity =   VacationRequestService.getInstance().getListVacationRequestDays(vacationRequest);
				  days = VacationRequestService.getInstance().getNumberDaysVacation(userAccountEntity, listVacationRequestEntity.get(0), listVacationRequestEntity.get(listVacationRequestEntity.size()-1), listHolidays);
				 daysHolidays = VacationRequestService.getInstance().getNumberDaysHolidays(userAccountEntity, listVacationRequestEntity.get(0), listVacationRequestEntity.get(listVacationRequestEntity.size()-1), listHolidays);
			userAccountEntity.setVacationDays(userAccountEntity.getVacationDays() +  days);
			 userAccountEntity = UserAccountService.getInstance().update(userAccountEntity); 
			 try {
					VacationRequestService.getInstance().deleteVacationrequest(startData);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
		request.setAttribute("startData", listVacationRequestEntity.get(0));
		request.setAttribute("endData", listVacationRequestEntity.get(listVacationRequestEntity.size()-1));
		request.getRequestDispatcher("/modify.jsp").forward(request, response);
		}
			 else if(i==listVacationRequest.size()-1 && (days == 0 || daysHolidays ==  0)){
					request.getRequestDispatcher("/invalidDateModify.html").forward(request, response);
				}
			}
	}
}
	
			
	


