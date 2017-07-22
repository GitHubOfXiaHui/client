package com.bupt.client.service.post.impl;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;

import org.apdplat.word.WordSegmenter;
import org.apdplat.word.segmentation.SegmentationAlgorithm;
import org.apdplat.word.segmentation.Word;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bupt.client.service.post.PostCipherService;
import com.bupt.client.utils.CipherUtils;
import com.bupt.client.vo.Keyword;
import com.bupt.client.vo.post.EncryptedPostReqVo;
import com.bupt.client.vo.post.Post;
import com.fasterxml.jackson.core.JsonProcessingException;

@Service
public class PostCipherServiceImpl implements PostCipherService {

	@Autowired
	private CipherUtils cipherUtils;

	@Override
	public EncryptedPostReqVo encrypt(Post post) throws InvalidKeyException, JsonProcessingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException {
		EncryptedPostReqVo encryptedPostReqVo = new EncryptedPostReqVo();
		// 加密后的帖子
		encryptedPostReqVo.setEncryptedPost(cipherUtils.encryptPost(post));
		// 加密后帖子关键词
		List<Word> words = WordSegmenter.seg(post.getTitle() + " " + post.getContent(), SegmentationAlgorithm.MaxNgramScore);
		Set<Keyword> keywords = new HashSet<>();
		for (Word word : words) {
			Keyword keyword = new Keyword(cipherUtils.encryptPostKeyword(word.getText()), 0.0f/* word.getFrequency() * word.getWeight() */);
			keywords.add(keyword);
		}
		encryptedPostReqVo.setKeywords(keywords);
		return encryptedPostReqVo;
	}
	
}
