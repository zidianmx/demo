package com.example.demo.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.SuperMapper;
import com.example.demo.pojo.User;
@Repository
public interface UserMapper extends SuperMapper<User>{
	
	List<User> getAll();
	
}
