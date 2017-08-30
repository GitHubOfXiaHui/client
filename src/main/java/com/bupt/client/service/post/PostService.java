package com.bupt.client.service.post;

import com.bupt.client.vo.post.Post;
import com.bupt.clientsdk.dto.post.PostCreateResDTO;

public interface PostService {

	PostCreateResDTO createPost(Post post) throws Exception;
	
}
