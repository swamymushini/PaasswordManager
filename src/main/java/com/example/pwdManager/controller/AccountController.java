package com.example.pwdManager.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.pwdManager.Model.Account;
import com.example.pwdManager.Model.Website;
import com.example.pwdManager.errors.ResourceNotFoundException;
import com.example.pwdManager.repo.AccountRepository;
import com.example.pwdManager.repo.WebsiteRepository;
import com.example.pwdManager.service.UserService;

@RestController
@RequestMapping("/accounts")
@CrossOrigin(origins = "*")
public class AccountController {

	private final AccountRepository accountRepository;
	private final WebsiteRepository websiteRepository;
	private final UserService userService;

	@Autowired
	public AccountController(AccountRepository accountRepository, WebsiteRepository websiteRepository,
			UserService userService) {
		this.accountRepository = accountRepository;
		this.websiteRepository = websiteRepository;
		this.userService = userService;
	}

	@PostMapping("/add")
	public ResponseEntity<Account> addAccount(@RequestBody Account account) {

		Website website = websiteRepository.findByName(account.getWebsite().getName());

		if (website == null) {
			website = new Website(account.getWebsite().getName(), account.getWebsite().getUrl());
			websiteRepository.save(website);
		}

		account.setWebsite(website);
		Account newAccount = accountRepository.save(account);
		return ResponseEntity.status(HttpStatus.CREATED).body(newAccount);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Account> updateAccount(@PathVariable Long id, @RequestBody Account account) {
		Account existingAccount = accountRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Account not found with id " + id));

		existingAccount.setUsername(account.getUsername());
		existingAccount.setPassword(account.getPassword());

		Account updatedAccount = accountRepository.save(existingAccount);
		return ResponseEntity.ok(updatedAccount);
	}

	@GetMapping("/websites")
	public List<Website> getAllWebsites() {
		List<Website> websites = websiteRepository.findAll();
		return websites;
	}

	@GetMapping("/websites/user")
	public List<Website> getAllWebsitesByUser(@RequestParam Long userId) {
		List<Account> accounts = accountRepository.findByUserId(userId);
		List<Website> websites = accounts.stream().map(Account::getWebsite).collect(Collectors.toList());
		return websites;
	}

	@GetMapping("/account")
	public ResponseEntity<Account> getAccount(@RequestParam Long websiteId, @RequestParam String secretKey,
			@RequestParam Long userId) {
		Account account = accountRepository.findByWebsiteIdAndSecretKeyAndUserId(websiteId, secretKey, userId);

		if (account == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		} else {
			return ResponseEntity.ok(account);
		}
	}

}
