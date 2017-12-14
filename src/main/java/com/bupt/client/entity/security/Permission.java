package com.bupt.client.entity.security;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.bupt.client.entity.BaseEntity;

@Entity
@Table(name = "sec_permission")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE, region = "com.bupt.client.entity.security")
public class Permission extends BaseEntity {

	private static final long serialVersionUID = -1925402391480046411L;

	/** 权限标识符 */
	@Column(nullable = false, unique = true)
	private byte code;
	
	@Column
	private String name;
	
	@Column
	private String value;

	@ManyToMany(mappedBy = "permissions")
	private Set<Role> roles;

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

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "Permission [code=" + code + ", name=" + name + ", value=" + value + "]";
	}

}
