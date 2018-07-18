package com.example.demo.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class TestAsyncImpl implements TestAsync {
	
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private AsnycTask asnycTask;
	
	@Override
	public void execute() {
		asnycTask.a();
		asnycTask.b();
//		System.out.println("调用存储过程");
//		jdbcTemplate.execute(new CallableStatementCreator() {
//            public CallableStatement createCallableStatement(Connection con) throws SQLException {
//                String storedProc = "{call testuser()}";
//                CallableStatement cs = con.prepareCall(storedProc);
//                return cs;
//            }
//        }, new CallableStatementCallback() {
//            public Object doInCallableStatement(CallableStatement cs) throws SQLException, DataAccessException {
//                cs.execute();
//                return null;
//            }
//        });
    }
	
}
