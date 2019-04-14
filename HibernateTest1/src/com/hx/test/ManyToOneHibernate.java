package com.hx.test;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.hx.hibernate.entities.Department;
import com.hx.hibernate.entities.Employee;

public class ManyToOneHibernate {

	private SessionFactory sessionFactory;
	private Session session = null;
	private Transaction transaction = null;
	
	@Before
	public void beginTest() {
		Configuration conf=new Configuration().configure();
		ServiceRegistryBuilder srb=new ServiceRegistryBuilder().applySettings(conf.getProperties());
		ServiceRegistry sr=srb.buildServiceRegistry();
		sessionFactory=conf.buildSessionFactory(sr);
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
	}
	
	@After
	public void after() {
		transaction.commit();
		session.close();
		sessionFactory.close();
	}
	
	/**
	 * 保存部门
	 */
	@Test
	public void testSaveDept() {
		Department department1 = new Department(null, "销售部");
		Department department2 = new Department(null, "技术部");
		Department department3 = new Department(null, "后勤部");
		
		session.save(department1);
		session.save(department2);
		session.save(department3);
	}
	
	/**
	 * 新增员工
	 */
	@Test
	public void testSaveEmp() {
		Employee employee1 = new Employee(null, "aaa", "java开发", new Date(), 1200.0);
		Employee employee2 = new Employee(null, "bbb", "php开发", new Date(), 1200.0);
		Employee employee3 = new Employee(null, "ccc", "前端开发", new Date(), 1200.0);
		
		Department department = (Department) session.get(Department.class, 2);
		//给员工设置部门
		employee1.setDepartment(department);
		employee2.setDepartment(department);
		employee3.setDepartment(department);
		
		session.save(employee1);
		session.save(employee2);
		session.save(employee3);
	}
	
	/**
	 * 员工从一个部门调整到另一个部门
	 */
	@Test
	public void testEmpUpdateDepttestdeleteDeptAndEmp() {
		Employee employee = (Employee) session.get(Employee.class, 4);
		Department department = (Department) session.get(Department.class, 3);
		
		employee.setDepartment(department);
		session.update(employee);
	}
	
	/**
	 * 级联删除：删除部门及其所有员工
	 * 在多对一标签设置级联属性：cascade=delete
	 */
	@Test
	public void testdeleteDeptAndEmp() {
		Department department = (Department) session.get(Department.class, 2);
		
		session.delete(department);
	}
	
	/**
	 * 查询部门是什么的所有员工，显示员工的id，所属部门的名称
	 */
	@Test
	public void testEmpByDept() {
		Department department = (Department) session.get(Department.class, 3);
		String hql = "From Employee e where e.department = ?";
		List<Employee> list = session.createQuery(hql).setEntity(0, department).list();
		for(Employee e:list) {
			System.out.println(e.getEid() +":" + e.getDepartment());
		}
	}

	/**
	 * 查询员工数量
	 * 查询薪资大于多少的员工的数量，每页显示三条记录，显示部门，id，所属部门名称
	 */
	@Test
	public void testEmpCountAndPage() {
		String hql = "select count(*) From Employee e where e.salary > ?";//查询员工数量
		Long count = (Long) session.createQuery(hql).setDouble(0, 2200).uniqueResult();//uniqueResult()返回一条记录
		System.out.println("记录共"+count+"条");
		hql = "From Employee e left join fetch e.department where e.salary > ?";
		List<Employee> list = session.createQuery(hql).setDouble(0, 2200)//setFirstResult()设置从第几条记录查起，setMaxResults()设置一共查几条记录
				.setFirstResult(0).setMaxResults(3).list();//list()返回一list个集合
		for(Employee e:list) {
			System.out.println(e.getEid() +":" + e.getDepartment());
		}
	}
}
