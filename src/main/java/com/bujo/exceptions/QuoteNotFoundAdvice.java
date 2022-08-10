package com.bujo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class QuoteNotFoundAdvice {
	@ResponseBody
	@ExceptionHandler(QuoteNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String quoteNotFoundHandler(QuoteNotFoundException ex) {
		return ex.getMessage();
	}
}
