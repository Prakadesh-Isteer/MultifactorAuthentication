package com.iSteer.prakadesh.exceptions;

import com.iSteer.prakadesh.enums.MfaEnum;

public class AdminDuplicateEntryException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public AdminDuplicateEntryException(MfaEnum message) {
		super( message.getStatusMessage());
	}

}
