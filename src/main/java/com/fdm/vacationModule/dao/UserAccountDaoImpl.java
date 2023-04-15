package com.fdm.vacationModule.dao;



import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.fdm.vacationModule.dao.entities.UserAccountEntity;
import com.fdm.vacationModule.dao.entities.VacationRequestEntity;

public  class UserAccountDaoImpl implements UserAccountDao{

	@Override
	public UserAccountEntity save(UserAccountEntity userAccountEntity, Session session) {
		session.save(userAccountEntity);
        session.refresh(userAccountEntity);
        return userAccountEntity;   
	}

	@Override
	public UserAccountEntity findById(int id, Session session) {
		return session.get(UserAccountEntity.class, id);
	}

	@Override
	public UserAccountEntity update(UserAccountEntity userAccountEntity, Session session) {
		 session.update(userAccountEntity);
	        return userAccountEntity;
	}

	@Override
	public List<VacationRequestEntity> findAllRequest(int id, Session session) {
		UserAccountEntity userAccountEntity = session.get(UserAccountEntity.class, id);        
        Hibernate.initialize(userAccountEntity.getVacationRequest());
        List<VacationRequestEntity> listVacationRequest = userAccountEntity.getVacationRequest();
		return listVacationRequest;
	}

	@Override
	public UserAccountEntity findByEmail(String email, Session session) {
		Query query = session.createQuery(" from UserAccountEntity where email= :email");
        query.setParameter("email", email);
        UserAccountEntity userAccountEntity = (UserAccountEntity) query.uniqueResult();
		return userAccountEntity;
	}

	@Override
	public UserAccountEntity findByOtp(String otp, Session session) {
		Query query = session.createQuery("from UserAccountEntity where otp= :otp");
        query.setParameter("otp", otp);
        UserAccountEntity userAccountEntity = (UserAccountEntity) query.uniqueResult();
		return userAccountEntity;
	}

	@Override
	public UserAccountEntity delete(UserAccountEntity userAccountEntity, Session session) {
		 session.delete(userAccountEntity);
	        return userAccountEntity;
	}

	

	}


