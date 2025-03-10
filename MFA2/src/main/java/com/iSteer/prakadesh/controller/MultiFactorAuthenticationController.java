package com.iSteer.prakadesh.controller;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;

import com.iSteer.prakadesh.dto.LoginCredentialsDto;
import com.iSteer.prakadesh.dto.LoginResponseDto;
import com.iSteer.prakadesh.dto.SecretQuestionDto;

import com.iSteer.prakadesh.dto.StatusSuccessDto;
import com.iSteer.prakadesh.dto.SuccessMessageDto;
import com.iSteer.prakadesh.dto.UserAnswerUpdateRequestDto;
import com.iSteer.prakadesh.dto.UserDto;
import com.iSteer.prakadesh.entity.User;
import com.iSteer.prakadesh.entity.UserRoleCredentials;
import com.iSteer.prakadesh.service.MultiFactorAuthenticationService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author prakadesh.u_isteer
 * 
 */
@RestController
@RequestMapping("/mfa/sqa")
public class MultiFactorAuthenticationController{
	@Autowired
	private MultiFactorAuthenticationService service;
	private static final Logger logging = LogManager.getLogger(MultiFactorAuthenticationController.class);
	
	
	@PostMapping("/registerUser")
	public StatusSuccessDto registerUser(@Valid @RequestBody UserRoleCredentials userRegister) {
		StatusSuccessDto status = new StatusSuccessDto();
		if(service.registerCustomer(userRegister)) {
			status.setStatusCode(1023);
			status.setStatusMessage("User registered Successfully");
		} else {
		status.setStatusCode(9999);
		status.setStatusMessage("User Registeration Failed");
		}
		return status;
	}
	
	@PostMapping("/login")
	public LoginResponseDto loginUser(@Valid @RequestBody LoginCredentialsDto userLogin) {
		LoginResponseDto response = new LoginResponseDto();
		String token =service.userLogin(userLogin);
		response.setStatusCode(1930);
	    response.setToken(token);
		return response;
		
		
	}
	@PreAuthorize("hasRole('Admin')")  
	@PostMapping("/roleFixing")
	public StatusSuccessDto updateCustomerRole(@RequestBody UserRoleCredentials updateRole) {
		StatusSuccessDto status = new StatusSuccessDto();
		System.out.println("in controller......................b");
		if(service.updateCustomerRole(updateRole)) {
			status.setStatusCode(1234);
			status.setStatusMessage("User role has changed ");
		}
		else {
			status.setStatusCode(9991);
			status.setStatusMessage("Access Denied");
			}
			return status;
	}
	
	@PreAuthorize("hasRole('User') or hasRole('Admin')")
	@PostMapping("/registerSecret")
    public StatusSuccessDto registerUser(@Valid @RequestBody UserDto userdata){
		StatusSuccessDto status = new StatusSuccessDto();
		logging.info(" user registeration endpoint hitted successfully");
	   if(service.addUserDetails(userdata)) {
		   status.setStatusCode(1340);
			status.setStatusMessage("USER ADDED SUCCESSFULLY");
	   }
	   else {
		   status.setStatusCode(9991);
			status.setStatusMessage("USER NOT ADDED");
	   }
		return status;
	}
   
	@PreAuthorize("hasRole('Admin')")  
	@GetMapping("/checkRegistration")
	public SuccessMessageDto verifyUser(@NotNull @RequestParam (required = true) String userId) {
		SuccessMessageDto status = new SuccessMessageDto();
        if(service.verifyUser(userId)) {
        	status.setStatusMessage(" USER FOUND! USER VERIFIED! ");
        }
        else {
        	status.setStatusMessage("Verification Operation failed");
        }
        return status;
	}
	
	@PreAuthorize("hasRole('User') or hasRole('Admin')")
	@GetMapping("/getSecretQuestion")
	public SecretQuestionDto randomUserQuestions(@RequestParam String userId) {
	        return service.randomSecretQuestion(userId);      
	    
	}
	
	 @PreAuthorize("hasRole('User') or hasRole('Admin')")
	 @PostMapping("/validateAnswer")
	    public StatusSuccessDto validateAnswer(@RequestBody User userReq) {
	        return service.validateAnswer(userReq.getUserId(), userReq.getSecretAnswer());
	 }
	  
	 @PreAuthorize("hasRole('User') or hasRole('Admin')")	
	 @PatchMapping("/updateSecretAnswer")
	    public StatusSuccessDto updateAnswer(@RequestBody UserAnswerUpdateRequestDto updateRequest) {
	        return service.updateAnswer(updateRequest);  
	    }


}
