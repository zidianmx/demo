package com.example.demo.service;

import java.util.Set;

import org.springframework.data.redis.core.ZSetOperations.TypedTuple;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.pojo.User;


public interface UserService {
//	extends IService<User>
//	List<User> getUserList();
	
	Page<User> getUserList1();

	Set<TypedTuple<String>> getRedisTest(int id);

	/**
	 * @return
	 */
	Object getLazy();
}
