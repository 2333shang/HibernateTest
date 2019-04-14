package com.hx.generator.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the employee database table.
 * 
 */
@Entity(name="generator.Employee")
public class Employee implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int eid;

	private String ename;

	@Temporal(TemporalType.TIMESTAMP)
	private Date hireddate;

	private String job;

	private double salary;

	//bi-directional many-to-one association to Department
	@ManyToOne(cascade={CascadeType.REMOVE})
	@JoinColumn(name="DEPTID")
	private Department department;

	public Employee() {
	}

	public int getEid() {
		return this.eid;
	}

	public void setEid(int eid) {
		this.eid = eid;
	}

	public String getEname() {
		return this.ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public Date getHireddate() {
		return this.hireddate;
	}

	public void setHireddate(Date hireddate) {
		this.hireddate = hireddate;
	}

	public String getJob() {
		return this.job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public double getSalary() {
		return this.salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public Department getDepartment() {
		return this.department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

}