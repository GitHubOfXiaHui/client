package com.bupt.client.dao.security;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.bupt.client.entity.security.Role;

public interface RoleDao extends JpaRepository<Role, Long> {

	/**
	 * 根据角色编号查找角色
	 * @param code
	 * @return
	 */
	Role findByCode(byte code);

	/**
	 * 根据角色名查找
	 * @param name
	 * @param page
	 * @return
	 */
	Page<Role> findByNameLike(String name, Pageable page);

	/**
	 * 根据角色名查找角色
	 * @param name
	 * @return
	 */
	Role findByName(String name);

	/**
	 * 根据角色值查找角色
	 * @param name
	 * @return
	 */
	Role findByValue(String value);
}
