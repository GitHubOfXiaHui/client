package com.bupt.client.service.security;

import java.util.List;
import java.util.Set;

import com.bupt.client.entity.security.User;
import com.bupt.clientsdk.dto.page.DWZPage;

public interface UserService {
	
	/**
	 * 根据id查找用户
	 * @param id
	 * @return
	 */
	User findUser(Long id);
	
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

	/**
	 * 根据用户名模糊查询
	 * @param username
	 * @param page
	 * @return
	 */
	List<User> findUsers(String username, DWZPage page);

	/**
	 * 保存用户
	 * @param user
	 * @return
	 */
	User createUser(User user);

	/**
	 * 删除用户
	 * @param ids
	 */
	boolean deleteUser(Long id);

	/**
	 * 修改密码
	 * @param username
	 * @param newPassword 
	 */
	void changePassword(String username, String newPassword);

	/**
	 * 设置角色
	 * @param id
	 * @param roles
	 */
	void updateUser(Long id, Byte[] roles);
}
