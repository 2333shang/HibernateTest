package com.hx.test;


import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.hx.entities.Student;
import com.hx.entities.Teacher;
import com.hx.statictools.HibernateUtils;

class CascadeTest {

	Session session = null;
	Transaction transaction = null;
	
	@Before
	void beginTest() {
		session = HibernateUtils.getCurrentSession();
		transaction = session.beginTransaction();
	}
	
	@Test
	void test() {
		Teacher t = new Teacher(null, "tea-5");
		Student s = new Student(null, "s-5");
		
		t.getStudents().add(s);
		
		session.save(t);
	}
	
	@Test
	void testDelete() {
		Teacher t = (Teacher) session.get(Teacher.class, 2);
		
		session.delete(t);
	}
	
	@Test
	void testDeleteOrphan() {
		Teacher t = (Teacher) session.get(Teacher.class, 1);
		Student s = (Student) session.get(Student.class, 1);
		
		t.getStudents().remove(s);
	}
	
	@After
	void after() {
		transaction.commit();
	}

}
