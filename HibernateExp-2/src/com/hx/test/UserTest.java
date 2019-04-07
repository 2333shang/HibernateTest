package com.hx.test;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.hx.entities.Orders;
import com.hx.entities.User;
import com.hx.statictools.HibernateUtils;
class UserTest {
	
	Session session = null;
	Transaction transaction = null;
	
	@BeforeEach
	void beginTest() {
		session = HibernateUtils.getCurrentSession();
		transaction = session.beginTransaction();
	}
	
	@Test
	void testManyToOneSave() {
		User user = new User(null, "aa", 18);
		
		Orders order1 = new Orders(null, "aa-a");
		Orders order2 = new Orders(null, "aa-b");
		
		order1.setUser(user);
		order2.setUser(user);
		
		session.save(user);
		
		session.save(order1);
		session.save(order2);
		
	}
	
	@Test
	void testManyToOneGet() {
		Orders order = (Orders) session.get(Orders.class, 1);
		System.out.println(order);
		System.out.println(order.getUser().getClass().getName());
		System.out.println(order.getUser());
		
	}
	
	@Test
	void testManyToOneGet2() {
		Orders order = (Orders) session.get(Orders.class, 1);
		System.out.println(order);
		System.out.println(order.getUser().getClass().getName());
		System.out.println(order.getUser());
		
	}
	
	@Test
	void testManyToOneGet3() {
		Orders order = (Orders) session.get(Orders.class, 1);
		System.out.println(order);
		session.close();
		transaction.commit();
		
		System.out.println(order.getUser());
		
	}
	
	@Test
	void testManyToOneUpdate() {
		Orders order = (Orders) session.get(Orders.class, 1);
		order.getUser().setName("aaa");
		session.update(order.getUser());
		System.out.println(order.getUser());
	}
	
	@Test
	void testManyToOneDelete() {
		Orders order = (Orders) session.get(Orders.class, 1);
		session.delete(order.getUser());
	}
	
	@Test
	void testManyToOneDelete2() {
		User user = (User) session.get(User.class, 1);
		session.delete(user);
	}
	
	@Test
	void testManyToOneDelete3() {
		Orders order = (Orders) session.get(Orders.class, 3);
		session.delete(order.getUser());
	}
	
	@AfterEach
	void after() {
		transaction.commit();
	}
}
