package com.example.demo.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.SuperMapper;
import com.example.demo.pojo.Order;
import com.example.demo.pojo.User;
@Repository
public interface UserMapper extends SuperMapper<User>{
	
	List<User> getAll(Page<User> page);
	
	int insertUser(User user);

	/**
	 * @return
	 */
	List<Order> getLazy();
	
//	List<User> selectUserList(Page<User> page);
}
