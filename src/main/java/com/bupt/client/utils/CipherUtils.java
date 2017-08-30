package com.bupt.client.utils;

//@Component
public class CipherUtils {

//	private Key mainPrivateKey;
//
//	@Autowired
//	@Qualifier("tableSecretKeys")
//	private Properties tableSecretKeys;
//
//	private Cipher mainCipher;
//	private Cipher tableCipher;
//
//	public CipherUtils() throws IOException, ClassNotFoundException, NoSuchAlgorithmException, NoSuchPaddingException {
//		try (ObjectInputStream ois = new ObjectInputStream(new ClassPathResource(MAIN_PRIVATE_KEY).getInputStream())) {
//			this.mainPrivateKey = (Key) ois.readObject();
//		}
//		this.mainCipher = Cipher.getInstance(RSA);
//		this.tableCipher = Cipher.getInstance(AES);
//	}
//
//	public String encryptPostKeyword(String str)
//			throws InvalidKeyException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException {
//		return encrypt(str, POST_KEY);
//	}
//
//	private String encrypt(String str, String key)
//			throws InvalidKeyException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException {
//		// 解密表密钥
//		Key tableKey = getTableKey(key);
//		tableCipher.init(Cipher.ENCRYPT_MODE, tableKey);
//		return Base64.encodeBase64String(tableCipher.doFinal(str.getBytes()));
//	}
//
////	public EncryptedPost encryptPost(Post post) throws JsonProcessingException, InvalidKeyException,
////			NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException {
////		EncryptedPost encryptedPost = new EncryptedPost();
////		encrypt(encryptedPost, post, POST_KEY);
////		return encryptedPost;
////	}
//
////	public Post decryptPost(EncryptedPost encryptedPost) throws InvalidKeyException, NoSuchAlgorithmException,
////			IllegalBlockSizeException, BadPaddingException, JsonParseException, JsonMappingException, IOException {
////		return (Post) decrypt(Post.class, encryptedPost, POST_KEY);
////	}
//
//	private void encrypt(EncryptedObject secret, PlainObject plain, String key) throws JsonProcessingException,
//			InvalidKeyException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException {
//		// 解密表密钥
//		Key tableKey = getTableKey(key);
//		// 将数据序列化为JSON
//		ObjectMapper mapper = new ObjectMapper();
//		byte[] json = mapper.writeValueAsBytes(plain);
//		// 加密
//		tableCipher.init(Cipher.ENCRYPT_MODE, tableKey);
//		String data = Base64.encodeBase64String(tableCipher.doFinal(json));
//		secret.setData(data);
//		// 设置id
//		secret.setId(plain.getId());
//	}
//
//	private PlainObject decrypt(Class<? extends PlainObject> plainClazz, EncryptedObject secret, String key)
//			throws InvalidKeyException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException,
//			JsonParseException, JsonMappingException, IOException {
//		// 解密表密钥
//		Key tableKey = getTableKey(key);
//		// 解密
//		tableCipher.init(Cipher.DECRYPT_MODE, tableKey);
//		byte[] json = tableCipher.doFinal(Base64.decodeBase64(secret.getData()));
//		// 反序列化JSON字节数组
//		ObjectMapper mapper = new ObjectMapper();
//		PlainObject plain = mapper.readValue(json, plainClazz);
//		// 设置id
//		plain.setId(secret.getId());
//
//		return plain;
//	}
//
//	/**
//	 * 解密表密钥
//	 * 
//	 * @param key
//	 * @return
//	 * @throws InvalidKeyException
//	 * @throws NoSuchAlgorithmException
//	 */
//	private Key getTableKey(String key) throws InvalidKeyException, NoSuchAlgorithmException {
//		mainCipher.init(Cipher.UNWRAP_MODE, mainPrivateKey);
//		return mainCipher.unwrap(Base64.decodeBase64(tableSecretKeys.getProperty(key)), AES,
//				Cipher.SECRET_KEY);
//	}

}
