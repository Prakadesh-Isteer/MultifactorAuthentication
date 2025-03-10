package com.iSteer.prakadesh.dto;

	public class UserAnswerUpdateRequestDto {

	    private String userId;
	    private String secretQuestion;
	    private String oldAnswer;
	    private String newAnswer;

	    // Getters and Setters
	    
	    public String getUserId() {
	        return userId;
	    }
	    public void setUserId(String userId) {
	        this.userId = userId;
	    }
	    public String getOldAnswer() {
	        return oldAnswer;
	    }

	    public void setOldAnswer(String oldAnswer) {
	        this.oldAnswer = oldAnswer;
	    }

	    public String getNewAnswer() {
	        return newAnswer;
	    }

	    public void setNewAnswer(String newAnswer) {
	        this.newAnswer = newAnswer;
	    }

		public String getSecretQuestion() {
			return secretQuestion;
		}

		public void setSecretQuestion(String secretQuestion) {
			this.secretQuestion = secretQuestion;
		}
	}


