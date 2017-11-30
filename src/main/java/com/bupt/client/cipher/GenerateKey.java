package com.bupt.client.cipher;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Security;
import java.util.Iterator;
import java.util.Properties;

import javax.crypto.Cipher;

import org.apache.commons.codec.binary.Base64;

import com.sun.crypto.provider.SunJCE;

@SuppressWarnings("restriction")
public class GenerateKey {

	public static void main(String[] args) throws Exception {
		// 生成新的主密钥
		KeyPair keyPair = getNewMainKey();
		PublicKey newPublicKey = keyPair.getPublic();
		PrivateKey newPrivateKey = keyPair.getPrivate();

		// 获取旧的主密钥和会话密钥
		PrivateKey oldMainPrivateKey = getOldMainPrivateKey();
		Properties tableSecretKeys = getOldTableSecretKeys();

		// 依次解密会话密钥，并用新的主密钥重新加密
		Cipher oldCipher = Cipher.getInstance(MAIN_ALGORITH_NAME);
		Cipher newCipher = Cipher.getInstance(MAIN_ALGORITH_NAME);
		Iterator<String> it = tableSecretKeys.stringPropertyNames().iterator();
		while (it.hasNext()) {
			String name = it.next();
			
			// 解密
			oldCipher.init(Cipher.UNWRAP_MODE, oldMainPrivateKey);
			Key sessionKey = oldCipher.unwrap(Base64.decodeBase64(tableSecretKeys.getProperty(name)),
					TABLE_ALGORITH_NAME, Cipher.SECRET_KEY);
			newCipher.init(Cipher.WRAP_MODE, newPublicKey);
			
			// 加密
			String sessionKeyStr = Base64.encodeBase64String(newCipher.wrap(sessionKey));
			tableSecretKeys.put(name, sessionKeyStr);
		}
		
		// 更新会话密钥
		File table = getFile(TABLE_SECRET_KEYS);
		try (OutputStream os = new FileOutputStream(table)) {
			tableSecretKeys.store(os, "更新会话密钥");
		}
		
		// 更新主密钥
		File pub = getFile(MAIN_PUBLIC_KEY);
		pub.delete();
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(pub))) {
			oos.writeObject(newPublicKey);
		}
		File pri = getFile(MAIN_PRIVATE_KEY);
		pri.delete();
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(pri))) {
			oos.writeObject(newPrivateKey);
		}
	}

	private static KeyPair getNewMainKey() throws Exception {
		Security.addProvider(new SunJCE());
		KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(MAIN_ALGORITH_NAME);
		SecureRandom random = new SecureRandom();
		keyPairGen.initialize(KEY_SIZE, random);
		return keyPairGen.generateKeyPair();
	}

	private static Properties getOldTableSecretKeys() throws Exception {
		Properties tableSecretKeys = new Properties();
		File f = getFile(TABLE_SECRET_KEYS);
		try (FileInputStream is = new FileInputStream(f)) {
			tableSecretKeys.load(is);
		}
		return tableSecretKeys;
	}

	private static PrivateKey getOldMainPrivateKey() throws Exception {
		File f = getFile(MAIN_PRIVATE_KEY);
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
			return (PrivateKey) ois.readObject();
		}
	}

	private static File getFile(String filename) {
		File root = new File(ROOT);
		File[] file = root.listFiles((dir, name) -> name.equals(filename));
		return file[0];
	}

	private static final String MAIN_ALGORITH_NAME = "RSA";
	private static final int KEY_SIZE = 1024;
	private static final String TABLE_ALGORITH_NAME = "AES";
	private static final String MAIN_PUBLIC_KEY = "MainPublic.key";
	private static final String MAIN_PRIVATE_KEY = "MainPrivate.key";
	private static final String TABLE_SECRET_KEYS = "TableSecretKeys.properties";
	private static final String ROOT = "D:\\GraduationProject\\client\\src\\main\\resources";

}
