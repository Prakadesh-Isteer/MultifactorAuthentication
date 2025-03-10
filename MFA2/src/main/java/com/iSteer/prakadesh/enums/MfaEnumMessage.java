package com.iSteer.prakadesh.enums;

public enum MfaEnumMessage {
	USER_VERIFICATION_EXCEPTION ("USER NOT FOUND! USERCANNOT BE VERIFIED");
	
	String statusMessage;

	MfaEnumMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

}
