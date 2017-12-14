package com.bupt.client;

import java.util.List;

import org.apdplat.word.segmentation.Word;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bupt.client.segmenter.SegmenterUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:applicationContext.xml", "classpath:applicationContext-mvc.xml",
		"classpath:applicationContext-jpa.xml", "classpath:applicationContext-shiro.xml" })
public class SegmenterUtilsTest {

	@Test
	public void segTest() {
		String str = "第五期后勤服务座谈会";
		List<Word> words = SegmenterUtils.seg(str);
		for (Word word : words) {
			System.out.println("*******" + word.getText() + "*******");
		}
	}
	
}
