package com.example.demo.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations.TypedTuple;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.RedisUtils;
import com.example.demo.mapper.UserMapper;
import com.example.demo.pojo.Order;
import com.example.demo.pojo.User;
import com.example.demo.service.UserService;
@Service
public class UserServiceImpl   implements UserService{
//	extends ServiceImpl<UserMapper, User>
//	@Autowired
//	private UserRepository ur;
//	@Autowired
//	private UserMapper userMapper;
	@Autowired
	RedisUtils redisUtils;
	
	@Autowired
    private RedisTemplate redisTemplate;
	
	@Autowired
	private UserMapper userMapper;
	
	private StringRedisTemplate str;
	
//	public List<User> getUserList(){
//		List<User> list = ur.findAll();
//		return list;
//	}
//	
	public Page<User> getUserList1(){
//		List<User> list = baseMapper.getAll();
		Page<User> page = new Page<User>(1,1);
		List<User> list = userMapper.getAll(page);
		System.out.println("list的size"+list.size());
		page.setRecords(list);
		return page;
	}
	
//	@Cacheable(value="Alluser")
	public Set<TypedTuple<String>> getRedisTest(int id) {
//		User user = new User();
//		user.setName("userTestchange");
//		user.setAge(22);
//		System.out.println("打印语句则没走缓存");
//		redisUtils.set("user", user);
//		redisTemplate.opsForValue().set("userTestchange", "userTest");
//		System.out.println("userTestchange"+redisUtils.get("userTestchange"));
//		str.opsForValue().set("strTest", "strTest");
//		redisUtils.remove("user");
		System.out.println("--------------------kaishi-------------------");
//		redisUtils.zAdd("zset1", "vzset1", 0);
//		redisUtils.zAdd("zset1", "vzset2", 1);
//		redisUtils.zAdd("zset1", "vzset3", 2);
//		System.out.println(redisTemplate.opsForZSet().range("zset1", 0, 2));
//		Set<Object> a = redisTemplate.opsForZSet().range("zset1", 0, -1);
//		System.out.println(a);
//		Iterator<String> a1= a.iterator();
//		while(a1.hasNext()) {
//			System.out.println("a1"+a1.next());
//		}
		//*****rangeByScore******-1有问题
//		System.out.println(redisTemplate.opsForZSet().rangeByScore("zset1", 0, 2));
		Set<TypedTuple<String>> b = redisTemplate.opsForZSet().rangeWithScores("zset1", 0, -1);
//		Iterator<TypedTuple<String>> b1=b.iterator();
//		while(b1.hasNext()) {
//			TypedTuple<String> b2 = b1.next();
//			System.out.println("b2:"+b2.getValue()+",score:"+b2.getScore());
//		}
//		System.out.println(redisTemplate.boundZSetOps("zset1").rangeWithScores(0,-1));
//		Set<String> a = redisTemplate.keys("*");
//		Iterator<String> a1= a.iterator();
//		while(a1.hasNext()) {
//			System.out.println("a1"+a1.next());
//		}
		
		System.out.println("--------------------jieshu-------------------");
//		return baseMapper.getAll();
		return b;
	}

	/**
	 * @return
	 */
	@Override
	@Transactional
	public Object getLazy() {
//		List<Order> order = userMapper.getLazy();
		User user = new User();
		user.setId(4);
		user.setAge(60);
		userMapper.updateById(user);
		user.setId(3);
		user.setAge(60);
		int i = 1/0;
		userMapper.updateById(user);
//		userMapper.deleteById(4);
		return userMapper.selectById(4);
	}
}
