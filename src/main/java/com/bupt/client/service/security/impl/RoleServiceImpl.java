package com.bupt.client.service.security.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bupt.client.dao.security.PermissionDao;
import com.bupt.client.dao.security.RoleDao;
import com.bupt.client.entity.security.Permission;
import com.bupt.client.entity.security.Role;
import com.bupt.client.service.security.RoleService;
import com.bupt.clientsdk.dto.page.DWZPage;
import com.bupt.clientsdk.dto.page.PageUtils;

@Service("roleService")
@Transactional(readOnly = true)
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private PermissionDao permissionDao;

	@Override
	public List<Role> findAllRoles() {
		return roleDao.findAll();
	}

	@Override
	public List<Role> findUsers(String rolename, DWZPage page) {
		Page<Role> roles = roleDao.findByNameLike("%" + rolename + "%", PageUtils.createPageable(page));
		PageUtils.injectPageProperties(page, roles);
		return roles.getContent();
	}

	@Override
	public Role createRole(Role role) {
		return roleDao.save(role);
	}

	@Override
	public void deleteRole(Long id) {
		roleDao.delete(id);
	}

	@Override
	public Role findRoleByCode(Byte code) {
		return code != null ? roleDao.findByCode(code) : null;
	}

	@Override
	public Role findRoleByName(String name) {
		return name != null ? roleDao.findByName(name) : null;
	}

	@Override
	public Role findRoleByValue(String value) {
		return value != null ? roleDao.findByValue(value) : null;
	}

	@Override
	public Role findRole(Long id) {
		return roleDao.findOne(id);
	}

	@Override
	public void updateRole(Long id, Byte[] ps) {
		Role role = roleDao.findOne(id);
		Set<Permission> permissions = new HashSet<>();
		for (Byte p : ps) {
			permissions.add(permissionDao.findByCode(p));
		}
		role.setPermissions(permissions);
		roleDao.save(role);
	}

}
