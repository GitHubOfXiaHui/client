package com.bupt.client.controller.post;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.bupt.client.service.post.PostCipherService;
import com.bupt.client.utils.AjaxObject;
import com.bupt.client.vo.post.EncryptedPost;
import com.bupt.client.vo.post.EncryptedPostReqVo;
import com.bupt.client.vo.post.Post;
import com.fasterxml.jackson.core.JsonProcessingException;

@Controller
@RequestMapping("/post")
public class PostController {
	
	@Autowired
	private PostCipherService postCipherService;
	
	@Autowired
	@Qualifier("RESTful")
	private Properties restful;
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String create() {
		return CREATE;
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public @ResponseBody String create(Post post) throws InvalidKeyException, JsonProcessingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException {
		EncryptedPostReqVo encryptedPostReqVo = postCipherService.encrypt(post);
		RestTemplate rest = new RestTemplate();
		rest.postForObject(restful.getProperty("url.create"), encryptedPostReqVo, EncryptedPost.class);
		return AjaxObject.newOk("发帖成功。").toString();
	}

	@RequestMapping("/list")
	public String list(Model model) {
		
		return LIST;
	}
	
	private static final String CREATE = "post/create";
	private static final String LIST = "post/list";
}
