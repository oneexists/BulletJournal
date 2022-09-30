package com.bujo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AppUserNotFoundAdvice {
	@ExceptionHandler(AppUserNotFoundException.class)
	public ResponseEntity<ErrorResponse> appUserNotFoundHandler(AppUserNotFoundException ex) {
		return new ResponseEntity<>(new ErrorResponse(ex.getMessage()), HttpStatus.NOT_FOUND);
	}
}
