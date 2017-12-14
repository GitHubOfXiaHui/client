package com.bupt.client.controller.post;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bupt.client.constants.Constants;
import com.bupt.client.entity.security.User;
import com.bupt.client.exception.MessageException;
import com.bupt.client.service.post.PostService;
import com.bupt.client.utils.AjaxObject;
import com.bupt.client.vo.post.Post;
import com.bupt.client.vo.post.PostFindRes;
import com.bupt.client.vo.post.PostGetRes;
import com.bupt.clientsdk.dto.page.DWZPage;
import com.bupt.clientsdk.dto.post.PostCreateResDTO;
import com.bupt.clientsdk.dto.post.PostDeleteResDTO;
import com.bupt.clientsdk.dto.post.PostUpdateResDTO;

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
	public @ResponseBody String create(Post post, HttpSession session) throws Exception {
		User user = (User) session.getAttribute(Constants.CURRENT_USER);
		post.setAuthor(user.getNickname());
		PostCreateResDTO response = postService.createPost(post);
		AjaxObject result;
		if (response.isSuccess()) {
			result = AjaxObject.newOk("发帖成功：" + response.getMsg());
		} else {
			result = AjaxObject.newError("发帖失败：" + response.getMsg());
		}
		return result.toString();
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	public @ResponseBody String delete(@PathVariable("id") Long id) {
		PostDeleteResDTO response = postService.deletePost(id);
		AjaxObject result;
		if (response.isSuccess()) {
			result = AjaxObject.newOk("删帖成功：" + response.getMsg());
		} else {
			result = AjaxObject.newError("删帖失败：" + response.getMsg());
		}
		return result.setCallbackType("").toString();
	}

	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Long id, Model model) throws Exception {
		PostGetRes response = postService.findPost(id);
		if (!response.isSuccess()) {
			throw new MessageException(response.getMsg());
		}
		model.addAttribute("post", response.getPost());
		return UPDATE;
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public @ResponseBody String update(Post post) throws Exception {
		PostUpdateResDTO response = postService.updatePost(post);
		AjaxObject result;
		if (response.isSuccess()) {
			result = AjaxObject.newOk("帖子更新成功：" + response.getMsg());
		} else {
			result = AjaxObject.newError("帖子更新失败：" + response.getMsg());
		}
		return result.toString();
	}

	@RequestMapping("/list")
	public String list(@RequestParam(defaultValue = "") String keyword, DWZPage page, Model model) throws Exception {
		PostFindRes response = postService.findPosts(keyword, page);
		if (!response.isSuccess()) {
			throw new MessageException(response.getMsg());
		}
		model.addAttribute("keyword", keyword);
		model.addAttribute("posts", response.getPosts());
		model.addAttribute("page", response.getPage());
		return LIST;
	}

	private static final String CREATE = "post/create";
	private static final String UPDATE = "post/update";
	private static final String LIST = "post/list";
}
