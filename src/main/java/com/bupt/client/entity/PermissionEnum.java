package com.bupt.client.entity;

public enum PermissionEnum {

	POST_CREATE((byte)1, "post:create", "新建"),
	POST_DELETE((byte)2, "post:delete", "删除"),
	POST_EDIT((byte)3, "post:edit", "编辑"),
	POST_VIEW((byte)4, "post:view", "查看");
	
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
