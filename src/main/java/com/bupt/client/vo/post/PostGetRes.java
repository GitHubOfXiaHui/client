package com.bupt.client.vo.post;

public class PostGetRes {

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
