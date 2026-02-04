package com.pfms.api.controller;

import java.util.Collections;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pfms.api.dto.AuthRequest;
import com.pfms.api.dto.AuthResponse;
import com.pfms.api.service.AuthService;
import com.pfms.api.util.JwtUtil;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private AuthService authService;

	@Autowired
	private JwtUtil jwtUtil;

	@PostMapping("/register")
	public ResponseEntity<AuthResponse> register(@RequestBody AuthRequest request) {
	    return ResponseEntity.ok(authService.register(request));
	}

	@PostMapping("/login")
	public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
	    return ResponseEntity.ok(authService.authenticate(request));
	}


	@GetMapping("/validate")
	public ResponseEntity<?> checkExpiry(@RequestHeader("Authorization") String authHeader) {
	    String token = authHeader.startsWith("Bearer ") ? authHeader.substring(7) : authHeader;

	    boolean isExpired = jwtUtil.isTokenExpired(token);
	    return ResponseEntity.ok(isExpired ? "Token expired" : "Token valid");
	}
}
