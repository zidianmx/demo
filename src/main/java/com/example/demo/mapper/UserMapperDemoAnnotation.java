package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.example.demo.pojo.User;

public interface UserMapperDemoAnnotation {
	
	@Select("select * from user")
	@Results({
		@Result(property="name",column="name"),
		@Result(property="age",column="age")
	})
	List<User> getAll();
	
}
