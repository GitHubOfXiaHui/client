package com.bupt.client.service.post;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;

import com.bupt.client.vo.post.EncryptedPostReqVo;
import com.bupt.client.vo.post.Post;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface PostCipherService {

	/**
	 * 预处理
	 * @param post
	 * @return
	 * @throws BadPaddingException 
	 * @throws IllegalBlockSizeException 
	 * @throws NoSuchAlgorithmException 
	 * @throws JsonProcessingException 
	 * @throws InvalidKeyException 
	 */
	EncryptedPostReqVo encrypt(Post post) throws InvalidKeyException, JsonProcessingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException;

}
