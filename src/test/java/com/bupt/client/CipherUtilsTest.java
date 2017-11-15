package com.bupt.client;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bupt.client.cipher.CipherUtils;
import com.bupt.client.cipher.KeyEnum;
import com.bupt.client.vo.post.Post;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:applicationContext.xml", "classpath:applicationContext-mvc.xml",
		"classpath:applicationContext-jpa.xml", "classpath:applicationContext-shiro.xml" })
public class CipherUtilsTest {

	@Autowired
	private CipherUtils cipher;

	//@Test
	public void postTest() throws Exception {
		Post post = new Post();
		post.setTitle("whatever");
		post.setContent("say something.");
		String secret = cipher.encrypt(post, KeyEnum.POST);
		Post post2 = cipher.decrypt(Post.class, secret, KeyEnum.POST);
		System.out.println("Post: " + post);
		System.out.println("Post2: " + post2);
	}
	
	//@Test
	public void digestTest() throws Exception {
		String str = "波风更夜";
		System.out.println("*******" + cipher.digest(str) + "*******");
	}
	
	@Test
	public void encryptTest() throws Exception {
		Post post = new Post();
		post.setTitle("个人信息");
		post.setContent("用户名：夏辉，昵称：波风更夜。");
		String secret = cipher.encrypt(post, KeyEnum.POST);
		System.out.println("*******" + secret + "*******");
	}

}
