package com.example.pwdManager.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.pwdManager.Model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
	Account findByWebsiteIdAndSecretKey(Long websiteId, String secretKey);

	Account findByWebsiteIdAndSecretKeyAndUserId(Long websiteId, String secretKey, Long userId);

	List<Account> findByUserId(Long userId);
}
