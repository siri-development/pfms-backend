package com.pfms.api.util;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

//    private final String secret = "yourSecretKeyForJWTGeneration";
	/*
	 * @Value("${jwt.secret}") private String secret;
	 * 
	 * @Value("${jwt.expiration}") private long expiration;
	 */

	@Value("${jwt.secret}")
	private String secret;

	public String generateToken(UserDetails userDetails) {
		System.out.println("=============Entered into JwtUtil==========");
		return Jwts.builder().setSubject(userDetails.getUsername())
				.claim("roles", userDetails.getAuthorities().toString())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 hour
				.signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
	}

	public boolean validateToken(String token, UserDetails userDetails) {
		return getUsernameFromToken(token).equals(userDetails.getUsername()) && !isTokenExpired(token);
	}

	public boolean validateToken(String token) {
		boolean isAlive = false;
		try {
			String username = getUsernameFromToken(token);
			if (username != null && !isTokenExpired(token)) {
				return isAlive = true;
			} else {
				return isAlive;
			}
		} catch (Exception e) {
			return false;
		}
	}

	public String getUsernameFromToken(String token) {
		return getClaims(token).getSubject();
	}

	public boolean isTokenExpired(String token) {
		return getClaims(token).getExpiration().before(new Date());
	}

	private Claims getClaims(String token) {
		return Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token).getBody();
	}

	private Key getSignKey() {
		byte[] keyBytes = secret.getBytes(StandardCharsets.UTF_8);
		return Keys.hmacShaKeyFor(keyBytes);
	}

}
