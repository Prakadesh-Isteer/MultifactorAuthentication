package com.iSteer.prakadesh.exceptions;

import com.iSteer.prakadesh.enums.MfaEnum;

public class ValidationFailedException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ValidationFailedException(MfaEnum message) {
		super(" Status : " + message.getStatusCode() + " Message : " + message.getStatusMessage());
	}

}
