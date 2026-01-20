package com.ey.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class ForgotPasswordRequest {

	@NotBlank(message = "Email is required")
    @Email(message = "Email must be a valid email address")
	private String email;

	public ForgotPasswordRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ForgotPasswordRequest(String email) {
		super();
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
