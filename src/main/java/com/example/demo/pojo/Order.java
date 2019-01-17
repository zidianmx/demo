/**
 * 
 */
package com.example.demo.pojo;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author 
 * 
 */
@JsonIgnoreProperties(value = {"handler"})
@TableName(value="orders")
public class Order {
	private int id;
	private int userId;
	private int number;
	private User user;
	//逻辑删除字段注解
	@TableLogic
	private int isDeleted;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
}
