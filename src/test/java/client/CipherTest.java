package client;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.Security;
import java.util.Properties;

import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;

import org.apache.commons.codec.binary.Base64;

import com.bupt.client.utils.CipherConstants;
import com.sun.crypto.provider.SunJCE;

public class CipherTest implements CipherConstants {

	public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, FileNotFoundException, IOException {
		Security.addProvider(new SunJCE());
		KeyPairGenerator pairgen = KeyPairGenerator.getInstance(MAIN_ALGORITH_NAME);
		SecureRandom random = new SecureRandom();
		pairgen.initialize(KEY_SIZE, random);
		KeyPair keyPair = pairgen.generateKeyPair();
		Key mainPublicKey = keyPair.getPublic();
		Key mainPrivateKey = keyPair.getPrivate();
		
		KeyGenerator gen = KeyGenerator.getInstance(TABLE_ALGORITH_NAME);
		gen.init(random);
		Key postKey = gen.generateKey();
		
		Cipher cipher = Cipher.getInstance(MAIN_ALGORITH_NAME);
		cipher.init(Cipher.WRAP_MODE, mainPublicKey);
		String wrappedPostKey = Base64.encodeBase64String(cipher.wrap(postKey));
		
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(MAIN_PUBLIC_KEY))) {
			oos.writeObject(mainPublicKey);
		}
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(MAIN_PRIVATE_KEY))) {
			oos.writeObject(mainPrivateKey);
		}
		Properties tableSecretKeys = new Properties();
		try (OutputStream os = new FileOutputStream(TABLE_SECRET_KEYS)) {
			tableSecretKeys.put(POST_KEY, wrappedPostKey);
			tableSecretKeys.store(os, "表密钥");
		}
		
		System.out.println("SUCCESS!");
	}

	private static final int KEY_SIZE = 1024;

}
