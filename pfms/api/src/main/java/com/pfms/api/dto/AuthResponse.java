package com.pfms.api.dto;

public class AuthResponse {
    private String token;
    private String message;

    public AuthResponse(String message, String token) {
        this.setMessage(message);
        this.token = token;
    }

    public String getToken() {
        return token;
    }

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
