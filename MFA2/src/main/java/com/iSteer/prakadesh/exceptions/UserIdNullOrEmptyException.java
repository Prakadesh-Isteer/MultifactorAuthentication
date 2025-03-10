package com.iSteer.prakadesh.exceptions;

import com.iSteer.prakadesh.enums.MfaEnum;

public class UserIdNullOrEmptyException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

public UserIdNullOrEmptyException(MfaEnum message) {
	super(" Status : " + message.getStatusCode() + " Message : " + message.getStatusMessage());
}
}
