package com.bupt.client.vo;

import java.io.Serializable;

public interface EncryptedObject extends Serializable {
	
	Long getId();
	void setId(Long id);

	String getData();
	void setData(String data);
	
}
