package com.bupt.client.entity.security;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.bupt.client.entity.IdLongEntity;

@Entity
@Table(name = "sec_role")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE, region = "com.bupt.client.entity.security")
public class Role extends IdLongEntity {

	private static final long serialVersionUID = -2617312579960892393L;

	/** 角色标识符 */
	@Column(nullable = false, unique = true)
	private byte code;
	
	@Column
	private String name;
	
	@Column
	private String value;

	@ManyToMany(mappedBy = "roles")
	private Set<User> users;

	@ManyToMany
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + code;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Role other = (Role) obj;
		if (code != other.code)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Role [code=" + code + ", name=" + name + ", value=" + value + ", permissions=" + permissions + "]";
	}

}
