package com.bupt.client.vo.post;

import com.bupt.clientsdk.dto.BaseResponseDTO;

public class PostGetRes extends BaseResponseDTO {

	private static final long serialVersionUID = 6291106947541035147L;
	
	private Post post;

	public PostGetRes() {
		super();
	}

	public PostGetRes(Post post) {
		super();
		this.post = post;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}
	
}
