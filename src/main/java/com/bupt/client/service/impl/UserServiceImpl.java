package com.bupt.client.service.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bupt.client.dao.UserDao;
import com.bupt.client.entity.User;
import com.bupt.client.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;

	@Override
	public User findUser(String username) {
		return userDao.findByUsername(username);
	}

	@Override
	public Set<String> findStringRoles(String username) {
		return null;
	}

	@Override
	public Set<String> findStringPermissions(String username) {
		return null;
	}

}
