package com.hx.entities;

import java.util.HashSet;
import java.util.Set;

public class Student {

	private Integer id;
	private String name;
	private Set<Teacher> teachers = new HashSet<>();
	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Student(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + "]";
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Set<Teacher> getTeachers() {
		return teachers;
	}
	public void setTeachers(Set<Teacher> teachers) {
		this.teachers = teachers;
	}
}
