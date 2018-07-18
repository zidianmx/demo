package com.example.demo;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JumpController {
	
	/**
	 * 重定向方式：1.@RestController返回的json形式无法直接跳转，方法中加入HttpServletResponse使用response.sendRedirect("/hello")返回值void
	 * 2.@Controller注解与springMVC跳转方式相同"redirect:/hello"
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/")
	public void jump(HttpServletResponse response) throws IOException {
		
//		return "redirect:/hello";
		response.sendRedirect("/hello");
	}
	
}
