package com.bupt.client.service.post.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.apdplat.word.segmentation.Word;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bupt.client.cipher.CipherUtils;
import com.bupt.client.cipher.KeyEnum;
import com.bupt.client.segmenter.SegmenterUtils;
import com.bupt.client.service.post.PostService;
import com.bupt.client.vo.post.Post;
import com.bupt.client.vo.post.PostFindRes;
import com.bupt.client.vo.post.PostGetRes;
import com.bupt.clientsdk.dto.BaseResponseDTO;
import com.bupt.clientsdk.dto.KeywordDTO;
import com.bupt.clientsdk.dto.enumeration.ResponseEnum;
import com.bupt.clientsdk.dto.page.DWZPage;
import com.bupt.clientsdk.dto.post.PostCreateReqDTO;
import com.bupt.clientsdk.dto.post.PostCreateResDTO;
import com.bupt.clientsdk.dto.post.PostDTO;
import com.bupt.clientsdk.dto.post.PostDeleteReqDTO;
import com.bupt.clientsdk.dto.post.PostDeleteResDTO;
import com.bupt.clientsdk.dto.post.PostFindReqDTO;
import com.bupt.clientsdk.dto.post.PostFindResDTO;
import com.bupt.clientsdk.dto.post.PostGetReqDTO;
import com.bupt.clientsdk.dto.post.PostGetResDTO;
import com.bupt.clientsdk.dto.post.PostUpdateReqDTO;
import com.bupt.clientsdk.dto.post.PostUpdateResDTO;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	@Qualifier("RESTful")
	private Properties restful;

	@Autowired
	private CipherUtils cipher;

	@Autowired
	private RestTemplate rest;

	@Override
	public PostCreateResDTO createPost(Post post) throws Exception {
		PostCreateReqDTO request;
		try {
			request = buildPostCreateReqDTO(post);
		} catch (Exception e) {
			return BaseResponseDTO.buildResponse(ResponseEnum.ERROR_10, PostCreateResDTO.class);
		}
		return rest.postForObject(restful.getProperty("url.post.create"), request, PostCreateResDTO.class);
	}

	private PostCreateReqDTO buildPostCreateReqDTO(Post post) throws Exception {
		// 加密
		String text = cipher.encrypt(post, KeyEnum.POST);
		// 分词
		Set<KeywordDTO> keywords = seg(post);
		return new PostCreateReqDTO(new PostDTO(text), keywords);
	}

	private Set<KeywordDTO> seg(Post post) throws Exception {
		List<Word> words = SegmenterUtils.seg(post.getTitle(), post.getContent());
		Set<KeywordDTO> keywords = new HashSet<>();
		for (Word word : words) {
			String keyword = cipher.digest(word.getText());
			keywords.add(new KeywordDTO(keyword));
		}
		return keywords;
	}

	@Override
	public PostFindRes findPosts(String keyword, DWZPage page) throws Exception {
		PostFindReqDTO request;
		try {
			request = new PostFindReqDTO(seg(keyword), page);
		} catch (Exception e) {
			return BaseResponseDTO.buildResponse(ResponseEnum.ERROR_10, PostFindRes.class);
		}

		PostFindResDTO response = rest.postForObject(restful.getProperty("url.post.find"), request,
				PostFindResDTO.class);
		return bulidPostFindRes(response);
	}

	private PostFindRes bulidPostFindRes(PostFindResDTO response) throws Exception {
		PostFindRes postFindRes = new PostFindRes();
		postFindRes.setCode(response.getCode());
		postFindRes.setMsg(response.getMsg());
		postFindRes.setPage(response.getPage());
		try {
			List<Post> posts = getPosts(response.getPosts());
			postFindRes.setPosts(posts);
		} catch (Exception e) {
			return BaseResponseDTO.buildResponse(ResponseEnum.ERROR_13, PostFindRes.class);
		}
		return postFindRes;
	}

	private List<Post> getPosts(List<PostDTO> postDTOs) throws Exception {
		List<Post> posts = new ArrayList<>();
		if (postDTOs != null) {
			for (PostDTO postDTO : postDTOs) {
				posts.add(buildPost(postDTO));
			}
		}
		return posts;
	}

	private Post buildPost(PostDTO postDTO) throws Exception {
		if (postDTO == null) {
			return null;
		}
		Post post = cipher.decrypt(Post.class, postDTO.getText(), KeyEnum.POST);
		post.setId(postDTO.getId());
		return post;
	}

	private Set<String> seg(String keyword) throws Exception {
		List<Word> words = SegmenterUtils.seg(keyword);
		Set<String> keywords = new HashSet<>();
		for (Word word : words) {
			keywords.add(cipher.digest(word.getText()));
		}
		return keywords;
	}

	@Override
	public PostDeleteResDTO deletePost(Long id) {
		return rest.postForObject(restful.getProperty("url.post.delete"), new PostDeleteReqDTO(id),
				PostDeleteResDTO.class);
	}

	@Override
	public PostGetRes findPost(Long id) throws Exception {
		PostGetResDTO response = rest.postForObject(restful.getProperty("url.post.get"), new PostGetReqDTO(id),
				PostGetResDTO.class);
		return buildPostGetRes(response);
	}

	private PostGetRes buildPostGetRes(PostGetResDTO response) throws Exception {
		PostGetRes postGetRes = new PostGetRes();
		postGetRes.setCode(response.getCode());
		postGetRes.setMsg(response.getMsg());
		try {
			Post post = buildPost(response.getPost());
			postGetRes.setPost(post);
		} catch (Exception e) {
			return BaseResponseDTO.buildResponse(ResponseEnum.ERROR_13, PostGetRes.class);
		}
		return postGetRes;
	}

	@Override
	public PostUpdateResDTO updatePost(Post post) throws Exception {
		PostUpdateReqDTO request = new PostUpdateReqDTO();
		PostDeleteReqDTO delete = new PostDeleteReqDTO(post.getId());
		PostCreateReqDTO create;
		try {
			create = buildPostCreateReqDTO(post);
		} catch (Exception e) {
			e.printStackTrace();
			return BaseResponseDTO.buildResponse(ResponseEnum.ERROR_10, PostUpdateResDTO.class);
		}
		request.setPostDeleteReq(delete);
		request.setPostCreateReq(create);
		return rest.postForObject(restful.getProperty("url.post.update"), request, PostUpdateResDTO.class);
	}

}
