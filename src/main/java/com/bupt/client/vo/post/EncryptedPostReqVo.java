package com.bupt.client.vo.post;

import java.io.Serializable;
import java.util.Set;

import com.bupt.client.vo.Keyword;

public class EncryptedPostReqVo implements Serializable {

	private static final long serialVersionUID = 2340958921069650953L;

	private EncryptedPost encryptedPost;
	
	private Set<Keyword> keywords;

	public EncryptedPost getEncryptedPost() {
		return encryptedPost;
	}

	public void setEncryptedPost(EncryptedPost encryptedPost) {
		this.encryptedPost = encryptedPost;
	}

	public Set<Keyword> getKeywords() {
		return keywords;
	}

	public void setKeywords(Set<Keyword> keywords) {
		this.keywords = keywords;
	}

	@Override
	public String toString() {
		return "EncryptedPostReqVo [encryptedPost=" + encryptedPost + ", keywords=" + keywords + "]";
	}
	
}
