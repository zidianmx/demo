package com.example.demo.repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.example.demo.pojo.User;

@Component
public class AsnycTask {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	@Async
	public void a() {
		System.out.println("调用存储过程");
		System.out.println(Thread.currentThread().getName());
		jdbcTemplate.execute(new CallableStatementCreator() {
            public CallableStatement createCallableStatement(Connection con) throws SQLException {
                String storedProc = "{call testuser(?)}";
                CallableStatement cs = con.prepareCall(storedProc);
                cs.registerOutParameter(1, Types.INTEGER);
                return cs;
            }
        }, new CallableStatementCallback() {
            public Object doInCallableStatement(CallableStatement cs) throws SQLException, DataAccessException {
                cs.execute();
                System.out.println(cs.getInt("a1"));
                return null;
            }
        });
	}
	@Async
	public void b() {
		System.out.println(Thread.currentThread().getName());
		logger.info("测试信息");
        logger.error("测试错误");
        /*
         * 尽管同步拦截异常成功但是后续代码不会继续执行
         */
     	User user = null;
        user.getName();
		for(int i=0;i<10;i++) {
			System.out.println("第二个线程"+i);
		}
	}
	
}
