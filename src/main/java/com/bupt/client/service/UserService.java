package com.bupt.client.service;

import java.util.Set;

import com.bupt.client.entity.User;

public interface UserService {
	
	/**
	 * 根据用户名查找用户
	 * @param username
	 * @return
	 */
	User findUser(String username);
	
	/**
	 * 根据用户名查找角色
	 * @param username
	 * @return
	 */
	Set<String> findStringRoles(String username);
	
	/**
	 * 根据用户名查找权限
	 * @param username
	 * @return
	 */
	Set<String> findStringPermissions(String username);
}
