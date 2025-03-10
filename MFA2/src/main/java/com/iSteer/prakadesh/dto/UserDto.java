package com.iSteer.prakadesh.dto;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;


public class UserDto {
	@NotBlank(message = "USER ID KEY CANNOT BE NULL")
	private String userId;
	@NotEmpty(message = " YOU CANNOT DO THIS OPERATION ! PLEASE FILL THIS REQUIREMENTS")
	private List<UserDataDto> userSecrets;
	
	private String password;
	private String role;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public List<UserDataDto> getUserSecrets() {
		return userSecrets;
	}
	public void setUserSecrets(List<UserDataDto> userSecrets) {
		this.userSecrets = userSecrets;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
}
