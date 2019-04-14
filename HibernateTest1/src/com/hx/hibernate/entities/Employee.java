package com.hx.hibernate.entities;

import java.util.Date;

public class Employee {

	private Integer eid;
	private String ename;
	private String job;
	private Date hiredDate;
	private Double salary;
	
	private Department department;

	public Integer getEid() {
		return eid;
	}

	public void setEid(Integer eid) {
		this.eid = eid;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public Date getHiredDate() {
		return hiredDate;
	}

	public void setHiredDate(Date hiredDate) {
		this.hiredDate = hiredDate;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	@Override
	public String toString() {
		return "Employee [eid=" + eid + ", ename=" + ename + ", job=" + job + ", hiredDate=" + hiredDate + ", salary="
				+ salary + ", department=" + department + "]";
	}

	public Employee(Integer eid, String ename, String job, Date hiredDate, Double salary) {
		super();
		this.eid = eid;
		this.ename = ename;
		this.job = job;
		this.hiredDate = hiredDate;
		this.salary = salary;
	}

	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}
}
