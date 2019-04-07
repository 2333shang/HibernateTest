package com.hx.statictools;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class HibernateUtils {
	
	private static SessionFactory sessionFactory;
	
	static {
		Configuration conf=new Configuration().configure();
		ServiceRegistryBuilder srb=new ServiceRegistryBuilder().applySettings(conf.getProperties());
		ServiceRegistry sr=srb.buildServiceRegistry();
		sessionFactory=conf.buildSessionFactory(sr);
	}
	
	/**
	 * getCurrentSession()使用当前的session，若没有则新创建一个，然后绑定到当前线程中
	 * 它会自动关闭和打开session
	 * @return
	 */
	public static Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}
}
