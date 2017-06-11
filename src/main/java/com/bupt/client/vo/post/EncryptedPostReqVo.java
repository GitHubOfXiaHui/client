package com.bupt.client.vo.post;

import java.io.Serializable;
import java.util.Set;

public class EncryptedPostReqVo implements Serializable {

	private static final long serialVersionUID = 2340958921069650953L;

	private EncryptedPost encryptedPost;
	
	private Set<String> keywords;

	public EncryptedPost getEncryptedPost() {
		return encryptedPost;
	}

	public void setEncryptedPost(EncryptedPost encryptedPost) {
		this.encryptedPost = encryptedPost;
	}

	public Set<String> getKeywords() {
		return keywords;
	}

	public void setKeywords(Set<String> keywords) {
		this.keywords = keywords;
	}

	@Override
	public String toString() {
		return "EncryptedPostReqVo [encryptedPost=" + encryptedPost + ", keywords=" + keywords + "]";
	}
	
}
