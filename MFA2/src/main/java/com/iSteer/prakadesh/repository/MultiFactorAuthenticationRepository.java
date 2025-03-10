package com.iSteer.prakadesh.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.iSteer.prakadesh.entity.User;


@Repository
public interface MultiFactorAuthenticationRepository extends JpaRepository<User, Long>{

	public List<User> findByUserId(String string);
	
	public List<User> findByUserIdAndMarkquestion(String userId, String markquestion);

}
