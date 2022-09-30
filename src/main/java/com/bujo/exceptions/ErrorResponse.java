package com.bujo.exceptions;

import java.time.LocalDateTime;

public class ErrorResponse {
	LocalDateTime timestamp = LocalDateTime.now();
	String message;

	public ErrorResponse(String message) {
		this.message = message;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public String getMessage() {
		return message;
	}
	
}
