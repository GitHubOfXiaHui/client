package com.bupt.client.controller.main;

import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bupt.client.constants.Constants;
import com.bupt.client.entity.security.User;
import com.bupt.client.service.security.UserService;

@Controller
@RequestMapping("/main")
public class LoginController {
	
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return LOGIN;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(User user, HttpSession session) {
		// 登录认证
		Subject subject = SecurityUtils.getSubject();
		AuthenticationToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
		subject.login(token);
		
		// 登录成功
		User dbUser = userService.findUser(user.getUsername());
		session.setAttribute(Constants.CURRENT_USER, dbUser);
		return "redirect:/main/index";
	}

	@ExceptionHandler(AuthenticationException.class)
	public String handlerFailLogin(Model model) {
		model.addAttribute("error", "用户名/密码错误");
		return LOGIN;
	}
	
	@RequestMapping("/logout")
	public String logout() {
		// 退出
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		return LOGIN;
	}

	private static final String LOGIN = "main/login";
}
