package com.hx.hibernate.entities;

import java.util.HashSet;
import java.util.Set;

public class Department {

	private Integer deptid;
	private String name;
	private Set<Employee> employees = new HashSet<>();
	
	public Set<Employee> getEmployees() {
		return employees;
	}
	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}
	@Override
	public String toString() {
		return "Department [deptid=" + deptid + ", name=" + name + "]";
	}
	public Department() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Department(Integer deptid, String name) {
		super();
		this.deptid = deptid;
		this.name = name;
	}
	
	public Integer getDeptid() {
		return deptid;
	}
	public void setDeptid(Integer deptid) {
		this.deptid = deptid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
