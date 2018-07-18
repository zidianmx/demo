package com.example.demo.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.pojo.User;


public interface UserService extends IService<User>{
	
//	List<User> getUserList();
	
	List<User> getUserList1();
}
