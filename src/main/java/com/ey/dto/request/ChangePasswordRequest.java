package com.ey.dto.request;

import jakarta.validation.constraints.NotBlank;

public class ChangePasswordRequest {

	@NotBlank(message = "Old password is required")
	private String oldPassword;
	
	@NotBlank(message = "New password is required")
    private String newPassword;
	public ChangePasswordRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ChangePasswordRequest(String oldPassword, String newPassword) {
		super();
		this.oldPassword = oldPassword;
		this.newPassword = newPassword;
	}
	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
    
    
}
