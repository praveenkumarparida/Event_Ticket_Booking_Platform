package com.ey.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class LoginRequest {


	@NotBlank(message = "Email is required")
    @Email(message = "Email must be a valid email address")
	private String email;

    @NotBlank(message = "Password is required")
    private String password;
    
	public LoginRequest(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}
	public LoginRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
    
    
}
