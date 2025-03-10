package com.iSteer.prakadesh.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

// this entity will be created as a table in the database

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
    private String userId;
    private String secretQuestion;
    private String secretAnswer;
    private String markquestion;
  
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public void setId(Long id) {
		Id = id;
	}
	public String getSecretQuestion() {
		return secretQuestion;
	}
	public void setSecretQuestion(String secretQuestion) {
		this.secretQuestion = secretQuestion;
	}
	public String getSecretAnswer() {
		return secretAnswer;
	}
	public void setSecretAnswer(String secretAnswer) {
		this.secretAnswer = secretAnswer;
	}
	public Long getId() {
		return Id;
	}

	public User() {
	
	}
	
	@Override
	public String toString() {
		
		return " String "+ userId + "  " + secretQuestion + "  " +  secretAnswer;
	}
	
	public String getMarkquestion() {
		return markquestion;
	}
	public void setMarkquestion(String markquestion) {
		this.markquestion = markquestion;
	}


}
