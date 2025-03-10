package com.iSteer.prakadesh.dto;

public class StatusSuccessDto {
	
	//This DTO is will used to get the success message and code
	
	int statusCode;
	String statusMessage;
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public String getStatusMessage() {
		return statusMessage;
	}
	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}
	
}
