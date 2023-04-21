package com.example.pwdManager.repo;

import java.util.Base64;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pwdManager.Model.Account;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    private final String secretKey = "my-secret-key";

    public Account addAccount(String website,String url, String username, String password) {
        String encryptedPassword = encryptPassword(password);
        Account account = new Account(website, url, username, encryptedPassword);
        return accountRepository.save(account);
    }

    public List<Account> getAccounts(String website) {
        List<Account> accounts = accountRepository.findByWebsite(website);
        for (Account account : accounts) {
            String decryptedPassword = decryptPassword(account.getPassword());
            account.setPassword(decryptedPassword);
        }
        return accounts;
    }

    private String encryptPassword(String password) {
        try {
            Cipher cipher = Cipher.getInstance("AES");
            SecretKeySpec key = new SecretKeySpec(secretKey.getBytes(), "AES");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] encryptedPasswordBytes = cipher.doFinal(password.getBytes());
            return Base64.getEncoder().encodeToString(encryptedPasswordBytes);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    private String decryptPassword(String encryptedPassword) {
        try {
            Cipher cipher = Cipher.getInstance("AES");
            SecretKeySpec key = new SecretKeySpec(secretKey.getBytes(), "AES");
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] encryptedPasswordBytes = Base64.getDecoder().decode(encryptedPassword);
            byte[] decryptedPasswordBytes = cipher.doFinal(encryptedPasswordBytes);
            return new String(decryptedPasswordBytes);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
