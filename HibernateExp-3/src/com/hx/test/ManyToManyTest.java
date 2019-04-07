package com.hx.test;


import java.util.Iterator;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.hx.entities.Student;
import com.hx.entities.Teacher;
import com.hx.statictools.HibernateUtils;

public class ManyToManyTest {

	Session session = null;
	Transaction transaction = null;
	
	@Before
	public void beginTest() {
		session = HibernateUtils.getCurrentSession();
		transaction = session.beginTransaction();
	}
	
	@Test
	public void testSave() {
		Teacher t1 = new Teacher(null, "tea-1");
		Teacher t2 = new Teacher(null, "tea-2");
		
		Student s1 = new Student(null, "s-1");
		Student s2 = new Student(null, "s-2");
		
		t1.getStudents().add(s1);
		t1.getStudents().add(s2);
		t2.getStudents().add(s1);
		t2.getStudents().add(s2);
		
		session.save(s1);
		session.save(s2);
		
		session.save(t1);
		session.save(t2);
	}
	
	@Test
	public void testSave2() {
		Teacher t = new Teacher(null,"tea-3");
		
		Student s = (Student) session.get(Student.class, 2);
		t.getStudents().add(s);
		
		session.save(t);
		session.update(s);
	}
	
	@Test
	public void testGet() {
		Teacher t1 = (Teacher) session.get(Teacher.class, 2);
		System.out.println(t1.getName());
		Set<Student> students = t1.getStudents();
		for(Student s:students) {
			System.out.println(s);
		}
	}
	
	@Test
	public void testUpdate() {
		Teacher t1 = (Teacher) session.get(Teacher.class, 1);
		Set<Student> students = t1.getStudents();
		Student student = students.iterator().next();
		student.setName("ss-1");
		session.update(t1);
		System.out.println(student);
	}
	
	@Test
	public void testDelete() {
		Teacher t = (Teacher) session.get(Teacher.class, 1);
		Iterator students = t.getStudents().iterator();
		while(students.hasNext()) {
			Student s = (Student) students.next();
			s.getTeachers().remove(t);
		}
		session.delete(t);
	}
	
	/**
	 * 维护关联关系的一方，可以不设置级联属性删除己方记录和相关的外键记录或中间表记录，
	 * 但不能删除关联一端的记录。
	 * 非维护关联关系的一方，由于外键约束，不能直接删除
	 */
	@Test
	public void testDelete2() {
		Student s = (Student) session.get(Student.class, 1);
		Iterator teachers = s.getTeachers().iterator();
		while(teachers.hasNext()) {
			Teacher t = (Teacher) teachers.next();
			t.getStudents().remove(s);
		}
		session.delete(s);
	}
	
	@After
	public void after() {
		transaction.commit();
	}

}
