package com.fdm.vacationModule.dao;

import java.util.List;

import org.hibernate.Session;

import com.fdm.vacationModule.dao.entities.UserAccountEntity;
import com.fdm.vacationModule.dao.entities.VacationRequestEntity;

public interface UserAccountDao {
	
	UserAccountEntity save(UserAccountEntity userAccountEntity, Session session);
	UserAccountEntity findById(int id, Session session);
	UserAccountEntity update(UserAccountEntity userAccountEntity, Session session);
	UserAccountEntity delete(UserAccountEntity userAccountEntity, Session session);
	List<VacationRequestEntity> findAllRequest(int id, Session session);
	UserAccountEntity findByEmail(String email, Session session);
	UserAccountEntity findByOtp(String otp, Session session);
}
