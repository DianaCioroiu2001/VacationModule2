package com.fdm.vacationModule.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.fdm.vacationModule.dao.UserAccountDao;
import com.fdm.vacationModule.dao.UserAccountDaoImpl;
import com.fdm.vacationModule.dao.entities.UserAccountEntity;
import com.fdm.vacationModule.dao.entities.VacationRequestEntity;

public class UserAccountService {
	
	private static UserAccountService instance;
	private UserAccountDao userAccountDao;
	
	private OTPService otpService;
	private UserAccountService userAccountService;

	
	
	
	private UserAccountService() {
		this.userAccountDao =  new UserAccountDaoImpl();
		this.otpService = OTPService.getInstance();
	}

	public static UserAccountService getInstance() {
		if(instance == null) {
			instance = new UserAccountService();
		}
		return instance;
	}

	public UserAccountEntity save(UserAccountEntity userAccountEntity) {
		Session session = SessionUtils.createSession();
		Transaction transaction = session.beginTransaction();
		UserAccountEntity uae;
		try {
			uae = userAccountDao.save(userAccountEntity, session);
			transaction.commit();
		} finally {
			session.close();
		}		
		return uae;
	
	}
	
	public UserAccountEntity update(UserAccountEntity userAccountEntity) {
		Session session = SessionUtils.createSession();
		Transaction transaction = session.beginTransaction();
		UserAccountEntity uae;
		try {
			uae = userAccountDao.update(userAccountEntity, session);
			transaction.commit();
		} finally {
			session.close();
		}		
		return uae;
	
	}
	
	public UserAccountEntity delete(UserAccountEntity userAccountEntity) {
		Session session = SessionUtils.createSession();
		Transaction transaction = session.beginTransaction();
		UserAccountEntity uae;
		try {
			uae = userAccountDao.delete(userAccountEntity, session);
			transaction.commit();
		} finally {
			session.close();
		}		
		return uae;
	
	}
	
	public String generateOTP(String email) {
		
		Session session = SessionUtils.createSession();
		Transaction transaction = session.beginTransaction();
		String OTP;
		try {
			OTP = otpService.generateOTP();
			System.out.println("email in generateOtp= " + email);
			UserAccountEntity uae = userAccountDao.findByEmail(email, session);
			uae.setOtp(OTP);
			userAccountDao.update(uae, session);
			transaction.commit();
		} finally {
			session.close();
		}
		return OTP;
	}
public List<VacationRequestEntity> getVacationRequest(int userAccountId){
		
	Session session = SessionUtils.createSession();
	List<VacationRequestEntity> findAllRequest;
	try {
		findAllRequest = userAccountDao.findAllRequest(userAccountId, session);
	} finally {
		session.close();
	}
	return findAllRequest;
	}
public UserAccountEntity getUserAccount (String email) {
	Session session = SessionUtils.createSession();
	UserAccountEntity userAccountEntity;
	try {
		userAccountEntity = userAccountDao.findByEmail(email, session);
	} finally {
		session.close();
	}
	return userAccountEntity;
	
}
public UserAccountEntity getUser (String otp) {
	Session session = SessionUtils.createSession();
	UserAccountEntity userAccountEntity;
	try {
		userAccountEntity = userAccountDao.findByOtp(otp, session);
	} finally {
		session.close();
	}
	return userAccountEntity;
	
}
	
public boolean verifyCredentials(String email, String password, String otp) {
	UserAccountEntity userAccountEntity = UserAccountService.getInstance().getUserAccount(email);
	boolean isCorrect = false;
	if (userAccountEntity != null) {			
		if (email.equals(userAccountEntity.getEmail()) && 
			password.equals(userAccountEntity.getParola())
			&& 
			otp.equals(userAccountEntity.getOtp())) {
			isCorrect = true;
		}
	} 
	return isCorrect;
}
public boolean verifyCredentials(String otp) {
	UserAccountEntity userAccountEntity = UserAccountService.getInstance().getUser(otp);
	boolean isCorrect = false;
	if (userAccountEntity != null) {			
		if (otp.equals(userAccountEntity.getOtp())) {
			isCorrect = true;
		}
	} 
	return isCorrect;
}



public int decreaseDaysModify(UserAccountEntity user, LocalDate start_date , LocalDate end_date, List<LocalDate> list ) {
int days = 0;
while( start_date.isBefore(end_date) || start_date.isEqual(end_date)) {
	  if(!list.contains(start_date)) {
		  days++; 
	  } 
		  start_date = start_date.plusDays(1);  }
		return days;
}
public void verifyCredentials(HttpServletResponse response, HttpServletRequest request, String otp) throws ServletException, IOException {
	if(UserAccountService.getInstance().verifyCredentials(otp)) {
		HttpSession session = request.getSession();
		session.setAttribute("otp", otp);
	} else {
		request.setAttribute("message", "Invalid user credentials");
		request.getRequestDispatcher("/error.html").forward(request, response);
	}	
}

public Boolean verifyEmail(String email) throws IOException { 
	if(email.length()<10)
		return false;
	if( email.substring(email.length() - 10).equals("@gmail.com") || email.substring(email.length() - 10).equals("@yahoo.com")) {
		System.out.println(email.length() - 10);
		return true;
	}
return false;
}

public Boolean verifyPassword(String password) {
	Boolean message = false;
	if(password.contains("0") || password.contains("1") || password.contains("2") || password.contains("3") || password.contains("4") || password.contains("5") || password.contains("6") || password.contains("7") || password.contains("8") || password.contains("9")) {
	message = true;
	}
	for(int i=0; i < password.length(); i++) {
		if(Character.isUpperCase(password.charAt(i)))
			message = true;
	}
	return message;
}

public Boolean verifyCnp(String cnp) {
	if(cnp.length() == 13)
		return true;
return false;
}
		  
	 //else {
		//UserAccountService out = response.getWriter();
		//out.write("There is already a request starting from " + startData);
	}

//	}
