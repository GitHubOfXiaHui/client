package com.bupt.client.controller.security;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bupt.client.entity.security.Permission;
import com.bupt.client.entity.security.Role;
import com.bupt.client.exception.MessageException;
import com.bupt.client.service.security.PermissionService;
import com.bupt.client.service.security.RoleService;
import com.bupt.client.utils.AjaxObject;
import com.bupt.clientsdk.dto.page.DWZPage;

@Controller
@RequestMapping("/security/role")
public class RoleController {

	@Autowired
	private RoleService roleService;

	@Autowired
	private PermissionService permissionService;

	@RequiresPermissions("role:create")
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String create() {
		return CREATE;
	}

	@RequiresPermissions("role:create")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public @ResponseBody String create(Role role) {
		validateCreateRole(role);
		// 验证通过，创建角色
		roleService.createRole(role);
		return AjaxObject.newOk("新建角色成功。").toString();
	}

	@RequiresPermissions("role:delete")
	@RequestMapping("/delete/{id}")
	public @ResponseBody String delete(@PathVariable("id") Long id) {
		roleService.deleteRole(id);
		return AjaxObject.newOk("删除角色成功。").setCallbackType("").toString();
	}

	@RequiresPermissions("role:privilege:update")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Long id, Model model) {
		Role role = roleService.findRole(id);
		List<Permission> allPermissions = permissionService.findAllPermissions();

		model.addAttribute("id", role.getId());
		model.addAttribute("permissions", role.getPermissions());
		model.addAttribute("allPermissions", allPermissions);

		return UPDATE;
	}

	@RequiresPermissions("role:privilege:update")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public @ResponseBody String update(Long id,
			@RequestParam(value = "permissions[]", required = false) Byte[] permissions) {
		roleService.updateRole(id, permissions);
		return AjaxObject.newOk("设置权限成功。").toString();
	}

	@RequiresPermissions("role:view")
	@RequestMapping("/list")
	public String list(@RequestParam(defaultValue = "") String rolename, DWZPage page, Model model) {
		List<Role> roles = roleService.findUsers(rolename, page);

		model.addAttribute("roles", roles);
		model.addAttribute("rolename", rolename);
		model.addAttribute("page", page);
		return LIST;
	}

	// 验证能否创建角色
	private void validateCreateRole(Role role) {
		if (roleService.findRoleByCode(role.getCode()) != null) {
			throw new MessageException("角色编号被占用！");
		}
		if (roleService.findRoleByName(role.getName()) != null) {
			throw new MessageException("角色名被占用！");
		}
		if (roleService.findRoleByValue(role.getValue()) != null) {
			throw new MessageException("角色值被占用！");
		}
	}

	private static final String CREATE = "security/role/create";
	private static final String UPDATE = "security/role/update";
	private static final String LIST = "security/role/list";
}
