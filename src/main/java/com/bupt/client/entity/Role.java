package com.bupt.client.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "sec_role")
public class Role extends IdLongEntity {

	private static final long serialVersionUID = -2617312579960892393L;

	/** 角色标识符 */
	@Column(nullable = false, unique = true)
	private byte code;

	@ManyToMany(mappedBy = "roles", cascade = CascadeType.ALL)
	private Set<User> users;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "sec_role_permission", joinColumns = @JoinColumn(name = "role_id"), inverseJoinColumns = @JoinColumn(name = "permission_id"))
	private Set<Permission> permissions;

	public String getRole() {
		return RoleEnum.getRoleEnum(code).getRole();
	}

	public String getDescription() {
		return RoleEnum.getRoleEnum(code).getDescription();
	}

	public byte getCode() {
		return code;
	}

	public void setCode(byte code) {
		this.code = code;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public Set<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(Set<Permission> permissions) {
		this.permissions = permissions;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("Role{code = " + code + ", role = " + getRole() + ", description = "
				+ getDescription() + ", permissions = [");
		for (Permission permission : permissions) {
			sb.append(permission.getDescription() + ", ");
		}
		sb.append("]}");
		return sb.toString();
	}

}
