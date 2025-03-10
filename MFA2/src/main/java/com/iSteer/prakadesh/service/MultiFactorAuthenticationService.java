package com.iSteer.prakadesh.service;

import java.util.List;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.iSteer.prakadesh.dto.LoginCredentialsDto;
import com.iSteer.prakadesh.dto.SecretQuestionDto;
import com.iSteer.prakadesh.dto.StatusSuccessDto;
import com.iSteer.prakadesh.dto.SuccessMessageDto;
import com.iSteer.prakadesh.dto.UserAnswerUpdateRequestDto;
import com.iSteer.prakadesh.dto.UserDataDto;
import com.iSteer.prakadesh.dto.UserDto;
import com.iSteer.prakadesh.entity.User;
import com.iSteer.prakadesh.entity.UserRoleCredentials;
import com.iSteer.prakadesh.enums.MfaEnum;
import com.iSteer.prakadesh.enums.MfaEnumMessage;
import com.iSteer.prakadesh.exceptions.AdminDuplicateEntryException;
import com.iSteer.prakadesh.exceptions.AnswerCheckerException;
import com.iSteer.prakadesh.exceptions.AnswerInvaildException;
import com.iSteer.prakadesh.exceptions.AnswerNullOrEmptyException;
import com.iSteer.prakadesh.exceptions.NewAnswerNullOrEmptyException;
import com.iSteer.prakadesh.exceptions.OldAnswerNullOrEmptyException;
import com.iSteer.prakadesh.exceptions.PasswordException;
import com.iSteer.prakadesh.exceptions.QuestionNotFoundException;
import com.iSteer.prakadesh.exceptions.QuestionNullOrEmptyException;
import com.iSteer.prakadesh.exceptions.RoleMismatchException;
import com.iSteer.prakadesh.exceptions.UserAlreadyFoundExcption;
import com.iSteer.prakadesh.exceptions.UserIdNullOrEmptyException;
import com.iSteer.prakadesh.exceptions.UserNotFoundException;
import com.iSteer.prakadesh.exceptions.UserNotVerifiedException;
import com.iSteer.prakadesh.repository.MultiFactorAuthenticationRepository;
import com.iSteer.prakadesh.repository.UserDataRepo;

/**
 * @author prakadesh.u_isteer
 */
@Service
public class MultiFactorAuthenticationService implements MultifactorAuthenticationInterface {

	@Autowired
	private MultiFactorAuthenticationRepository repo;
	@Autowired
	UserDataRepo repoData;
	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	JwtFilterUtil jwtUtil;

	public static Logger logging = LogManager.getLogger(MultiFactorAuthenticationService.class);
	
	public boolean registerCustomer(UserRoleCredentials credentials) {
		String userName = credentials.getUserName();
		String password = credentials.getPassword();
		String encrypted = encoder.encode(password);
		UserRoleCredentials wrkCredentials = repoData.findByUserName(userName);
		if (wrkCredentials != null) {
			throw new UserAlreadyFoundExcption(MfaEnum.USER_NOT_ADDED);// Check if a user with the same userId already																// exists
		}
		credentials.setUserName(userName);
		credentials.setPassword(encrypted);
		repoData.save(credentials);
		return true;
	}

	public String userLogin(LoginCredentialsDto loginData) {
		UserRoleCredentials wrkCredentials = repoData.findByUserName(loginData.getUserName());
		logging.info("In the process of verifying.............");
		try {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginData.getUserName(), loginData.getPassword()));
	     	
