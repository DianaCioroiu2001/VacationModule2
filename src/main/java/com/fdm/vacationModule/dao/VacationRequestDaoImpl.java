package com.fdm.vacationModule.dao;

import java.sql.DriverManager;
import java.sql.SQLException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.fdm.vacationModule.dao.entities.UserAccountEntity;
import com.fdm.vacationModule.dao.entities.VacationRequestEntity;
import com.fdm.vacationModule.service.UserAccountService;
import com.fdm.vacationModule.service.VacationRequestService;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class VacationRequestDaoImpl implements VacationRequestDao{

	@Override
	public VacationRequestEntity save(VacationRequestEntity vacationRequestEntity) {
			Configuration configuration = new Configuration().configure();
			SessionFactory sessionFactory = configuration.buildSessionFactory();
			Session session = sessionFactory.openSession();
			Transaction tx =  session.beginTransaction();
			int id = (int) session.save(vacationRequestEntity);
			tx.commit();
			VacationRequestEntity savedVacationRequestEntity = session.get(VacationRequestEntity.class, id);
			session.close();
			sessionFactory.close();
			return vacationRequestEntity;
		}
	public VacationRequestEntity findById(int id) {
		Configuration configuration = new Configuration().configure();

        SessionFactory sessionFactory = configuration.buildSessionFactory();

        Session session = sessionFactory.openSession();

        Transaction tx = session.beginTransaction();

        VacationRequestEntity vacationRequestEntity = session.get(VacationRequestEntity.class, id);
        
        tx.commit();


        session.close();

        sessionFactory.close();
        
        return vacationRequestEntity;
	}
	@Override
	public VacationRequestEntity update(VacationRequestEntity vacationRequestEntity, String otp) {
		Configuration configuration = new Configuration().configure();
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx =  session.beginTransaction();
	    session.update(vacationRequestEntity);
	    int id = UserAccountService.getInstance().getUser(otp).getId();
		tx.commit();
		VacationRequestEntity savedVacationRequestEntity = session.get(VacationRequestEntity.class, id);
		session.close();
		sessionFactory.close();
		return vacationRequestEntity;
	}
	
	
	}


