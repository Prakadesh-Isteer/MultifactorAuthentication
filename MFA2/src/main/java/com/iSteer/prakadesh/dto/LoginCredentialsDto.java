package com.iSteer.prakadesh.dto;

import jakarta.validation.constraints.NotBlank;

public class LoginCredentialsDto {
	@NotBlank(message = "USER NAME KEY CANNOT BE NULL")
	private String userName;
	@NotBlank(message = "USER PASSWORD CANNOT BE NULL")
	private String password;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}
