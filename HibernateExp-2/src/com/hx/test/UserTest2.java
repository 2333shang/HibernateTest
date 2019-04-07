package com.hx.test;

import java.util.HashSet;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.hx.entities.Orders;
import com.hx.entities.Orders2;
import com.hx.entities.User;
import com.hx.entities.User2;
import com.hx.statictools.HibernateUtils;
class UserTest2 {
	
	Session session = null;
	Transaction transaction = null;
	
	@BeforeEach
	void beginTest() {
		session = HibernateUtils.getCurrentSession();
		transaction = session.beginTransaction();
	}
	
	@Test
	void testOneToManySave() {
		User2 user = new User2(null, "aa", 18);
		
		Orders2 order1 = new Orders2(null, "aa-a");
		Orders2 order2 = new Orders2(null, "aa-b");
		
		user.getOrder2s().add(order1);
		user.getOrder2s().add(order2);
		
		session.save(order1);
		session.save(order2);
		
		session.save(user);
	}
	
	@Test
	void testOneToManyGet() {
		User2 user = (User2) session.get(User2.class, 1);
		System.out.println(user);
		System.out.println(user.getOrder2s().getClass().getName());
		System.out.println(user.getOrder2s());
		
	}
	
	@Test
	void testOneToManyUpdate() {
		User2 user = (User2) session.get(User2.class, 1);
		Orders2 order = user.getOrder2s().iterator().next();
		order.setOrderName("aa-aa");
		session.update(order);
		System.out.println(order);
	}
	
	@Test
	void testOneToManyDelete() {
		User2 user = (User2) session.get(User2.class, 1);
		session.delete(user);
	}
	
	@Test
	void testOneToManyDelete2() {
		User2 user = (User2) session.get(User2.class, 2);
		user.setOrder2s(null);
		session.delete(user);
	}
	
	@AfterEach
	void after() {
		transaction.commit();
	}
}
