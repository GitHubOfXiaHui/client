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
		Set<KeywordDTO> keywords = seg(post, KeyEnum.POST);
		return new PostCreateReqDTO(new PostDTO(text), keywords);
	}

	private Set<KeywordDTO> seg(Post post, KeyEnum key) throws Exception {
		List<Word> words = SegmenterUtils.seg(post.getTitle(), post.getContent());
		Set<KeywordDTO> keywords = new HashSet<>();
		for (Word word : words) {
			String keyword = cipher.encrypt(word.getText(), KeyEnum.POST);
			keywords.add(new KeywordDTO(keyword));
		}
		return keywords;
	}
	
	@Override
	public PostFindRes findPosts(String keyword, DWZPage page) {
		PostFindReqDTO request;
		try {
			request = new PostFindReqDTO(seg(keyword, KeyEnum.POST), page);
		} catch (Exception e) {
			return new PostFindRes(new ArrayList<>(), page);
		}
		
		PostFindResDTO response = rest.postForObject(restful.getProperty("url.post.find"), request, PostFindResDTO.class);
		
		List<Post> posts;
		try {
			posts = getPosts(response);
		} catch (Exception e) {
			return new PostFindRes(new ArrayList<>(), page);
		}
		
		return new PostFindRes(posts, response.getPage());
	}

	private List<Post> getPosts(PostFindResDTO response) throws Exception {
		List<Post> posts = new ArrayList<>();
		if (response.isSuccess() && response.getPosts() != null) {
			List<PostDTO> postDTOs = response.getPosts();
			for (PostDTO postDTO : postDTOs) {
				posts.add(buildPost(postDTO));
			}
		}
		return posts;
	}
	
	private Post buildPost(PostDTO postDTO) throws Exception {
		Post post = cipher.decrypt(Post.class, postDTO.getText(), KeyEnum.POST);
		post.setId(postDTO.getId());
		return post;
	}

	private Set<String> seg(String keyword, KeyEnum key) throws Exception {
		List<Word> words = SegmenterUtils.seg(keyword);
		Set<String> keywords = new HashSet<>();
		for (Word word : words) {
			keywords.add(cipher.encrypt(word.getText(), KeyEnum.POST));
		}
		return keywords;
	}

	@Override
	public PostDeleteResDTO deletePost(Long id) {
		return rest.postForObject(restful.getProperty("url.post.delete"), new PostDeleteReqDTO(id), PostDeleteResDTO.class);
	}

	@Override
	public PostGetRes findPost(Long id) throws Exception {
		PostGetResDTO response = rest.postForObject(restful.getProperty("url.post.get"), new PostGetReqDTO(id), PostGetResDTO.class);
		return new PostGetRes(buildPost(response.getPost()));
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
