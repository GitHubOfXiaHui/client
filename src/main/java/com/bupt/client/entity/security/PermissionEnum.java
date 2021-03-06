package com.bupt.client.entity.security;

public enum PermissionEnum {

	POST_CREATE((byte)1, "post:create", "新建帖子"),
	POST_DELETE((byte)2, "post:delete", "删除帖子"),
	POST_EDIT((byte)3, "post:update", "修改帖子"),
	POST_VIEW((byte)4, "post:view", "查看帖子"),
	
	USER_CREATE((byte)5, "user:create", "新建用户"),
	USER_DELETE((byte)6, "user:delete", "删除用户"),
	USER_EDIT((byte)7, "user:changePassword", "修改密码"),
	USER_VIEW((byte)8, "user:view", "查看用户"),
	USER_ROLE_UPDATE((byte)9, "user:role:update", "设置角色"),
	
	ROLE_CREATE((byte)10, "role:create", "创建角色"),
	ROLE_DELETE((byte)11, "role:delete", "删除角色"),
	ROLE_PRIVILEGE_UPDATE((byte)12, "role:privilege:update", "设置角色"),
	ROLE_VIEW((byte)13, "role:view", "查看角色");
	
	private byte code;
	private String permission;
	private String description;
	
	private PermissionEnum(byte code, String permission, String description) {
		this.code = code;
		this.permission = permission;
		this.description = description;
	}
	
	public static PermissionEnum getPermissionEnum(byte code) {
		for (PermissionEnum permissionEnum : PermissionEnum.values()) {
			if (permissionEnum.getCode() == code) {
				return permissionEnum;
			}
		}
		throw new RuntimeException("未知权限标识符");
	}

	public byte getCode() {
		return code;
	}

	public void setCode(byte code) {
		this.code = code;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
