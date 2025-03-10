package com.iSteer.prakadesh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iSteer.prakadesh.entity.UserRoleCredentials;

@Repository
public interface UserDataRepo  extends JpaRepository<UserRoleCredentials,Integer >{
	
	UserRoleCredentials findByUserName(String Username);
}
