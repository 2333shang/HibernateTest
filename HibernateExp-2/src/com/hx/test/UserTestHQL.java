package com.hx.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.hx.entities.Orders;
import com.hx.entities.User;
import com.hx.entities.User2;
import com.hx.statictools.HibernateUtils;

class UserTestHQL {
	Session session = null;
	Transaction transaction = null;
	
	@BeforeEach
	void beginTest() {
		session = HibernateUtils.getCurrentSession();
		transaction = session.beginTransaction();
	}
	
	//HQL查询的懒加载与配置文件属性lazy一致，默认不会查询关联的一端的记录，可通过
	//SQL语句的左外连接来改变懒加载机制
	//HQL不支持insert操作
	@Test
	void testParam() {
		String hql = "From User u where u.age = ? and u.name like ?";
		Query query = session.createQuery(hql);
		query.setInteger(0, 18).setString(1, "%a%");
		List list = query.list();
		System.out.println(list);
	}
	
	@Test
	void testParamWithEntity() {
		String hql = "From Orders o where o.user = ?";
		Query query = session.createQuery(hql);
		query.setEntity(0, session.get(User.class, 2));
		List list = query.list();
		System.out.println(list);
	}
	
	@Test
	void testPage() {
		String hql = "From User";
		Query query = session.createQuery(hql);
		query.setFirstResult(2).setMaxResults(2);
		List list = query.list();
		System.out.println(list);
	}
	
	@Test
	void testNamedSpace() {
		Query query = session.getNamedQuery("queryUser");
		query.setInteger("age", 18);
		List list = query.list();
		System.out.println(list);
	}
	
	@Test
	void testFieldQuery() {
		String hql = "select new User(u.name) From User u";
		Query query = session.createQuery(hql);
		List list = query.list();
		System.out.println(list);
	}
	
	@Test
	void testFieldQuery2() {
		String hql = "select u.name,u.age From User u";
		Query query = session.createQuery(hql);
		List<Object[]> list = query.list();
		for(Object[] l:list) {
			System.out.println(Arrays.asList(l));
		}
	}
	
	@Test
	void testLeftJoinFetch() {
		String hql = "From User2 u left join fetch u.order2s";
		Query query = session.createQuery(hql);
		List<User2> list = query.list();
		for(User2 u:list) {
			System.out.println(u);
			System.out.println(u.getOrder2s());
		}
	}
	
	@Test
	void testInnerJoinFetch() {
		String hql = "From User2 u Inner join fetch u.order2s";
		Query query = session.createQuery(hql);
		List<User2> list = query.list();
		for(User2 u:list) {
			System.out.println(u);
			System.out.println(u.getOrder2s());
		}
	}

	@Test
	void testLeftJoin() {
		String hql = "From User2 u left join u.order2s";
		Query query = session.createQuery(hql);
		List<Object[]> list = query.list();
		for(Object[] u:list) {
			System.out.println(Arrays.asList(u));
		}
	}
	
	@Test
	void testLeftJoin2() {
		String hql = "select u From User2 u left join u.order2s";
		Query query = session.createQuery(hql);
		List<User2> list = query.list();
		for(User2 u:list) {
			System.out.println(u.getName());
			System.out.println(u.getOrder2s());
		}
	}
	
	@Test
	void testInnerJoin() {
		String hql = "From User2 u Inner join u.order2s";
		Query query = session.createQuery(hql);
		List<Object[]> list = query.list();
		for(Object[] u:list) {
			System.out.println(Arrays.asList(u));
		}
	}
	
	@Test
	void testJiaocha() {
		String hql = "From User2 u,Orders2 o where"
				+ " u.id = o.id";
		Query query = session.createQuery(hql);
		List<Object[]> list = query.list();
		for(Object[] u:list) {
			System.out.println(Arrays.asList(u));
		}
	}
	
	@Test
	void testChildQuery() {
		String hql = "From User u where u.age < ("
				+ "select avg(u1.age) from User u1)";
		Query query = session.createQuery(hql);
		List<User> user = query.list();
		for(User u:user) {
			System.out.println(u);
		}
	}
	
	@Test
	void testChildQueryWithIn() {
		String hql = "From User u where u.age in ("
				+ "select u1.age from User u1 where u1.age>20)";
		Query query = session.createQuery(hql);
		List<User> user = query.list();
		for(User u:user) {
			System.out.println(u);
		}
	}
	
	@Test
	void testChildQueryWithExists() {
		String hql = "From Orders o where exists ("
				+ "select u from o.user u where"
				+ " u.age < 20)";
		Query query = session.createQuery(hql);
		List<Orders> orders = query.list();
		for(Orders o:orders) {
			System.out.println(o);
		}
	}
	
	@AfterEach
	void after() {
		transaction.commit();
	}
}
