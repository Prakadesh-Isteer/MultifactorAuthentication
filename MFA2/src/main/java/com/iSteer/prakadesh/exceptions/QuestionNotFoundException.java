package com.iSteer.prakadesh.exceptions;

import com.iSteer.prakadesh.enums.MfaEnum;

public class QuestionNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public QuestionNotFoundException(MfaEnum message) {
		super(" Status : " + message.getStatusCode() + " Message : " + message.getStatusMessage());
	}

}
