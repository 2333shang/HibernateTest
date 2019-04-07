package com.hx.test;

import static org.junit.Assert.*;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Before;
import org.junit.Test;

import com.hx.entities.Student;
import com.hx.entities.Teacher;
import com.hx.statictools.HibernateUtils;

public class TestCascade {

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
	
	@Test
	public void testDelete() {
		Teacher t = (Teacher) session.get(Teacher.class, 2);
		
		session.delete(t);
	}
	
	@Test
	public void testDeleteOrphan() {
		Teacher t = (Teacher) session.get(Teacher.class, 1);
		Student s = (Student) session.get(Student.class, 1);
		
		t.getStudents().remove(s);
	}

}
