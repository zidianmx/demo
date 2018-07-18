package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.mapper.UserMapper;
import com.example.demo.pojo.User;
import com.example.demo.service.UserService;
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>  implements UserService{
	
//	@Autowired
//	private UserRepository ur;
//	@Autowired
//	private UserMapper userMapper;
	
//	public List<User> getUserList(){
//		List<User> list = ur.findAll();
//		return list;
//	}
//	
	public List<User> getUserList1(){
		List<User> list = baseMapper.getAll();
		return list;
	}
}
