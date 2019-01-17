/**
 * 
 */
package com.example.demo.config;

import java.io.InputStream;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * mybaits测试
 * @author 
 * 
 */
public class CRUDTest {
	
	SqlSession ss = null;
	InputStream inputStream = null;
	public void get() {
		ss.selectList("");
		SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(inputStream);
		ss=ssf.openSession();
	}
	
	
}
