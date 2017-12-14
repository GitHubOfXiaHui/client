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
	public void digestTest() throws Exception {
		String str = "第五期";
		System.out.println("*******" + cipher.digest(str) + "*******");
	}
	
	//@Test
	public void encryptTest() throws Exception {
		Post post = new Post();
		post.setTitle("忘拿坛服了");
		post.setContent("昨晚有点忙，忘记去拿坛服了，怎么办？急，在线等");
		String secret = cipher.encrypt(post, KeyEnum.POST);
		System.out.println("*******" + secret + "*******");
	}
	
	@Test
	public void decryptTest() throws Exception {
		String str = "ttiUxNfILdyVKcoWQEg/kqlm985Pf2sjVbGNvhhANClovMk7ImCe3xK4Zhaj4NRhwXcnn3h5q8BW31UWwSjIXO9lPRSnTzsXCn43NKWSN5xKlx0fLOY4FrN83j5dyQuqk1R+QKDRSzwHPB8VTEOSQx54A017Za5w5wVJxiP+AB09FhAjqiMx0Gt1oKDCTrBJYkJf5ZhrC0ilmNKHPZEGCQ==";
		Post post = cipher.decrypt(Post.class, str, KeyEnum.POST);
		System.out.println("*******" + post + "*******");
	}

}
