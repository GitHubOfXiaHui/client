package com.bupt.client.vo.post;

import java.util.List;

import com.bupt.clientsdk.dto.BaseResponseDTO;
import com.bupt.clientsdk.dto.page.DWZPage;

public class PostFindRes extends BaseResponseDTO {

	private static final long serialVersionUID = -1369711954251367769L;

	private List<Post> posts;

	private DWZPage page;

	public PostFindRes() {
		super();
	}

	public PostFindRes(List<Post> posts, DWZPage page) {
		super();
		this.posts = posts;
		this.page = page;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public DWZPage getPage() {
		return page;
	}

	public void setPage(DWZPage page) {
		this.page = page;
	}
	
}
