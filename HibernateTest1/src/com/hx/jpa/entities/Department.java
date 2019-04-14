package com.hx.jpa.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.hx.jpa.entities.Employee;

@Table(name="department")
@Entity(name="jpa.department")
public class Department {

	private Integer deptid;
	private String name;
	
	private Set<Employee> employees = new HashSet<>();
	
//	@JoinColumn(name="deptid")
	@OneToMany(mappedBy="department")
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
	
	@GeneratedValue()
	@Id
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
