package com.example.demo.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;

//@Entity
@SuppressWarnings("serial")
public class User extends SuperEntity<User>{
//	@Id
//	private int id;
	
	@Override
	public String toString() {
		return "User [id="+id+",name=" + name + ", age=" + age + "]";
	}

	private String name;
	
	private int age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

//	public int getId() {
//		return id;
//	}
//
//	public void setId(int id) {
//		this.id = id;
//	}
	
}
