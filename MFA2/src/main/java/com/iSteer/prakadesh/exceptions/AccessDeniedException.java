package com.iSteer.prakadesh.exceptions;

import com.iSteer.prakadesh.enums.MfaEnum;

public class AccessDeniedException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AccessDeniedException(MfaEnum message) {
		super( message.getStatusMessage());

	}

}
