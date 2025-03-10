package com.iSteer.prakadesh.exceptions;

import com.iSteer.prakadesh.enums.MfaEnum;

public class MarkerNotFoundException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MarkerNotFoundException(MfaEnum message) {
		super(" Status : " + message.getStatusCode() + " Message : " + message.getStatusMessage());
	}

}
