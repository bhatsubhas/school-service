package com.subhas.school.student;

public class DeleteMessage {
	private String message;

	private DeleteMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public static DeleteMessage createDeleteMessage(String message) {
		return new DeleteMessage(message);
	}

}
