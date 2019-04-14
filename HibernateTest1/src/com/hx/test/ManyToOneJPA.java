package com.hx.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ManyToOneJPA {

	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;
	private EntityTransaction transaction;
	
	@Before
	public void init(){
		//创建EntityManagerFactory，类似SessionFactory,参数名为配置文件中persistence-unit标签name属性值指定的值
		entityManagerFactory = Persistence.createEntityManagerFactory("HibernateTest1");
		//创建EntityManager，类似Session
		entityManager = entityManagerFactory.createEntityManager();
		//创建事务，与Hibernate不同的是，需要先获取事务再开启事务
		transaction = entityManager.getTransaction();
		transaction.begin();
	}
	
	@After
	public void destroy(){
		transaction.commit();
		entityManager.close();
		entityManagerFactory.close();
	}
	
	@Test
	public void test() {
		
	}

}
