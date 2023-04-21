package com.example.pwdManager.controller;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.pwdManager.Model.SystemDetails;
import com.example.pwdManager.errors.IpAddressNotRegisteredException;
import com.example.pwdManager.repo.SystemDetailsRepository;
import com.example.pwdManager.service.EmailService;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class RequestControllerAdvice {

	@Autowired
	private SystemDetailsRepository systemDetailsRepository;

	@Autowired
	private EmailService emailService;

	@ExceptionHandler(IpAddressNotRegisteredException.class)
	public ResponseEntity<String> handleIpAddressNotRegisteredException(IpAddressNotRegisteredException ex) {
		String message = "IP address is not registered.";
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(message);
	}

	@ModelAttribute
	public void validateIpAddress(HttpServletRequest request) {
		String ipAddress = request.getRemoteAddr();
		Optional<SystemDetails> systemDetails = systemDetailsRepository.findByIpAddress(ipAddress);
		SystemDetails sysDetails = systemDetails.orElse(null);
		if (!systemDetails.isPresent()) {
			sysDetails = new SystemDetails(null, ipAddress, request.getRemoteHost(), LocalDateTime.now(), false, null);
			sysDetails.setLastRegisteredOn(LocalDateTime.now());
			emailService.sendEmail("ssphotoshades@gmail.com", "New access to your Passwords manager",
					ipAddress + " is trying to access your password manager");
		}

		sysDetails.setLastAccessedOn(LocalDateTime.now());
		systemDetailsRepository.save(sysDetails);
	}
}
