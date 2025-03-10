package com.iSteer.prakadesh.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.iSteer.prakadesh.MfaApplication;

public class UsePrincipal implements UserDetails {
	public Logger logging = LogManager.getLogger(MfaApplication.class);
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UserRoleCredentials wrkUser;
	
	public UsePrincipal(UserRoleCredentials wrkUser) {
		logging.info("in user principal class...............");
		this.wrkUser = wrkUser;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
	List<GrantedAuthority> authorities = new ArrayList<>();
	authorities.add(new SimpleGrantedAuthority("ROLE_"+ wrkUser.getRole()));
	logging.info("returning authority................");
		return authorities;
	}

	@Override
	public String getPassword() {
		logging.info("returning password.....................");
		return wrkUser.getPassword();
	}

	@Override
	public String getUsername() {
		logging.info("returning username..........................");
		return wrkUser.getUserName();
	}	

}