		if (authentication.isAuthenticated()) {
			logging.info("The token is get Generated !!!!!!");
			String token = jwtUtil.generateToken(wrkCredentials.getUserName(), wrkCredentials.getRole());
			return token;
		}
		}catch(BadCredentialsException e){
			throw new PasswordException(MfaEnum.PASSWORD_INVAILD_EXCEPTION);
		}
		return null;
		}
	

	public boolean updateCustomerRole(UserRoleCredentials rolefixing) {
		UserRoleCredentials wrkCredentials = repoData.findByUserName(rolefixing.getUserName());
		if (wrkCredentials == null) {
			throw new UserNotFoundException(MfaEnum.USER_NOT_FOUND);
		}
		if (!(rolefixing.getRole().equals("Admin") || rolefixing.getRole().equals("User"))) {

			throw new RoleMismatchException(MfaEnum.ROLE_FIELD_EXCEPTION);
		}
		if (rolefixing.getRole().equals(wrkCredentials.getRole())) {

			throw new AdminDuplicateEntryException(MfaEnum.Admin_duplicate_entry_exception);
		}
		wrkCredentials.setRole(rolefixing.getRole());
		repoData.save(wrkCredentials);
		return true;
	}

	
	public boolean addUserDetails(UserDto listOfUserDetails) {
		String userId = listOfUserDetails.getUserId();
		List<User> userDetails = repo.findByUserId(userId);
		if (!userDetails.isEmpty()) {
			throw new UserAlreadyFoundExcption(MfaEnum.USER_NOT_ADDED);// Check if a user with the same userId already exists 
		}
		// Ensure the data is not null before attempting to iterate
		for (UserDataDto wrkIterate : listOfUserDetails.getUserSecrets()) {
			if (wrkIterate.getSecretQuestion().isBlank()) {
				throw new QuestionNullOrEmptyException(MfaEnum.QUESTION_NULL_EMPTY_OR_INVAILD);
			}
			if (wrkIterate.getSecretAnswer().isBlank()) {
				throw new AnswerNullOrEmptyException(MfaEnum.ANSWER_NULL_OR_EMPTY);
			}
		}
		// If the user doesn't exist, save the new user
		List<UserDataDto> userDataSecrets = listOfUserDetails.getUserSecrets();
		for (UserDataDto secretsIterator : userDataSecrets) {
			User newUser = new User();
			newUser.setUserId(userId);
			newUser.setSecretQuestion(secretsIterator.getSecretQuestion());// Assuming UserData has question and answer
			newUser.setSecretAnswer(secretsIterator.getSecretAnswer());
			repo.save(newUser); // Save the new user to the database
			userDetails.add(newUser);
		}
		return true; // Tells true to the controller
	}

	
	public boolean verifyUser(String userId) {
		// Check whether the user left empty or null
	SuccessMessageDto status = new SuccessMessageDto();

		if (userId.isBlank()) {
			throw new UserIdNullOrEmptyException(MfaEnum.USER_ID_NULL_EMPTY_EXCEPTION); // Check if the userId is null
																						// // or empty
		}
		List<User> userDetailsList = repo.findByUserId(userId);
		if (userDetailsList.isEmpty()) {
			throw new UserNotVerifiedException(MfaEnumMessage.USER_VERIFICATION_EXCEPTION);
		}
		status.setStatusMessage(" USER FOUND! USER VERIFIED! ");
		return true;
	}

	
	public SecretQuestionDto randomSecretQuestion(String userId) {
	    // Check if the user exists
	    List<User> userDetailsList = repo.findByUserId(userId);
	    if (userDetailsList.isEmpty()) {
	        throw new UserNotFoundException(MfaEnum.USER_NOT_FOUND); // Business logic to handle missing user
	    }
	    SecretQuestionDto message = new SecretQuestionDto();

	    // Generate a random index to pick a secret question
	    Random random = new Random();
	    int wrkRandomQuestion = random.nextInt(userDetailsList.size());    
	    User userRandomQuestionIndex = userDetailsList.get(wrkRandomQuestion);

	    // Mark the question as null for avoiding multiple markings
	    for (User userDetailsIterator : userDetailsList) {
	        userDetailsIterator.setMarkquestion(null);
	    }

	    // Mark the question for validation set 1 if marked for the particular question
	    userRandomQuestionIndex.setMarkquestion("1");
	    // Save the updated user data
	    repo.save(userRandomQuestionIndex);

	    // Set the message with the secret question
	    message.setSecretQuestion(userRandomQuestionIndex.getSecretQuestion());
	    return message; // Return the secret question
	}

	
	public StatusSuccessDto validateAnswer(String userId, String answer) {
		StatusSuccessDto status = new StatusSuccessDto();
		List<User> userDetailsList = repo.findByUserIdAndMarkquestion(userId, "1");
		if (userDetailsList.isEmpty()) {
			throw new UserNotFoundException(MfaEnum.USER_NOT_FOUND);
		}
		for (User user : userDetailsList) {
			if (user.getSecretAnswer().equals(answer)) {
				status.setStatusCode(1832);
				status.setStatusMessage("Answer is correct for the Question");
				return status;
			}
			throw new AnswerInvaildException(MfaEnum.ANSWER_INCORRECT);

		}
		throw new QuestionNotFoundException(MfaEnum.QUESTION_NOT_FOUND_EXCEPION);
	}

	
	public StatusSuccessDto updateAnswer(UserAnswerUpdateRequestDto updateSecrets) {
		StatusSuccessDto status = new StatusSuccessDto();
		List<User> wrkuserSecrects = repo.findByUserId(updateSecrets.getUserId());
		if (wrkuserSecrects.isEmpty()) {
			throw new UserNotFoundException(MfaEnum.USER_NOT_FOUND);
		}

		// Find the question for which the answer needs to be updated
		for (User userSecrets : wrkuserSecrects) {
			if (userSecrets.getSecretQuestion().equals(updateSecrets.getSecretQuestion())) {

				// Validate the old answer from the from the database via entity
				if (userSecrets.getSecretAnswer().equals(updateSecrets.getOldAnswer())) {

					// Check if the new answer is not blank
					if (updateSecrets.getNewAnswer().isBlank()) {
						throw new NewAnswerNullOrEmptyException(MfaEnum.GET_NEW_ANSWER_FOR_UPDATE);
					}

					if (updateSecrets.getNewAnswer().equals(updateSecrets.getOldAnswer())) {
						throw new OldAnswerNullOrEmptyException(MfaEnum.GET_OLD_ANSWER_FORUPDATE);
					}

					// Update the old answer with the new answer
					userSecrets.setSecretAnswer(updateSecrets.getNewAnswer());
					repo.save(userSecrets);
					status.setStatusCode(1453);
					status.setStatusMessage("Answer updated successfully.");
					return status;
				} else {
					throw new AnswerCheckerException(MfaEnum.ANSWER_CHECK);
				}
			}
		}
		throw new QuestionNotFoundException(MfaEnum.QUESTION_NOT_FOUND_EXCEPION);
	}

}
