package com.example.pwdManager.controller;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.pwdManager.Model.SystemDetails;
import com.example.pwdManager.repo.SystemDetailsRepository;

@RestController
@RequestMapping("/api")
public class SystemDetailsController {

//	@Autowired
//	private SystemDetailsRepository systemDetailsRepository;
//
//	@PostMapping("/register-ip")
//	public String registerIp(@RequestParam("ipAddress") String ipAddress, @RequestParam("ipName") String ipName,
//			@RequestParam("accessToken") String accessToken) throws Exception {
//		// Check if IP is already registered
//		Optional<SystemDetails> systemDetails = systemDetailsRepository.findByIpAddress(ipAddress);
//		if (systemDetails != null) {
//			// Update last registered on
//			SystemDetails sysDetails = systemDetails.get();
//			sysDetails.setLastRegisteredOn(LocalDateTime.now());
//			systemDetailsRepository.save(sysDetails);
//			return "IP address updated successfully!";
//		} else {
//			// Check access token
//			if (accessToken.equals("valid_token")) {
//				// Create new SystemDetails object and save to database
//				SystemDetails newSystemDetails = new SystemDetails(null, ipAddress, ipName, LocalDateTime.now(), false, null);
//				systemDetailsRepository.save(newSystemDetails);
//
//				return "IP address registered successfully!";
//			} else {
//				throw new Exception("Invalid access token");
//			}
//		}
//	}
}
