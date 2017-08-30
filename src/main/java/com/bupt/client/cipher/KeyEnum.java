package com.bupt.client.cipher;

public enum KeyEnum {

	POST("key.post");
	
	private String value;
	
	KeyEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
	
}
