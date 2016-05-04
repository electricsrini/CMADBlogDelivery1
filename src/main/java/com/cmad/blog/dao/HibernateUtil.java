package com.cmad.blog.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import java.util.*;


public class HibernateUtil {
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		

	}
	
	private static SessionFactory sessionFactory;
	private static ThreadLocal<Session> tlSessions = new ThreadLocal<Session>();
	private static ThreadLocal<Transaction> tlTransactions= new ThreadLocal<Transaction>();
	static{
		Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
		sessionFactory = configuration.buildSessionFactory(serviceRegistry);
	}
			
	public static Session currentSession() {
		Session ses = tlSessions.get();
		if(ses == null){
			ses = sessionFactory.openSession();
			tlSessions.set(ses);
		}
		return ses;
	}

	public static void closeSession() {
		Session ses = tlSessions.get();
		if(ses!=null){
			ses.close();
			tlSessions.set(null);
		}
		
	}
	
	public static Session openCurrentSessionForTransaction(){
		Session ses = tlSessions.get();
		if(ses == null){
			ses = sessionFactory.openSession();
			tlSessions.set(ses);
		}
		tlTransactions.set(ses.beginTransaction());
		return ses;
	}
	
	public static void closeCurrentSessionPostTransaction(){
		Session ses = tlSessions.get();
		if(ses!=null){
			tlTransactions.get().commit();
			ses.close();
			tlSessions.set(null);
		}
	}

}
