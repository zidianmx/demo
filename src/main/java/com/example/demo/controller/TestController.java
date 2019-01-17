/**
 * 
 */
package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.UserService;

/**
 * @author 
 * 
 */
@RestController
@RequestMapping("/test")
public class TestController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping("/lazy")
	public Object testMybatisLazy() {
//		SparkConf conf = new SparkConf().setAppName("esRDDtest");
//        conf.setMaster("spark://192.168.4.254:7077");
//        conf.set("es.nodes", "192.168.4.254");
//        conf.set("es.port", "81");
//        SparkContext sc = new SparkContext(conf);
//        RDD eslogs = EsSpark.esRDD(sc,"megacorp/employee");
//        System.out.println(eslogs.count());
        
		Object ob = userService.getLazy();
		return ob;
	}
	
}
