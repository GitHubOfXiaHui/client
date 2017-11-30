package com.bupt.client.service.security.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.authc.credential.PasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bupt.client.dao.security.RoleDao;
import com.bupt.client.dao.security.UserDao;
import com.bupt.client.entity.security.Permission;
import com.bupt.client.entity.security.Role;
import com.bupt.client.entity.security.RoleEnum;
import com.bupt.client.entity.security.User;
import com.bupt.client.service.security.UserService;
import com.bupt.clientsdk.dto.page.DWZPage;
import com.bupt.clientsdk.dto.page.PageUtils;

@Service("userService")
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private PasswordService passwordService;
	
	@Override
	public User findUser(Long id) {
		return userDao.findOne(id);
	}

	@Override
	public User findUser(String username) {
		return userDao.findByUsername(username);
	}

	@Override
	public Set<String> findStringRoles(String username) {
		User user = userDao.findByUsername(username);
		Set<String> roles = new HashSet<>();
		for (Role role : user.getRoles()) {
			roles.add(role.getRole());
		}
		return roles;
	}

	@Override
	public Set<String> findStringPermissions(String username) {
		User user = userDao.findByUsername(username);
		Set<String> permissions = new HashSet<>();
		for (Role role : user.getRoles()) {
			for (Permission permission : role.getPermissions()) {
				permissions.add(permission.getPermission());
			}
		}
		return permissions;
	}

	@Override
	public List<User> findUsers(String username, DWZPage page) {
		Page<User> users = userDao.findByUsernameLike("%" + username + "%", PageUtils.createPageable(page));
		PageUtils.injectPageProperties(page, users);
		return users.getContent();
	}

	@Override
	public User createUser(User user) {
		user.setPassword(passwordService.encryptPassword(user.getPassword()));
		Set<Role> roles = new HashSet<>();
		roles.add(roleDao.findByCode(RoleEnum.VISITOR.getCode()));
		user.setRoles(roles);
		return userDao.save(user);
	}

	@Transactional
	@Override
	public boolean deleteUser(Long id) {
		User user = userDao.findOne(id);
		if (user.isSupervisor()) {
			return false;
		} else {
			userDao.delete(user);
			return true;
		}
	}

	@Transactional
	@Override
	public void changePassword(String username, String newPassword) {
		User user = userDao.findByUsername(username);
		user.setPassword(passwordService.encryptPassword(newPassword));
	}

	@Transactional
	@Override
	public void updateUser(Long id, Byte[] roles) {
		User user = userDao.findOne(id);
		Set<Role> rs = new HashSet<>();
		for (Byte role : roles) {
			rs.add(roleDao.findByCode(role));
		}
		user.setRoles(rs);
		userDao.save(user);
	}
}
