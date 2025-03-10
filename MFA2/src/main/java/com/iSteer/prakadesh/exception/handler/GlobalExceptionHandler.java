package com.iSteer.prakadesh.exception.handler;
import org.springframework.http.HttpStatus;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import com.iSteer.prakadesh.dto.ErrorMessageDto;
import com.iSteer.prakadesh.dto.StatusErrorDto;
import com.iSteer.prakadesh.enums.MfaEnum;
import com.iSteer.prakadesh.enums.MfaEnumMessage;
import com.iSteer.prakadesh.exceptions.AdminDuplicateEntryException;
import com.iSteer.prakadesh.exceptions.AnswerCheckerException;
import com.iSteer.prakadesh.exceptions.AnswerInvaildException;
import com.iSteer.prakadesh.exceptions.NewAnswerNullOrEmptyException;
import com.iSteer.prakadesh.exceptions.OldAnswerNullOrEmptyException;
import com.iSteer.prakadesh.exceptions.QuestionNullOrEmptyException;
import com.iSteer.prakadesh.exceptions.UserAlreadyFoundExcption;
import com.iSteer.prakadesh.exceptions.UserDataInvalidException;
import com.iSteer.prakadesh.exceptions.UserIdNullOrEmptyException;
import com.iSteer.prakadesh.exceptions.UserNotFoundException;
import com.iSteer.prakadesh.exceptions.UserNotVerifiedException;

import io.jsonwebtoken.security.SignatureException;

// i used this annotation to handle the exceptions globally.


@ControllerAdvice
// i used this annotation to display the data in json form directly. 
@ResponseBody

//exception handler is the annotation handles the exception of the particular class globally.

//response status it will used to set http status
/**
 * This is the global exception handler in which i handled all the exceptions globally.
 */
