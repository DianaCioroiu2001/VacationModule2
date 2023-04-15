package com.fdm.vacationModule.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionUtils {
	
	private static Configuration configuration = new Configuration().configure();
	private static SessionFactory sessionFactory = configuration.buildSessionFactory();
	
	public static Session createSession() {
        return sessionFactory.openSession();
	}
	
	public static void closeSessionFactory() {
		sessionFactory.close();
	}	

}
