package com.hx.test;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.Test;

import com.hx.entities.User;
import com.hx.service.UserService;
import com.hx.statictools.HibernateUtils;
class UserTest {
	
	@Test
	void testSession() {
		Session openSession = HibernateUtils.getCurrentSession();
		Session openSession2 = HibernateUtils.getCurrentSession();
		System.out.println(openSession == openSession2);
	}

	@Test
	void testSave() {
		UserService service = new UserService();
		User user = new User(null, "张三", 18);
		service.saveUser(user);
	}
	
	/**
	 * 数据库id为自增，save()方法无论对象有无id，
	 * 均会将对象的其它属性插入数据库中，并返回一个新的自增id
	 */
	@Test
	void testSave2() {
		UserService service = new UserService();
		User user = service.getUser(1);
		user.setName("gg");
		user.setId(11);
		service.saveUser(user);
	}
	
	/**
	 * get()方法返回的是一个实际的对象，不支持延迟加载
	 * 	检索的id不存在会返回一个空对象
	 * load()返回的是一个代理类对象，支持延迟加载，检索的id不存在会抛出异常
	 */
	@Test
	void testGet() {
		UserService service = new UserService();
		User user = service.getUser(1);
		System.out.println(user);
	}
	
	@Test
	void testGets() {
		UserService service = new UserService();
		List<User> users = service.getUsers();
		System.out.println(users);
	}
	
	@Test
	void testload() {
		UserService service = new UserService();
		User user = service.loadUser(1);
		System.out.println(user);
	}
	
	@Test
	void testloadAndGet() {
		UserService service = new UserService();
		User user = service.getUser(1);
		System.out.println(user.getClass().getName());
		user = service.loadUser(1);
		System.out.println(user.getClass().getName());
	}
	
	/**
	 * delete方法根据id删除对象，也可以通过重载方法用hql进行批量删除
	 */
	@Test
	void testDelete() {
		UserService service = new UserService();
		User user = new User();
		user.setId(2);
		service.deleteUser(user);
	}
	
	/**
	 * update()方法会将所有的字段都修改
	 */
	@Test
	void testupdate() {
		UserService service = new UserService();
		User user = service.getUser(1);
		user.setName("李四");
		service.updateUser(user);
	}
	
	/**
	 * id指定自增策略后，更新持久化对象时id不能进行修改
	 * Hibernate在事务提交后，会调用flush()方法，将持久化对象中的更新同步到数据库中
	 */
	@Test
	void testupdate2() {
		Session session = HibernateUtils.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		User user = (User) session.get(User.class,1);
		user.setName("qqq");
		transaction.commit();
	}
	
	/**
	 * update()方法将游离的对象转换为持久化对象，所以须保证session中没有与之对应id的对象，否则会抛出异常。
	 */
	@Test
	void testupdate3() {
		UserService service = new UserService();
		User user = new User(1, "zzz", 33);
		service.updateUser(user);
	}
	
	/**saveOrUpdate(相当于save和update方法的结合):
	 * 				      若对象是临时态，执行insert操作
	 * 				      若对象是游离态，执行update操作，若id在数据库中没有对应的记录，抛出异常
	 *  			      若对象是持久态，执行update操作
	 * 
	 */
	@Test
	void testsaveOrUpdate() {
		UserService service = new UserService();
		User user = service.getUser(1);
		user.setName("ccc");
		service.saveOrUpdateUser(user);
		
		User user2 = new User(null, "ddd", 21);
		service.saveOrUpdateUser(user2);
	}
	
	@Test
	void testsaveOrUpdate2() {
		UserService service = new UserService();
		User user = new User(15, "ddd", 21);
		service.saveOrUpdateUser(user);
	}
	
	/**
	 * merge():若对象是临时态，执行insert操作
	 * 		        若对象是游离态：
	 * 				若id在数据库中没有对应的记录，执行insert操作
	 * 				若id在数据库中没有对应的记录，执行update操作
	 *  	        若对象是持久态，执行update操作
	 */
	@Test
	void testMerge1() {
		UserService service = new UserService();
		User user = new User(null, "eee", 22);
		service.mergeUser(user);
	}
	
	@Test
	void testMerge2() {
		UserService service = new UserService();
		User user = new User(4, "李四", 24);
		service.mergeUser(user);
	}
	
	@Test
	void testMerge3() {
		UserService service = new UserService();
		User user = new User(8, "王五", 25);
		service.mergeUser(user);
	}
	
	@Test
	void testMerge4() {
		UserService service = new UserService();
		User user = service.getUser(1);
		user.setName("aaa");
		service.mergeUser(user);
		
	}
}
