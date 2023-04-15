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
import com.fdm.vacationModule.dao.entities.UserAccountEntity;
import com.fdm.vacationModule.dao.entities.VacationRequestEntity;
import com.fdm.vacationModule.service.UserAccountService;
import com.fdm.vacationModule.service.VacationRequestService;
/**
* Servlet implementation class VacationRequestServlet
*/
@WebServlet("/createRequestServlet")
public class VacationRequestServlet extends HttpServlet {
private static final long serialVersionUID = 1L;
/**
* @see HttpServlet#HttpServlet()
*/
public VacationRequestServlet() {
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
UserAccountService.getInstance().verifyCredentials(response, request, otp);
List<LocalDate> list = VacationRequestService.getInstance().getNationalHolidays();
int id = UserAccountService.getInstance().getUser(otp).getId();
List<VacationRequestEntity> listVacationRequest = UserAccountService.getInstance().getVacationRequest(id);
UserAccountEntity userAccountEntity = UserAccountService.getInstance().getUser(otp);
Boolean message = VacationRequestService.getInstance().checkDate(start_date, end_date);
if(message) {
	System.out.println("sunt in message si message= " + message);
int days = VacationRequestService.getInstance().getNumberDaysVacation(userAccountEntity, start_date, end_date, list);
int daysHolidays = VacationRequestService.getInstance().getNumberDaysHolidays(userAccountEntity, start_date, end_date, list);
if(days>0) {
	System.out.println("sunt in days si days= " + days);
int remainingDays = VacationRequestService.getInstance().decreaseDays(userAccountEntity, days, daysHolidays);
if(remainingDays >= 0) {
VacationRequestService.getInstance().createRequest(listVacationRequest, remainingDays, userAccountEntity, startData, endData, request, response);
} else {
	RequestDispatcher rd = request.getRequestDispatcher("generateError.jsp");
	rd.forward(request,response); 
}
}
else if(daysHolidays > 0){
	System.out.println("sunt in daysHolidays si daysHolidays= " + daysHolidays);
VacationRequestService.getInstance().createRequest(listVacationRequest, userAccountEntity.getVacationDays(), userAccountEntity, startData, endData, request, response);
} else {
RequestDispatcher rd = request.getRequestDispatcher("generateError.jsp");
rd.forward(request,response); }
}
else {
RequestDispatcher rd = request.getRequestDispatcher("invalidDate.jsp"); 
rd.forward(request,response);
}
}
}

