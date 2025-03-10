package com.iSteer.prakadesh.exceptions;

import com.iSteer.prakadesh.enums.MfaEnum;

public class PasswordException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public PasswordException(MfaEnum message) {
		super( message.getStatusMessage());
	}
	

}