public class GlobalExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public StatusErrorDto Exception(Exception e) {
		StatusErrorDto invaildOperation = new StatusErrorDto();
		invaildOperation.setErrorCode(9888);
		invaildOperation.setErrorMessage(e.getMessage());
		e.printStackTrace();
		return invaildOperation;
	}
	
	
	/**
	 * @param e
	 * @return The user not get added if user already found with the same userId.
	 */
	@ExceptionHandler(UserAlreadyFoundExcption.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public StatusErrorDto UserAlreadyFoundExcption(UserAlreadyFoundExcption e) {
		StatusErrorDto invaildOperation = new StatusErrorDto();
		invaildOperation.setErrorCode(MfaEnum.USER_NOT_ADDED.getStatusCode());
		invaildOperation.setErrorMessage(MfaEnum.USER_NOT_ADDED.getStatusMessage());
		return invaildOperation;	
	}
	
	/**
	 * @param e
	 * @return The user does not get fetched from the source, userId is not there in database
	 */
	@ExceptionHandler(UserNotFoundException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public StatusErrorDto UserNotFoundException(UserNotFoundException e) {	
		StatusErrorDto invaildOperation = new StatusErrorDto();
		invaildOperation.setErrorCode(MfaEnum.USER_NOT_FOUND.getStatusCode());
		invaildOperation.setErrorMessage(MfaEnum.USER_NOT_FOUND.getStatusMessage());
		return invaildOperation;
		
	}
	
	/**
	 * @param e
	 * @return
	 */
	@ExceptionHandler(UserIdNullOrEmptyException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public StatusErrorDto UserIdNullOrEmptyException(UserIdNullOrEmptyException e) {
		StatusErrorDto invaildOperation = new StatusErrorDto();
		invaildOperation.setErrorCode(MfaEnum.USER_ID_NULL_EMPTY_EXCEPTION.getStatusCode());
		invaildOperation.setErrorMessage(MfaEnum.USER_ID_NULL_EMPTY_EXCEPTION.getStatusMessage());
		return invaildOperation;
		
	}
	/**
	 * @param e
	 * @return
	 */
	@ExceptionHandler(QuestionNullOrEmptyException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public StatusErrorDto QuestionNullOrEmptyException(QuestionNullOrEmptyException e) {
		StatusErrorDto invaildOperation = new StatusErrorDto();
		invaildOperation.setErrorCode(MfaEnum.QUESTION_NULL_EMPTY_OR_INVAILD.getStatusCode());
		invaildOperation.setErrorMessage(MfaEnum.QUESTION_NULL_EMPTY_OR_INVAILD.getStatusMessage());
		return invaildOperation;
		
	}
	
	/**
	 * @param e
	 * @return
	 */
	@ExceptionHandler(com.iSteer.prakadesh.exceptions.AnswerNullOrEmptyException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public StatusErrorDto AnswerNullOrEmptyException(com.iSteer.prakadesh.exceptions.AnswerNullOrEmptyException e) {
		StatusErrorDto invaildOperation = new StatusErrorDto();
		invaildOperation.setErrorCode(MfaEnum.ANSWER_NULL_OR_EMPTY.getStatusCode());
		invaildOperation.setErrorMessage(MfaEnum.ANSWER_NULL_OR_EMPTY.getStatusMessage());
		return invaildOperation;
		
	}
	/**
	 * @param e
	 * @return
	 */
	@ExceptionHandler(UserDataInvalidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public StatusErrorDto UserDataInvalidException(UserDataInvalidException e) {
		StatusErrorDto invaildOperation = new StatusErrorDto();
		invaildOperation.setErrorCode(MfaEnum.DATA_VALIDATION_EXCEPTION.getStatusCode());
		invaildOperation.setErrorMessage(MfaEnum.DATA_VALIDATION_EXCEPTION.getStatusMessage());
		return invaildOperation;
	}
	
	/**
	 * @param e
	 * @return
	 */
	@ExceptionHandler(com.iSteer.prakadesh.exceptions.QuestionNotFoundException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public StatusErrorDto QuestionNotFoundException(com.iSteer.prakadesh.exceptions.QuestionNotFoundException e) {
		StatusErrorDto invaildOperation = new StatusErrorDto();
		invaildOperation.setErrorCode(MfaEnum.QUESTION_NOT_FOUND_EXCEPION.getStatusCode());
		invaildOperation.setErrorMessage(MfaEnum.QUESTION_NOT_FOUND_EXCEPION.getStatusMessage());
		return invaildOperation;
	}
	/**
	 * @param e
	 * @return
	 */
	@ExceptionHandler(AnswerCheckerException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public StatusErrorDto AnswerCheckerException(AnswerCheckerException e) {
		StatusErrorDto invaildOperation = new StatusErrorDto();
		invaildOperation.setErrorCode(MfaEnum.ANSWER_CHECK.getStatusCode());
		invaildOperation.setErrorMessage(MfaEnum.ANSWER_CHECK.getStatusMessage());
		return invaildOperation;
		
	}
	
	/**
	 * @param e
	 * @return
	 */
	@ExceptionHandler(AnswerInvaildException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public StatusErrorDto AnswerInvaildException(AnswerInvaildException e) {
		StatusErrorDto invaildOperation = new StatusErrorDto();
		invaildOperation.setErrorCode(MfaEnum.ANSWER_INCORRECT.getStatusCode());
		invaildOperation.setErrorMessage(MfaEnum.ANSWER_INCORRECT.getStatusMessage());
		return invaildOperation;
		
	}
	
	/**
	 * @param e
	 * @return
	 */
	@ExceptionHandler(NewAnswerNullOrEmptyException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public StatusErrorDto NewAnswerNullOrEmptyException(NewAnswerNullOrEmptyException e) {
		StatusErrorDto invaildOperation = new StatusErrorDto();
		invaildOperation.setErrorCode(MfaEnum.GET_NEW_ANSWER_FOR_UPDATE.getStatusCode());
		invaildOperation.setErrorMessage(MfaEnum.GET_NEW_ANSWER_FOR_UPDATE.getStatusMessage());
		return invaildOperation;
	
}
	/**
	 * @param e
	 * @return
	 */
	@ExceptionHandler(OldAnswerNullOrEmptyException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public StatusErrorDto OldAnswerNullOrEmptyException(OldAnswerNullOrEmptyException e) {
		StatusErrorDto invaildOperation = new StatusErrorDto();
		invaildOperation.setErrorCode(MfaEnum.GET_OLD_ANSWER_FORUPDATE.getStatusCode());
		invaildOperation.setErrorMessage(MfaEnum.GET_OLD_ANSWER_FORUPDATE.getStatusMessage());
		return invaildOperation;
	}
	
	/**
	 * @param e
	 * @return
	 */
	@ExceptionHandler(com.iSteer.prakadesh.exceptions.MarkerNotFoundException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public StatusErrorDto MarkerNotFoundException(com.iSteer.prakadesh.exceptions.MarkerNotFoundException e) {
		StatusErrorDto invaildOperation = new StatusErrorDto();
		invaildOperation.setErrorCode(MfaEnum.MARKER_NOT_FOUND_EXCEPTION.getStatusCode());
		invaildOperation.setErrorMessage(MfaEnum.MARKER_NOT_FOUND_EXCEPTION.getStatusMessage());
		return invaildOperation;
	}
	
	@ExceptionHandler(UserNotVerifiedException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorMessageDto UserNotVerifiedException(UserNotVerifiedException e) {
		ErrorMessageDto invaildOperation = new ErrorMessageDto();
		invaildOperation.setStatusMessage(MfaEnumMessage.USER_VERIFICATION_EXCEPTION.getStatusMessage());
		return invaildOperation;
		
	}
	
	@ExceptionHandler(MissingServletRequestParameterException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public StatusErrorDto MissingServletRequestParameterException(MissingServletRequestParameterException e) {
		StatusErrorDto invaildOperation = new StatusErrorDto();
		invaildOperation.setErrorCode(9824);
		invaildOperation.setErrorMessage(e.getMessage());
		return invaildOperation;
	}
	
	@ExceptionHandler(NoResourceFoundException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public StatusErrorDto NoResourceFoundException(NoResourceFoundException e) {
		StatusErrorDto invaildOperation = new StatusErrorDto();
		invaildOperation.setErrorCode(9478);
		invaildOperation.setErrorMessage(e.getMessage());
		return invaildOperation;
	}
	@ExceptionHandler(org.springframework.web.bind.MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public StatusErrorDto MethodArgumentNotValidException(org.springframework.web.bind.MethodArgumentNotValidException e) {
		StatusErrorDto invaildOperation = new StatusErrorDto();
		invaildOperation.setErrorCode(9345);
		invaildOperation.setErrorMessage(e.getBindingResult().getFieldError().getDefaultMessage());
		return invaildOperation;
	}
	
	@ExceptionHandler(com.iSteer.prakadesh.exceptions.RoleMismatchException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public StatusErrorDto RoleMismatchException(com.iSteer.prakadesh.exceptions.RoleMismatchException e) {
		StatusErrorDto invaildOperation = new StatusErrorDto();
		invaildOperation.setErrorCode(9478);
		invaildOperation.setErrorMessage(e.getMessage());
		return invaildOperation;
	}
	
	@ExceptionHandler(AdminDuplicateEntryException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public StatusErrorDto AdminEntryException(AdminDuplicateEntryException e) {
		StatusErrorDto invaildOperation = new StatusErrorDto();
		invaildOperation.setErrorCode(MfaEnum.Admin_duplicate_entry_exception.getStatusCode());
		invaildOperation.setErrorMessage(MfaEnum.Admin_duplicate_entry_exception.getStatusMessage());
		return invaildOperation;
	}
	
	@ExceptionHandler(com.iSteer.prakadesh.exceptions.PasswordException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public StatusErrorDto PasswordException(com.iSteer.prakadesh.exceptions.PasswordException e) {
		StatusErrorDto invaildOperation = new StatusErrorDto();
		invaildOperation.setErrorCode(MfaEnum.PASSWORD_INVAILD_EXCEPTION.getStatusCode());
		invaildOperation.setErrorMessage(MfaEnum.PASSWORD_INVAILD_EXCEPTION.getStatusMessage());
		return invaildOperation;
	}
	
	@ExceptionHandler(com.iSteer.prakadesh.exceptions.AccessDeniedException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public StatusErrorDto AccessDeniedException(com.iSteer.prakadesh.exceptions.AccessDeniedException e) {
		StatusErrorDto invaildOperation = new StatusErrorDto();
		invaildOperation.setErrorCode(MfaEnum.Access_Denied_Exception.getStatusCode());
		invaildOperation.setErrorMessage(MfaEnum.Access_Denied_Exception.getStatusMessage());
		return invaildOperation;
	}
	
	@ExceptionHandler(AuthorizationDeniedException.class)
	@ResponseStatus(HttpStatus.FORBIDDEN)
	public StatusErrorDto authorizationDeniedException(AuthorizationDeniedException e) {
		StatusErrorDto invaildOperation = new StatusErrorDto();
		invaildOperation.setErrorCode(9788);
		invaildOperation.setErrorMessage("ACCESS DENIED! ONLY ADMIN WITH TOKEN ARE ALLOWED");
		return invaildOperation;
	}
	@ExceptionHandler(SignatureException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public StatusErrorDto SignatureException(SignatureException e) {
		StatusErrorDto invaildOperation = new StatusErrorDto();
		invaildOperation.setErrorCode(9666);
		invaildOperation.setErrorMessage("CHECK THE PROVIDED TOKEN IS CORRECT!");
		return invaildOperation;
	}
	
	@ExceptionHandler(io.jsonwebtoken.ExpiredJwtException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public StatusErrorDto ExpiredJwtException(io.jsonwebtoken.ExpiredJwtException e) {
		StatusErrorDto invaildOperation = new StatusErrorDto();
		invaildOperation.setErrorCode(9666);
		invaildOperation.setErrorMessage(" THE TOKEN HAS EXPIRED ");
		return invaildOperation;
	}
	
	
}