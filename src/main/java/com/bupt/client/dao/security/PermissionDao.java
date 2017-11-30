package com.bupt.client.dao.security;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bupt.client.entity.security.Permission;

public interface PermissionDao extends JpaRepository<Permission, Long> {

	Permission findByCode(byte p);

}
