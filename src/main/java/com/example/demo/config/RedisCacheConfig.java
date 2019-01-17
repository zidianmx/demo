package com.example.demo.config;

import java.lang.reflect.Method;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@EnableCaching
public class RedisCacheConfig {
	
	   @Value("${spring.redis.host}")
	    private String host;
	    @Value("${spring.redis.port}")
	    private int port;
	    @Value("${spring.redis.timeout}")
	    private int timeout;
	    
	    /**
	     * spring boot2.0配置 指定使用哪一种缓存
	     * @param connectionFactory
	     * @return
	     */
//	    @Bean
//	    public RedisCacheManager cacheManager(RedisConnectionFactory connectionFactory) {
//	    	return RedisCacheManager.create(connectionFactory);
//	    }
//	    /**
//	     * 2.0以下指定使用哪一种缓存
//	     */
//	    @Bean
//	    public CacheManager cacheManager(RedisTemplate<?, ?> redisTemplate) {
//	        return RedisCacheManager.create(redisTemplate.getConnectionFactory());
//	    }
	    
//	    @Bean
//	    public RedisStandaloneConfiguration redisConnectionFactory() {
//	    	RedisStandaloneConfiguration factory = new RedisStandaloneConfiguration();
//	        factory.setHostName(host);
//	        factory.setPort(port);
//	        factory.setTimeout(timeout); //设置连接超时时间
//	        return factory;
//	    }
	    
	    /**
	     * redis模板，存储关键字是字符串，值是Jdk序列化（字符串）
	     * 为了更改存取值时序列化类型
	     * @Description:
	     * @param factory
	     * @return
	     */
	    @Bean
	    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory) {
	        RedisTemplate<String, String> redisTemplate = new RedisTemplate<String, String>();
	        redisTemplate.setConnectionFactory(factory);
	        RedisSerializer<String> redisSerializer = new StringRedisSerializer();
	        redisTemplate.setKeySerializer(redisSerializer);
	        redisTemplate.setHashKeySerializer(redisSerializer);

	        redisTemplate.setHashValueSerializer(redisSerializer);
	        redisTemplate.setValueSerializer(redisSerializer);
	        return redisTemplate;
	    }
	    
	    /**
	     * 自定义key. 这个可以不用
	     * 此方法将会根据类名+方法名+所有参数的值生成唯一的一个key,即使@Cacheable中的value属性一样，key也会不一样。
	     */
	    @Bean
	    public KeyGenerator keyGenerator() {
	       System.out.println("RedisCacheConfig.keyGenerator()");
	       return new KeyGenerator() {
	           @Override
	           public Object generate(Object o, Method method, Object... objects) {
	              // This will generate a unique key of the class name, the method name
	              //and all method parameters appended.
	              StringBuilder sb = new StringBuilder();
	              sb.append(o.getClass().getName());
	              sb.append(method.getName());
	              for (Object obj : objects) {
	                  sb.append(obj.toString());
	              }
	              System.out.println("keyGenerator=" + sb.toString());
	              return sb.toString();
	           }
	       };
	    }
	
}
