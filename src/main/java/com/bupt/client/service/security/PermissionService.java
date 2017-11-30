package com.bupt.client.service.security;

import java.util.List;

import com.bupt.client.entity.security.Permission;

public interface PermissionService {

	List<Permission> findAllPermissions();

}
