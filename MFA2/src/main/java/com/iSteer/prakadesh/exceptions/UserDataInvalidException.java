package com.iSteer.prakadesh.exceptions;

import com.iSteer.prakadesh.enums.MfaEnum;

public class UserDataInvalidException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public UserDataInvalidException(MfaEnum message) {
		super(" Status : " + message.getStatusCode() + " Message : " + message.getStatusMessage());
	}

}
