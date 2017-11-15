package com.bupt.client.cipher;

import java.io.ObjectInputStream;
import java.security.Key;
import java.security.MessageDigest;
import java.security.PrivateKey;
import java.util.Properties;

import javax.crypto.Cipher;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class CipherUtils {

	private Cipher mainCipher;
	private Cipher sessionCipher;

	@Autowired
	@Qualifier("tableSecretKeys")
	private Properties tableSecretKeys;

	public CipherUtils() throws Exception {
		mainCipher = Cipher.getInstance(RSA);
		sessionCipher = Cipher.getInstance(AES);
	}

	public String encrypt(Encryptable plain, KeyEnum key) throws Exception {
		// 解密会话密钥
		Key sessionKey = getSessionKey(key);

		// 将数据序列化为JSON
		byte[] json = new ObjectMapper().writeValueAsBytes(plain);

		// 加密
		sessionCipher.init(Cipher.ENCRYPT_MODE, sessionKey);
		return Base64.encodeBase64String(sessionCipher.doFinal(json));
	}

//	public String encrypt(String text, KeyEnum key) throws Exception {
//		// 解密会话密钥
//		Key sessionKey = getSessionKey(key);
//
//		// 加密
//		sessionCipher.init(Cipher.ENCRYPT_MODE, sessionKey);
//		return Base64.encodeBase64String(sessionCipher.doFinal(text.getBytes()));
//	}

	public <T> T decrypt(Class<T> clazz, String secret, KeyEnum key) throws Exception {
		// 解密表密钥
		Key sessionKey = getSessionKey(key);
		
		// 解密
		sessionCipher.init(Cipher.DECRYPT_MODE, sessionKey);
		byte[] json = sessionCipher.doFinal(Base64.decodeBase64(secret));
		
		// 反序列化JSON字节数组
		return new ObjectMapper().readValue(json, clazz);
	}
	
	public String digest(String plain) throws Exception {
		MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
		byte[] hash = messageDigest.digest(plain.getBytes("UTF-8"));
		return Hex.encodeHexString(hash);
	}

	private Key getSessionKey(KeyEnum key) throws Exception {
		PrivateKey mainKey = getMainKey();
		mainCipher.init(Cipher.UNWRAP_MODE, mainKey);
		return mainCipher.unwrap(Base64.decodeBase64(tableSecretKeys.getProperty(key.getValue())), AES,
				Cipher.SECRET_KEY);
	}

	private PrivateKey getMainKey() throws Exception {
		try (ObjectInputStream ois = new ObjectInputStream(new ClassPathResource(MAIN_PRIVATE_KEY).getInputStream())) {
			return (PrivateKey) ois.readObject();
		}
	}

	private static final String RSA = "RSA";
	private static final String AES = "AES";
	private static final String MAIN_PRIVATE_KEY = "MainPrivate.key";

}
