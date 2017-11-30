package com.bupt.client.service.security;

import java.util.List;

import com.bupt.client.entity.security.Role;
import com.bupt.clientsdk.dto.page.DWZPage;

public interface RoleService {

	List<Role> findAllRoles();

	List<Role> findUsers(String rolename, DWZPage page);

	Role createRole(Role role);

	void deleteRole(Long id);

	Role findRoleByCode(Byte code);

	Role findRoleByName(String name);

	Role findRoleByValue(String value);

	Role findRole(Long id);

	void updateRole(Long id, Byte[] permissions);

}
