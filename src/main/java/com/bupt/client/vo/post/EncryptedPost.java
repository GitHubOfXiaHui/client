package com.bupt.client.vo.post;

import com.bupt.client.vo.EncryptedObject;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class EncryptedPost implements EncryptedObject {

	private static final long serialVersionUID = -4980784300120097832L;
	
	@JsonInclude(Include.NON_NULL)
	private Long id;
	
	private String data;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "EncryptedPost [id=" + id + ", data=" + data + "]";
	}
	
}
