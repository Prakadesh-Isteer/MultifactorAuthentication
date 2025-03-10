package com.iSteer.prakadesh.dto;

public class VerifyDataDto {
	
	private String userId;
	private String secretAnswer;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getAnswer() {
		return secretAnswer;
	}
	public void setAnswer(String answer) {
		this.secretAnswer = answer;
	}
	
    public VerifyDataDto(String userId, String answer) {
	this.userId = userId;
	this.secretAnswer = answer;
	}

}
