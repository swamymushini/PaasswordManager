package com.example.pwdManager.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.pwdManager.Model.User;
import com.example.pwdManager.repo.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	private final RestTemplate restTemplate = new RestTemplate();

	public User authorize(String token) {
		HttpHeaders headers = new HttpHeaders();
		headers.setBearerAuth(token);
		HttpEntity<String> entity = new HttpEntity<>(headers);
		ResponseEntity<Map> responseEntity = restTemplate.exchange("https://www.googleapis.com/oauth2/v3/userinfo",
				HttpMethod.GET, entity, Map.class);
		Map<String, Object> response = responseEntity.getBody();
		if (response != null) {
			String email = (String) response.get("email");
			String name = (String) response.get("name");
			String photo = (String) response.get("picture");

			User user = userRepository.findByEmail(email);
			if (user == null)
				user = new User(name, email, photo);

			return userRepository.save(user);
		} else {
			throw new RuntimeException("Failed to get user information from token.");
		}
	}

}
