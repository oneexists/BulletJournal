package com.bujo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AppUserTakenUsernameAdvice {
	@ExceptionHandler(AppUserTakenUsernameException.class)
	public ResponseEntity<ErrorResponse> appUserTakenUsernameHandler(AppUserTakenUsernameException ex) {
		return new ResponseEntity<>(new ErrorResponse(ex.getMessage()), HttpStatus.BAD_REQUEST);
	}
}
