package com.bupt.client.entity.security;

public enum RoleEnum {

	ADMIN((byte)1, "admin", "管理员"),
	NORMAL((byte)2, "normal", "普通用户"),
	VISITOR((byte)3, "visitor", "访客"),
	
	POST_CREATE((byte)4, "post:create", "新建帖子"),
	POST_DELETE((byte)5, "post:delete", "删除帖子"),
	POST_EDIT((byte)6, "post:update", "修改帖子"),
	POST_VIEW((byte)7, "post:view", "查看帖子"),
	
	USER_CREATE((byte)8, "user:create", "新建用户"),
	USER_DELETE((byte)9, "user:delete", "删除用户"),
	USER_EDIT((byte)10, "user:changePassword", "修改密码"),
	USER_VIEW((byte)11, "user:view", "查看用户"),
	
	PRIVILEGES_UPDATE((byte)12, "privileges:update", "设置权限");
	
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
