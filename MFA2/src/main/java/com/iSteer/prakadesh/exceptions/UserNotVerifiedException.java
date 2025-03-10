package com.iSteer.prakadesh.exceptions;

import com.iSteer.prakadesh.enums.MfaEnumMessage;

public class UserNotVerifiedException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public UserNotVerifiedException(MfaEnumMessage userVerificationException) {
		super( "Status : " + userVerificationException.getStatusMessage());
	}
	}
	


