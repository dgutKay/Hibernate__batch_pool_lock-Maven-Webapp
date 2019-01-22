package com.kay.hibernate.entity;

import java.util.Set;

public class Teacher {
	private int id;
	private String name;
	private Set<Classes> classesSet;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Classes> getClassesSet() {
		return classesSet;
	}

	public void setClassesSet(Set<Classes> classesSet) {
		this.classesSet = classesSet;
	}

	public Teacher(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Teacher() {
		super();
	}

}
