package com.zc.cris.shiro.handlers;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("shiro")
public class ShiroHandler {

	@RequestMapping("login")
	public String login(@RequestParam("name") String name, @RequestParam("password") String password) {
		Subject subject = SecurityUtils.getSubject();
		
		// 如果当前用户没有被验证过，那么就进行验证
		if(!subject.isAuthenticated()) {
			// 将用户的名字和密码封装成 UsernamePasswordToken 对象
			UsernamePasswordToken token = new UsernamePasswordToken(name, password);
			System.out.println(password);
			// rememberme
			token.setRememberMe(true);
			try {
				
				System.out.println(token.hashCode());
				// 开始认证
				subject.login(token);
				
			} catch (Exception e) {
				System.out.println("登陆认证失败："+e.getMessage());
			}
		}
		
		
		return "redirect:/list.jsp";
	}
}
