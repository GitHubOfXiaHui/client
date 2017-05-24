package com.bupt.client.entity.security;

public enum RoleEnum {

	ADMIN((byte)1, "admin", "管理员"),
	NORMAL((byte)2, "normal", "普通用户");
	
	private byte code;
	private String role;
	private String description;
	
	private RoleEnum(byte code, String role, String description) {
		this.code = code;
		this.role = role;
		this.description = description;
	}
	
	public static RoleEnum getRoleEnum(byte code) {
		for (RoleEnum roleEnum : RoleEnum.values()) {
			if (roleEnum.getCode() == code) {
				return roleEnum;
			}
		}
		throw new RuntimeException("未知角色标识符");
	}

	public byte getCode() {
		return code;
	}

	public void setCode(byte code) {
		this.code = code;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
