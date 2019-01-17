package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;

@Configuration
public class MybatisPlusConfig {
	/**
     * SQL执行效率插件 会以红色字体输出到控制台
     */
	@Bean
    public PerformanceInterceptor  performanceInterceptor(){
		PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
        /*<!-- SQL 执行性能分析，开发环境使用，线上不推荐。 maxTime 指的是 sql 最大执行时长 -->*/
        performanceInterceptor.setMaxTime(1000);
        /*<!--SQL是否格式化 默认false-->*/
//        performanceInterceptor.setFormat(true);
        return performanceInterceptor;
    }
	
	/**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
    /**
     * 逻辑删除
     * @return
     */
    @Bean
    public ISqlInjector sqlInjector() {
        return new LogicSqlInjector();
    }
    
    /**
     * 懒加载时忽略动态代理多余字段报错
     * @return
     */
//    @Bean
//    public ObjectMapper objectMapper() {
//        return new ObjectMapper().disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
//    }
}
