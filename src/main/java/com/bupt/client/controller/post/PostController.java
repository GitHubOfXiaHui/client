package com.bupt.client.controller.post;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bupt.client.constants.Constants;
import com.bupt.client.entity.security.User;
import com.bupt.client.service.post.PostService;
import com.bupt.client.utils.AjaxObject;
import com.bupt.client.vo.post.Post;
import com.bupt.client.vo.post.PostFindRes;
import com.bupt.clientsdk.dto.page.DWZPage;
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
	public @ResponseBody String create(Post post, HttpSession session) throws Exception {
		User user = (User) session.getAttribute(Constants.CURRENT_USER);
		post.setAuthor(user.getRealname());
		PostCreateResDTO response = postService.createPost(post);
		AjaxObject result;
		if (response.isSuccess()) {
			result = AjaxObject.newOk("发帖成功：" + response.getMsg());
		} else {
			result = AjaxObject.newError("发帖失败：" + response.getMsg());
		}
		return result.setCallbackType("").toString();
	}

	@RequestMapping("/list")
	public String list(@RequestParam(defaultValue = "") String keyword, DWZPage page, Model model) {
		PostFindRes response = postService.findPosts(keyword, page);

		model.addAttribute("keyword", keyword);
		model.addAttribute("posts", response.getPosts());
		model.addAttribute("page", response.getPage());
		return LIST;
	}

	private static final String CREATE = "post/create";
	private static final String LIST = "post/list";
}
