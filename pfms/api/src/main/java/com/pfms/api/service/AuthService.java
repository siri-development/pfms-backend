package com.pfms.api.service;

import org.springframework.security.core.userdetails.UserDetails;

import com.pfms.api.dto.AuthRequest;
import com.pfms.api.dto.AuthResponse;

public interface AuthService {
	AuthResponse authenticate(AuthRequest request);
    AuthResponse register(AuthRequest request);
    UserDetails loadUserByUsername (String username);
}
