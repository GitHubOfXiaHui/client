package client;

import org.apache.shiro.authc.credential.DefaultPasswordService;
import org.apache.shiro.authc.credential.PasswordService;

public class PasswordServiceTest {

	public static void main(String[] args) {
		PasswordService passwordService = new DefaultPasswordService();
		System.out.println(passwordService.encryptPassword("123456"));
	}

}
