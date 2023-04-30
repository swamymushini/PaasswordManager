package com.example.pwdManager.repo;

import java.util.Base64;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pwdManager.Model.Account;
import com.example.pwdManager.Model.User;
import com.example.pwdManager.Model.Website;
import com.example.pwdManager.errors.ResourceNotFoundException;

@Service
public class AccountService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private WebsiteRepository websiteRepository;

	private final String secretKey = "my-secret-key";

//	public Account addAccount(String website, String url, String username, String password, String secretKey,
//			String token) {
//		User user = userRepository.findByToken(token);
//		if (user == null) {
//			throw new UnauthorizedException("Invalid token");
//		}
//		Website websiteObj = websiteRepository.findByWebsite(website);
//		if (websiteObj == null) {
//			throw new ResourceNotFoundException("Website not found");
//		}
//		String encryptedPassword = encryptPassword(password);
//		Account account = new Account(websiteObj, url, username, encryptedPassword, secretKey, user);
//		return accountRepository.save(account);
//	}
//
//	public List<Account> getAccounts(String website) {
//		List<Account> accounts = accountRepository.findByWebsite(website);
//		for (Account account : accounts) {
//			String decryptedPassword = decryptPassword(account.getPassword());
//			account.setPassword(decryptedPassword);
//		}
//		return accounts;
//	}
//
//	private String encryptPassword(String password) {
//		try {
//			Cipher cipher = Cipher.getInstance("AES");
//			SecretKeySpec key = new SecretKeySpec(secretKey.getBytes(), "AES");
//			cipher.init(Cipher.ENCRYPT_MODE, key);
//			byte[] encryptedPasswordBytes = cipher.doFinal(password.getBytes());
//			return Base64.getEncoder().encodeToString(encryptedPasswordBytes);
//		} catch (Exception ex) {
//			throw new RuntimeException(ex);
//		}
//	}
//
//	private String decryptPassword(String encryptedPassword) {
//		try {
//			Cipher cipher = Cipher.getInstance("AES");
//			SecretKeySpec key = new SecretKeySpec(secretKey.getBytes(), "AES");
//			cipher.init(Cipher.DECRYPT_MODE, key);
//			byte[] encryptedPasswordBytes = Base64.getDecoder().decode(encryptedPassword);
//			byte[] decryptedPasswordBytes = cipher.doFinal(encryptedPasswordBytes);
//			return new String(decryptedPasswordBytes);
//		} catch (Exception ex) {
//			throw new RuntimeException(ex);
//		}
//	}
}
