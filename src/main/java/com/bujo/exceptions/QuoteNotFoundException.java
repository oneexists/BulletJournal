package com.bujo.exceptions;

public class QuoteNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 202208001L;
	
	public QuoteNotFoundException(Long id) {
		super("Could not find quote " + id);
	}
}
