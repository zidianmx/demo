package com.example.demo.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;

//@Entity
//@SuppressWarnings("serial")
@TableName(value="user_info")
public class User {
//	extends SuperEntity<User>
//	@Id
	private int id;
	
	private String name;
	
	private int age;
	
	@TableField(exist=false)
	private Order order;
	
	@TableLogic
	private int isDeleted;
	@TableField(value="name")
	private String test;
	
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
	
	@Override
	public String toString() {
		return "User [id="+id+",name=" + name + ", age=" + age + "]";
	}

	public String getTest() {
		return test;
	}

	public void setTest(String test) {
		this.test = test;
	}

	public int getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
	}
	
}
