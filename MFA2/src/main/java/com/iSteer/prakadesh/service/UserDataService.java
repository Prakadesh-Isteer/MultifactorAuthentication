package com.iSteer.prakadesh.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.iSteer.prakadesh.entity.UsePrincipal;
import com.iSteer.prakadesh.entity.UserRoleCredentials;
import com.iSteer.prakadesh.enums.MfaEnum;
import com.iSteer.prakadesh.exceptions.UserNotFoundException;
import com.iSteer.prakadesh.repository.UserDataRepo;
@Service
public class UserDataService implements UserDetailsService{
	
	@Autowired	
	UserDataRepo repo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserRoleCredentials wrkUser = repo.findByUserName(username);
		if(wrkUser ==null) {
			throw new UserNotFoundException(MfaEnum.USER_NOT_FOUND);
		}
		return new UsePrincipal(wrkUser);
	}
	
	
	

}
