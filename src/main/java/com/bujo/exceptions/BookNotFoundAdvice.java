package com.bujo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BookNotFoundAdvice {
	@ExceptionHandler(BookNotFoundException.class)
	public ResponseEntity<ErrorResponse> bookNotFoundHandler(BookNotFoundException ex) {
		return new ResponseEntity<>(new ErrorResponse(ex.getMessage()), HttpStatus.NOT_FOUND);
	}
}
