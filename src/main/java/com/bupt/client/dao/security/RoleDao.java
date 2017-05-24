package com.bupt.client.dao.security;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bupt.client.entity.security.Role;

public interface RoleDao extends JpaRepository<Role, Long> {

	/**
	 * 根据角色编号查找角色
	 * @param code
	 * @return
	 */
	Role findByCode(byte code);
}
