package com.example.pwdManager.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.pwdManager.Model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findByWebsite(String website);
    List<Account> findByWebsiteContainingIgnoreCase(String website);
	Account findByWebsiteAndSecretKey(String website, String secretKey);
}
