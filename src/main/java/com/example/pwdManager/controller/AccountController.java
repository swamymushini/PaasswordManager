package com.example.pwdManager.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.pwdManager.Model.Account;
import com.example.pwdManager.errors.ResourceNotFoundException;
import com.example.pwdManager.repo.AccountRepository;

@RestController
@RequestMapping("/accounts")
public class AccountController {

	private final AccountRepository accountRepository;

	public AccountController(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

	@PostMapping("/add")
	public ResponseEntity<Account> addAccount(@RequestBody Account account) {
		Account newAccount = accountRepository.save(account);
		return ResponseEntity.status(HttpStatus.CREATED).body(newAccount);
	}

	@PutMapping("/update")
	public ResponseEntity<Account> updateAccount(@PathVariable Long id, @RequestBody Account account) {
		Account existingAccount = accountRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Account not found with id " + id));
		existingAccount.setUsername(account.getUsername());
		existingAccount.setPassword(account.getPassword());
		existingAccount.setWebsite(account.getWebsite());
		existingAccount.setUrl(account.getUrl());
		Account updatedAccount = accountRepository.save(existingAccount);
		return ResponseEntity.ok(updatedAccount);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteAccount(@PathVariable Long id) {
		Account account = accountRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Account not found with id " + id));
		accountRepository.delete(account);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/getAllAccounts")
	public ResponseEntity<List<Account>> getAllAccounts() {
		List<Account> accounts = accountRepository.findAll();
		return ResponseEntity.ok(accounts);
	}

	@GetMapping("/findById/{id}")
	public ResponseEntity<Account> getAccountById(@PathVariable Long id) {
		Account account = accountRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Account not found with id " + id));
		return ResponseEntity.ok(account);
	}

	@GetMapping("/findByWebsite")
	public List<Account> findByWebsite(@RequestParam("website") String matchingName) {
		return accountRepository.findByWebsiteContainingIgnoreCase(matchingName);
	}
}
