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
		post.setContent("第五期后勤服务座谈会");
		String secret = cipher.encrypt(post, KeyEnum.POST);
		System.out.println("*******" + secret + "*******");
	}
	
	@Test
	public void decryptTest() throws Exception {
		String str = "Ubnrh68QFmhYLLDV9qtw9h9RqpdmLmW10k/BLzd81ny/thr9AC9X0E1ZRtp3wf77CvrS4zUAboQh3VSvbHSy/dpqokcu+NzGjIWR31M4Ove3R9hIU+rYk3ey3SnFVKOveq8+mi2w9tcO1HurK5Pc8A==";
		Post post = cipher.decrypt(Post.class, str, KeyEnum.POST);
		System.out.println("*******" + post.getContent() + "*******");
	}

}
