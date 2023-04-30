package com.example.pwdManager.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.pwdManager.Model.User;
import com.example.pwdManager.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping("/register")
	public ResponseEntity<User> registerUser(@RequestHeader(value = "token") String token) {
		try {
			User user = userService.authorize(token);
			return ResponseEntity.ok().body(user);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(null);
		}
	}

}
