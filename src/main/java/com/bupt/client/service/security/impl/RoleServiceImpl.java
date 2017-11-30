package com.bupt.client.service.security.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bupt.client.dao.security.RoleDao;
import com.bupt.client.entity.security.Role;
import com.bupt.client.service.security.RoleService;

@Service("roleService")
@Transactional(readOnly = true)
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RoleDao roleDao;

	@Override
	public List<Role> findAllRoles() {
		return roleDao.findAll();
	}

}
