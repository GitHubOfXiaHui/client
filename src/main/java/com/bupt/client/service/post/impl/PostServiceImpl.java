package com.bupt.client.service.post.impl;

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
import com.bupt.clientsdk.dto.BaseResponseDTO;
import com.bupt.clientsdk.dto.KeywordDTO;
import com.bupt.clientsdk.dto.enumeration.ResponseEnum;
import com.bupt.clientsdk.dto.post.PostCreateReqDTO;
import com.bupt.clientsdk.dto.post.PostCreateResDTO;

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
		// TODO Auto-generated method stub
		PostCreateReqDTO request;
		try {
			request = buildPostCreateReqDTO(post);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return BaseResponseDTO.buildResponse(ResponseEnum.ERROR_10, PostCreateReqDTO.class);
		}
		return rest.postForObject(restful.getProperty("url.create"), request, PostCreateResDTO.class);
	}

	private PostCreateReqDTO buildPostCreateReqDTO(Post post) throws Exception {
		// TODO Auto-generated method stub
		// 加密
		String text = cipher.encrypt(post, KeyEnum.POST);
		// 分词
		Set<KeywordDTO> keywords = seg(post, KeyEnum.POST);
		return new PostCreateReqDTO(text, keywords);
	}

	private Set<KeywordDTO> seg(Post post, KeyEnum key) throws Exception {
		// TODO Auto-generated method stub
		List<Word> words = SegmenterUtils.seg(post.getTitle(), post.getContent());
		Set<KeywordDTO> keywords = new HashSet<>();
		for (Word word : words) {
			String keyword = cipher.encrypt(word.getText(), KeyEnum.POST);
			keywords.add(new KeywordDTO(keyword));
		}
		return keywords;
	}

}
