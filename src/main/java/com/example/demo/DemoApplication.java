package com.example.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
/**
 * 
 * @author Administrator
 * 启动类标识spring容器不扫描注册此包下的类，把此包当做根目录扫描子包下的类并注册
 */
@SpringBootApplication
@MapperScan("com.example.demo.mapper")
public class DemoApplication {
	private final static Logger logger = LoggerFactory.getLogger(DemoApplication.class);
	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(DemoApplication.class, args);
		String[] activeProfiles = ctx.getEnvironment().getActiveProfiles();  
	    for (String profile : activeProfiles) {
	    	System.out.println("Spring Boot 使用profile为:"+profile);
	        logger.info("Spring Boot 使用profile为:{}" , profile);  
	    }
	}
}
