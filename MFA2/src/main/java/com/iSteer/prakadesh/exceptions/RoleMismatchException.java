package com.iSteer.prakadesh.exceptions;

import com.iSteer.prakadesh.enums.MfaEnum;

public class RoleMismatchException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public RoleMismatchException(MfaEnum message) {
		super( message.getStatusMessage());
		// TODO Auto-generated constructor stub
	}
	

}
