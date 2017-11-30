package com.bupt.client.service.security.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bupt.client.dao.security.PermissionDao;
import com.bupt.client.entity.security.Permission;
import com.bupt.client.service.security.PermissionService;

@Service("permissionService")
@Transactional(readOnly = true)
public class PermissionServiceImpl implements PermissionService {
	
	@Autowired
	private PermissionDao permissionDao;

	@Override
	public List<Permission> findAllPermissions() {
		return permissionDao.findAll();
	}

}
