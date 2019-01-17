package com.example.demo.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 * 全局异常拦截
 * @author Administrator
 *
 */
@ControllerAdvice
public class GlobalDefultExceptionHandler {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(GlobalDefultExceptionHandler.class);
	
	//声明要捕获的异常
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public String defultExcepitonHandler(Exception e) {
		if(e instanceof DescribeException) {
			DescribeException MyException = (DescribeException) e;
			return MyException.getCode()+"==="+MyException.getMessage();
		}
		LOGGER.error("[系统异常]",e);
		System.out.println("系统异常"+"-->"+e.getMessage());
	    return ExceptionEnum.UNKNOW_ERROR.getMsg();
	}
}
