package com.bupt.client.service.post;

import com.bupt.client.vo.post.Post;
import com.bupt.client.vo.post.PostFindRes;
import com.bupt.client.vo.post.PostGetRes;
import com.bupt.clientsdk.dto.page.DWZPage;
import com.bupt.clientsdk.dto.post.PostCreateResDTO;
import com.bupt.clientsdk.dto.post.PostDeleteResDTO;
import com.bupt.clientsdk.dto.post.PostUpdateResDTO;

public interface PostService {

	PostCreateResDTO createPost(Post post) throws Exception;

	PostFindRes findPosts(String keyword, DWZPage page) throws Exception;

	PostDeleteResDTO deletePost(Long id);

	PostGetRes findPost(Long id) throws Exception;

	PostUpdateResDTO updatePost(Post post) throws Exception;
	
}
