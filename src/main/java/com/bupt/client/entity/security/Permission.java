package com.bupt.client.entity.security;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.bupt.client.entity.IdLongEntity;

@Entity
@Table(name = "sec_permission")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE, region = "com.bupt.client.entity.security")
public class Permission extends IdLongEntity {

	private static final long serialVersionUID = -1925402391480046411L;

	/** 权限标识符 */
	@Column(nullable = false, unique = true)
	private byte code;

	@ManyToMany(mappedBy = "permissions", cascade = CascadeType.ALL)
	private Set<Role> roles;

	public String getPermission() {
		return PermissionEnum.getPermissionEnum(code).getPermission();
	}

	public String getDescription() {
		return PermissionEnum.getPermissionEnum(code).getDescription();
	}

	public byte getCode() {
		return code;
	}

	public void setCode(byte code) {
		this.code = code;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "Permission{code = " + code + ", permission = " + getPermission() + ", description = " + getDescription()
				+ "}";
	}

}
