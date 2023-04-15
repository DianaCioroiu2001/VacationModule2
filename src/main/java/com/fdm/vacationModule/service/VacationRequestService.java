package com.fdm.vacationModule.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fdm.vacationModule.dao.UserAccountDao;
import com.fdm.vacationModule.dao.UserAccountDaoImpl;
import com.fdm.vacationModule.dao.VacationRequestDao;
import com.fdm.vacationModule.dao.VacationRequestDaoImpl;
import com.fdm.vacationModule.dao.entities.UserAccountEntity;
import com.fdm.vacationModule.dao.entities.VacationRequestEntity;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class VacationRequestService {
	private static VacationRequestService instance;
	private VacationRequestDao vacationRequestDao;
	public List<LocalDate> nationalHolidays;
	
public List<LocalDate> getNationalHolidays(){
	List<LocalDate> list = new ArrayList<>();
	LocalDate anulNou1 = LocalDate.of(2023, 01, 01);
	list.add(anulNou1);
	LocalDate anulNou2 = LocalDate.of(2023, 01, 02);
	list.add(anulNou2);
	LocalDate ziuaUniriiPrincipatelorRomane = LocalDate.of(2023, 01, 24);
	list.add(ziuaUniriiPrincipatelorRomane);
	LocalDate vinereaMare = LocalDate.of(2023, 04, 14);
	list.add(vinereaMare);
	LocalDate paste1 = LocalDate.of(2023, 04, 16);
	list.add(paste1);
	LocalDate paste2 = LocalDate.of(2023, 04, 17);
	list.add(paste2);
	LocalDate ziuaMuncii = LocalDate.of(2023, 05, 01);
	list.add(ziuaMuncii);
	LocalDate ziuaCopilului = LocalDate.of(2023, 06, 01);
	list.add(ziuaCopilului);
	LocalDate rusalii1 = LocalDate.of(2023, 06, 04);
	list.add(rusalii1);
	LocalDate rusalii2 = LocalDate.of(2023, 06, 05);
	list.add(rusalii2);
	LocalDate adormireaMaiciiDomnului = LocalDate.of(2023, 8, 15);
	list.add(adormireaMaiciiDomnului);
	LocalDate sfAndrei = LocalDate.of(2023, 11, 30);
	list.add(sfAndrei);
	LocalDate ziuaNationalaARomaniei = LocalDate.of(2023, 12, 01);
	list.add(ziuaNationalaARomaniei);
	LocalDate craciun1 = LocalDate.of(2023, 12, 25);
	list.add(craciun1);
	LocalDate craciun2 = LocalDate.of(2023, 12, 26);
	list.add(craciun2);
	return list;
	
}
	private VacationRequestService() {
		this.vacationRequestDao =  new VacationRequestDaoImpl();
	}

	public static VacationRequestService getInstance() {
		if(instance == null) {
			instance = new VacationRequestService();
		}
		return instance;
	}

	public VacationRequestEntity save(VacationRequestEntity vacationRequestEntity) {
	
	return vacationRequestDao.save(vacationRequestEntity);
	
	}
	public VacationRequestEntity update(VacationRequestEntity vacationRequestEntity, String otp) {
		
		return vacationRequestDao.update(vacationRequestEntity, otp);
		
		}
	
	public VacationRequestEntity getVacationRequest(int id) {
		return vacationRequestDao.findById(id);
	}
	
	
	public Boolean checkDate(LocalDate start_date, LocalDate end_date) {
		Boolean message = true;
		if(!start_date.isBefore(end_date) && !start_date.isEqual(end_date))
			message = false;
		return message;
	}
	
	
	public int getDaysModify(UserAccountEntity userAccountEntity, LocalDate start_date, LocalDate end_date, List<LocalDate> list) {
		int days = -2;
			 
			   days = UserAccountService.getInstance().decreaseDaysModify(userAccountEntity, start_date, end_date, list);
	
		  
		 return days;
	}
	
	
	public void displayVacationRequest(HttpServletResponse response, int id) throws IOException {
List<VacationRequestEntity> listVacationRequest = UserAccountService.getInstance().getVacationRequest(id);
		
	    PrintWriter out = response.getWriter();
	  out.println("You have registered " + listVacationRequest.size() + " requests");
	    for(int i=0;i<listVacationRequest.size() ;i++) {
	    	
	    	out.println(" The request " + (i+1) + " is registered from date: ");
	   out.print(listVacationRequest.get(i).getStartDate().toString());
	   out.print(" to date ");
	   out.println(listVacationRequest.get(i).getEndDate().toString());
	}
	
}
	public void deleteVacationrequest(String start_date) throws SQLException  {
	    Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/vacationmodule2", "diana", "Developer@@2001");

	    String sql = "DELETE FROM vacationrequest WHERE start_date = ?";
	    System.out.println("a ajuns la delete");
	    PreparedStatement statement = (PreparedStatement) conn.prepareStatement(sql);
	    statement.setString(1, start_date);
	    statement.executeUpdate();
	    statement.close();
	    conn.close();
	}
	
	public void insertVacationrequest(String start_date, String end_date, int user_id) throws SQLException  {
	    Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/vacationmodule2", "diana", "Developer@@2001");

	    String sql = "INSERT INTO vacationrequest (start_date, end_date, user_id) VALUES (?,?,?)";
        PreparedStatement statement = (PreparedStatement) conn.prepareStatement(sql);
        statement.setString(1, start_date);
        statement.setString(2, end_date);
        statement.setInt(3, user_id);
     
	    statement.executeUpdate();
	    statement.close();
	    conn.close();
	}
	
	public boolean verificationDate(List<LocalDate> listDaysVacationCreated, List<LocalDate> listDays) {
		for(int i=0; i<listDaysVacationCreated.size(); i++)
			for(int j=0; j<listDays.size(); j++)
			if(listDaysVacationCreated.get(i) == listDaysVacationCreated.get(j))
				return false;
		return true;
	}
	
	public List<LocalDate> getDaysVacationCreated(List<VacationRequestEntity> listVacationRequest){
		List<LocalDate> listDaysVacation = new ArrayList<>();
		for(int i=0; i<listVacationRequest.size();i++) {
			String[] data_start =  listVacationRequest.get(i).getStartDate().split("-");
			int start_day = Integer.parseInt(data_start[0]);
			int start_month = Integer.parseInt(data_start[1]);
			int start_year = Integer.parseInt(data_start[2]);
		String[] data_end = listVacationRequest.get(i).getEndDate().split("-");
			int end_day = Integer.parseInt(data_end[0]);
			int end_month = Integer.parseInt(data_end[1]);
			int end_year = Integer.parseInt(data_end[2]);
			LocalDate start_date = LocalDate.of(start_year, start_month, start_day);
			LocalDate end_date = LocalDate.of(end_year, end_month, end_day);
			while(start_date.isBefore(end_date) || start_date.isEqual(end_date)) {
				listDaysVacation.add(start_date);
				start_date.plusDays(1);
			}
		}
			return listDaysVacation;
	}
	
	public List<LocalDate> getDaysVacationToCreate(String startDate, String endDate){
		List<LocalDate> listDaysVacation = new ArrayList<>();
		String[] data_start = startDate.split("-");
		int start_day = Integer.parseInt(data_start[0]);
		int start_month = Integer.parseInt(data_start[1]);
		int start_year = Integer.parseInt(data_start[2]);
	String[] data_end = endDate.split("-");
		int end_day = Integer.parseInt(data_end[0]);
		int end_month = Integer.parseInt(data_end[1]);
		int end_year = Integer.parseInt(data_end[2]);
		LocalDate start_date = LocalDate.of(start_year, start_month, start_day);
		LocalDate end_date = LocalDate.of(end_year, end_month, end_day);
		while(start_date.isBefore(end_date) || start_date.isEqual(end_date)) {
			listDaysVacation.add(start_date);
			start_date.plusDays(1);
		}
	
		return listDaysVacation;
	}
	public int getNumberOfVacationDays(List<VacationRequestEntity> listVacationRequest, LocalDate dateStart, LocalDate dateEnd, List<LocalDate> listHolidays) {
	int number = 0;
		for(int i=0; i<listVacationRequest.size(); i++) {
			String[] data_start =  listVacationRequest.get(i).getStartDate().split("-");
				int start_day = Integer.parseInt(data_start[0]);
				int start_month = Integer.parseInt(data_start[1]);
				int start_year = Integer.parseInt(data_start[2]);
			String[] data_end = listVacationRequest.get(i).getEndDate().split("-");
				int end_day = Integer.parseInt(data_end[0]);
				int end_month = Integer.parseInt(data_end[1]);
				int end_year = Integer.parseInt(data_end[2]);
				LocalDate start_date = LocalDate.of(start_year, start_month, start_day);
				LocalDate end_date = LocalDate.of(end_year, end_month, end_day);
				if((start_date.isAfter(dateStart) || start_date.isEqual(dateStart)) && (end_date.isBefore(dateEnd) || end_date.isEqual(dateEnd))) {
					while( start_date.isBefore(end_date) || start_date.isEqual(end_date)) {
						if(!listHolidays.contains(start_date))
					 number++;
						 start_date = start_date.plusDays(1);
							 }
				}
		}
		return number;
	}
	public int decreaseDays( UserAccountEntity userAccountEntity, int days, int daysHolidays) throws ServletException, IOException{
		if((userAccountEntity.getVacationDays() - days) >= 0 && days!=0 ) {
			System.out.println("userAccountEntity.getVacationDays() - days) = " + (userAccountEntity.getVacationDays() - days));
			System.out.println("days= " + days);
			System.out.println("daysHolidays = " + daysHolidays);
			  days = userAccountEntity.getVacationDays() - days; } 
		  else if(daysHolidays > 0 && days==0) { 
			  days = userAccountEntity.getVacationDays(); 
			  } else { 
				  days = -1; }
		System.out.println("in decrease days = " + days);
		return days;
	}
	
	public List<LocalDate> getListVacationRequestCreatedDays(List<VacationRequestEntity> listVacationRequest){
		List<LocalDate> listVacationRequestCreatedDays =  new ArrayList<>();
		for(int i=0; i<listVacationRequest.size(); i++) {
			String[] data_start =  listVacationRequest.get(i).getStartDate().split("-");
				int start_day = Integer.parseInt(data_start[0]);
				int start_month = Integer.parseInt(data_start[1]);
				int start_year = Integer.parseInt(data_start[2]);
			String[] data_end = listVacationRequest.get(i).getEndDate().split("-");
				int end_day = Integer.parseInt(data_end[0]);
				int end_month = Integer.parseInt(data_end[1]);
				int end_year = Integer.parseInt(data_end[2]);
				LocalDate start_date = LocalDate.of(start_year, start_month, start_day);
				LocalDate end_date = LocalDate.of(end_year, end_month, end_day);
					while( start_date.isBefore(end_date) || start_date.isEqual(end_date)) {
						listVacationRequestCreatedDays.add(start_date);
						 start_date = start_date.plusDays(1);
							 }
				}
		return listVacationRequestCreatedDays;
	}
	
	public  List<LocalDate> getListVacationRequestDays( VacationRequestEntity vacationRequestEntity){
		List<LocalDate> listVacationRequestDays =  new ArrayList<>();
			String[] data_start =  vacationRequestEntity.getStartDate().split("-");
				int start_day = Integer.parseInt(data_start[0]);
				int start_month = Integer.parseInt(data_start[1]);
				int start_year = Integer.parseInt(data_start[2]);
			String[] data_end = vacationRequestEntity.getEndDate().split("-");
				int end_day = Integer.parseInt(data_end[0]);
				int end_month = Integer.parseInt(data_end[1]);
				int end_year = Integer.parseInt(data_end[2]);
				LocalDate start_date = LocalDate.of(start_year, start_month, start_day);
				LocalDate end_date = LocalDate.of(end_year, end_month, end_day);
					while( start_date.isBefore(end_date) || start_date.isEqual(end_date)) {
						listVacationRequestDays.add(start_date);
						 start_date = start_date.plusDays(1);
							 }
		return listVacationRequestDays;
		
	}
	public void createRequest(List<VacationRequestEntity> listVacationRequest, int days, UserAccountEntity userAccountEntity, String startData, String endData, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		int number = 0;
		 VacationRequestEntity vacationRequestEntity = new VacationRequestEntity(startData, endData, userAccountEntity);
		 VacationRequestService vacationRequestService =VacationRequestService.getInstance(); 
		 List<LocalDate> listVacationRequestCreatedDays = getListVacationRequestCreatedDays(listVacationRequest);
		 List<LocalDate> listVacationRequestDays = getListVacationRequestDays(vacationRequestEntity);
		 for(int i=0; i<listVacationRequestDays.size(); i++)
		  if(listVacationRequestCreatedDays.contains(listVacationRequestDays.get(i))) 
			  number++;
		 if(number==0) {
		  VacationRequestEntity savedVacationRequestEntity = vacationRequestService.save(vacationRequestEntity);
		  userAccountEntity.setVacationDays(days); 
		  userAccountEntity = UserAccountService.getInstance().update(userAccountEntity); 
		  request.setAttribute("days", userAccountEntity.getVacationDays());
		  request.setAttribute("startData", startData);
		  request.setAttribute("endData", endData);
		  RequestDispatcher rd = request.getRequestDispatcher("requestCreated.jsp");
			rd.forward(request, response);
		  }else {
			  RequestDispatcher rd = request.getRequestDispatcher("requestCreatedError.jsp");
				rd.forward(request, response);
			   } 
		}
		 
	
	public int getNumberDaysHolidays(UserAccountEntity userAccountEntity, LocalDate start_date , LocalDate end_date, List<LocalDate> list ) {
		int daysHolidays = 0;
			 if(userAccountEntity.getVacationDays() > 0 ) {
				 while( start_date.isBefore(end_date) || start_date.isEqual(end_date)) {
			  if(list.contains(start_date)) {
				  daysHolidays++; 
			  } 
				  start_date = start_date.plusDays(1);  }}
			 return daysHolidays;
		}
	public int getNumberDaysVacation(UserAccountEntity userAccountEntity, LocalDate start_date , LocalDate end_date, List<LocalDate> list ) {
		int days = 0;
			 if(userAccountEntity.getVacationDays() > 0 ) {
				 while( start_date.isBefore(end_date) || start_date.isEqual(end_date)) {
			  if(!list.contains(start_date)) {
				  days++; 
			  } 
				  start_date = start_date.plusDays(1);  }
			 }
			 return days;
		}
}
