package com.iSteer.prakadesh.service;

import com.iSteer.prakadesh.dto.LoginCredentialsDto;
import com.iSteer.prakadesh.dto.SecretQuestionDto;
import com.iSteer.prakadesh.dto.StatusSuccessDto;
import com.iSteer.prakadesh.dto.UserAnswerUpdateRequestDto;
import com.iSteer.prakadesh.dto.UserDto;
import com.iSteer.prakadesh.entity.UserRoleCredentials;
import com.iSteer.prakadesh.exceptions.UserDataInvalidException;

public interface MultifactorAuthenticationInterface {
	
	
	/**
	 * @param credentials -> Customer Credentials have user name, password.
	 * @return The registration is successful 
	 */
	public boolean registerCustomer(UserRoleCredentials credentials);
	
	/**
	 * @param loginData
	 * @return
	 */
	public String userLogin(LoginCredentialsDto loginData);
	
	/**
	 * @param rolefixing
	 * @return
	 */
	public boolean updateCustomerRole(UserRoleCredentials rolefixing);
	
	/**
	 * @param user
	 * @throw This throw in else part will check the user was added already
	 * @return This will return if user added successfully This operation will add
	 *         the user details
	 * @throws UserDataInvalidException
	 * 
	 */
	public boolean addUserDetails(UserDto listOfUserDetails);
	
	/**
	 * @param userId
	 * @return the status message This method is responsible for whether the user in
	 *         registered to the API
	 */
	public boolean verifyUser(String userId);
	
	//
	/**
	 * @param userId
	 * @return the random questions This method is responsible for showing a random
	 *         question and marking it for // validation.
	 */
	public SecretQuestionDto randomSecretQuestion(String userId);
	
	/**
	 * @param userId
	 * @param answer
	 * @return This method validates the user's answer to the marked question.
	 */
	public StatusSuccessDto validateAnswer(String userId, String answer);
	
	/**
	 * @param userId
	 * @param question
	 * @param oldAnswer
	 * @param newAnswer This method is used to patch the old answer to new one
	 * @return the status message if the user gets updated successfully
	 */
	public StatusSuccessDto updateAnswer(UserAnswerUpdateRequestDto updateSecrets);

}
