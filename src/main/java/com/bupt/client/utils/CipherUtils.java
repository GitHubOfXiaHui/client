package com.bupt.client.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.Properties;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.apache.commons.codec.binary.Base64;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import com.bupt.client.vo.EncryptedObject;
import com.bupt.client.vo.PlainObject;
import com.bupt.client.vo.post.EncryptedPost;
import com.bupt.client.vo.post.Post;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class CipherUtils implements CipherConstants {

	private Key mainPrivateKey;

	private Properties tableSecretKeys;

	private Cipher mainCipher;
	private Cipher tableCipher;

	public CipherUtils() throws IOException, ClassNotFoundException, NoSuchAlgorithmException, NoSuchPaddingException {
		try (ObjectInputStream ois = new ObjectInputStream(new ClassPathResource(MAIN_PRIVATE_KEY).getInputStream())) {
			this.mainPrivateKey = (Key) ois.readObject();
		}
		this.tableSecretKeys = new Properties();
		try (InputStream is = new ClassPathResource(TABLE_SECRET_KEYS).getInputStream()) {
			this.tableSecretKeys.load(is);
		}
		this.mainCipher = Cipher.getInstance(MAIN_ALGORITH_NAME);
		this.tableCipher = Cipher.getInstance(TABLE_ALGORITH_NAME);
	}

	public String encryptPostKey(String str) throws InvalidKeyException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException {
		return encrypt(str, POST_KEY);
	}

	private String encrypt(String str, String key) throws InvalidKeyException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException {
		// 解密表密钥
		Key tableKey = getTableKey(key);
		tableCipher.init(Cipher.ENCRYPT_MODE, tableKey);
		return Base64.encodeBase64String(tableCipher.doFinal(str.getBytes()));
	}

	public EncryptedPost encrypt(Post post) throws JsonProcessingException, InvalidKeyException,
			NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException {
		EncryptedPost encryptedPost = new EncryptedPost();
		encrypt(encryptedPost, post, POST_KEY);
		return encryptedPost;
	}

	public Post decrypt(EncryptedPost encryptedPost) throws InvalidKeyException, NoSuchAlgorithmException,
			IllegalBlockSizeException, BadPaddingException, JsonParseException, JsonMappingException, IOException {
		return (Post) decrypt(Post.class, encryptedPost, POST_KEY);
	}

	private void encrypt(EncryptedObject secret, PlainObject plain, String key) throws JsonProcessingException,
			InvalidKeyException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException {
		// 解密表密钥
		Key tableKey = getTableKey(key);
		// 将数据序列化为JSON
		ObjectMapper mapper = new ObjectMapper();
		byte[] json = mapper.writeValueAsBytes(plain);
		// 加密
		tableCipher.init(Cipher.ENCRYPT_MODE, tableKey);
		String data = Base64.encodeBase64String(tableCipher.doFinal(json));
		secret.setData(data);
		// 设置id
		secret.setId(plain.getId());
	}

	private PlainObject decrypt(Class<? extends PlainObject> plainClazz, EncryptedObject secret, String key)
			throws InvalidKeyException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException,
			JsonParseException, JsonMappingException, IOException {
		// 解密表密钥
		Key tableKey = getTableKey(key);
		// 解密
		tableCipher.init(Cipher.DECRYPT_MODE, tableKey);
		byte[] json = tableCipher.doFinal(Base64.decodeBase64(secret.getData()));
		// 反序列化JSON字节数组
		ObjectMapper mapper = new ObjectMapper();
		PlainObject plain = mapper.readValue(json, plainClazz);
		// 设置id
		plain.setId(secret.getId());

		return plain;
	}

	private Key getTableKey(String key) throws InvalidKeyException, NoSuchAlgorithmException {
		mainCipher.init(Cipher.UNWRAP_MODE, mainPrivateKey);
		Key tableKey = mainCipher.unwrap(Base64.decodeBase64(tableSecretKeys.getProperty(key)), TABLE_ALGORITH_NAME,
				Cipher.SECRET_KEY);
		return tableKey;
	}

	public static void main(String[] args)
			throws InvalidKeyException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException,
			ClassNotFoundException, NoSuchPaddingException, IOException {
		CipherUtils cipher = new CipherUtils();

		Post post = new Post();
		post.setId(1L);
		post.setTitle("翻来覆去睡不着");
		post.setContent("失眠好难受呀！");
		post.setAuthor("lalalalala");
		Calendar calendar = Calendar.getInstance();
		calendar.set(2017, 4, 26, 0, 55, 32);
		post.setCreateTime(calendar.getTimeInMillis());

		EncryptedPost encryptedPost = cipher.encrypt(post);
		System.out.println(encryptedPost);
		System.out.println(cipher.decrypt(encryptedPost));
	}

}
