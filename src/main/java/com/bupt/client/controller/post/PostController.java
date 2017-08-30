package com.bupt.client.controller.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bupt.client.service.post.PostService;
import com.bupt.client.utils.AjaxObject;
import com.bupt.client.vo.post.Post;
import com.bupt.clientsdk.dto.post.PostCreateResDTO;

@Controller
@RequestMapping("/post")
public class PostController {
	
	@Autowired
	private PostService postService;
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String create() {
		return CREATE;
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public @ResponseBody String create(Post post) throws Exception {
		PostCreateResDTO response = postService.createPost(post);
		if (response.isSuccess()) {
			return AjaxObject.newOk("发帖成功：" + response.getMsg()).toString();
		} else {
			return AjaxObject.newError("发帖失败：" + response.getMsg()).toString();
		}
	}

	@RequestMapping("/list")
	public String list(Model model) {
		
		return LIST;
	}
	
	private static final String CREATE = "post/create";
	private static final String LIST = "post/list";
}
