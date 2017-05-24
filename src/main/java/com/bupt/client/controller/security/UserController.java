package com.bupt.client.controller.security;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bupt.client.entity.security.User;
import com.bupt.client.service.security.UserService;
import com.bupt.client.utils.AjaxObject;
import com.bupt.client.utils.DWZPage;

@Controller
@RequestMapping("/security/user")
public class UserController {

	@Autowired
	private UserService userService;

	@RequiresPermissions("user:create")
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String create() {
		return CREATE;
	}

	@RequiresPermissions("user:create")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public @ResponseBody String create(User user, String confirm, boolean isAdmin) {
		if (user == null || !validatePassword(user.getPassword(), confirm)) {
			throw new RuntimeException("两次输入密码不一致！");
		}
		try {
			userService.createUser(user, isAdmin);
		} catch (Exception e) {
			throw new RuntimeException("系统忙，请稍后再试。");
		}

		return AjaxObject.newOk("新建用户成功。").toString();
	}
	
	@RequiresPermissions("user:delete")
	@RequestMapping("/delete")
	public @ResponseBody String delete(Long[] ids) {
		try {
			userService.deleteUsers(ids);
		} catch (Exception e) {
			throw new RuntimeException("系统忙，请稍后再试。");
		}
		return AjaxObject.newOk("删除用户成功。").toString();
	}
	
	@RequiresPermissions("user:changePassword")
	@RequestMapping(value = "/changePassword", method = RequestMethod.GET)
	public String changePassword() {
		return CHANGE_PASSWORD;
	}
	
	@RequiresPermissions("user:changePassword")
	@RequestMapping(value = "/changePassword", method = RequestMethod.POST)
	public @ResponseBody String changePassword(String oldPassword, String newPassword, String confirm) {
		Subject subject = SecurityUtils.getSubject();
		String username = (String) subject.getPrincipal();
		User user = null;
		try {
			user = userService.findUser(username);
		} catch (Exception e) {
			throw new RuntimeException("用户名不存在！");
		}
		
		
		return AjaxObject.newOk("修改密码成功。").toString();
	}

	@RequiresPermissions("user:view")
	@RequestMapping("/list")
	public String list(@RequestParam(defaultValue = "") String username, DWZPage page, Model model) {
		List<User> users = userService.findUsers(username, page);

		model.addAttribute("users", users);
		model.addAttribute("username", username);
		model.addAttribute("page", page);
		return LIST;
	}

	@ExceptionHandler(RuntimeException.class)
	public @ResponseBody String handlerException(RuntimeException e) {
		return AjaxObject.newError(e.getMessage()).toString();
	}


	// 验证密码是否一致
	private boolean validatePassword(String password, String confirm) {
		return password != null && confirm != null && password.equals(confirm);
	}
	
	private static final String CREATE = "security/user/create";
	private static final String CHANGE_PASSWORD = "security/user/changePassword";
	private static final String LIST = "security/user/list";
}
