package com.hx.test;

import static org.junit.Assert.*;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.hx.entities.Student;
import com.hx.entities.Teacher;
import com.hx.statictools.HibernateUtils;

public class CascadeTest {

	Session session = null;
	Transaction transaction = null;
	
	@Before
	public void beginTest() {
		session = HibernateUtils.getCurrentSession();
		transaction = session.beginTransaction();
	}
	
	@Test
	public void test() {
		Teacher t = new Teacher(null, "tea-5");
		Student s = new Student(null, "s-5");
		
		t.getStudents().add(s);
		
		session.save(t);
	}
	
	/**
	 * 1对多删除：不设置级联属性，仅删除1端以及清空n端外键
	 * 			设置级联属性，都删除
	 * 多对1删除：不能删除1端记录
	 * 多对多删除：不设置级联属性，删除多端以及中间表对应的记录
	 *  		设置级联属性，删除多端以及中间表对应的记录，以及中间表
	 *  		对应的另一端表记录和表对应的中间表记录
	 *  孤儿删除：删除与对应记录解除关联关系的记录（delete-orphan）
	 */
	@Test
	public void testDelete() {
		Teacher t = (Teacher) session.get(Teacher.class, 2);
		
		session.delete(t);
	}
	
	@Test
	public void testDeleteOrphan() {
		Teacher t = (Teacher) session.get(Teacher.class, 1);
		Student s = (Student) session.get(Student.class, 2);
		
		t.getStudents().remove(s);
	}
	
	@After
	public void after() {
		transaction.commit();
	}

}
