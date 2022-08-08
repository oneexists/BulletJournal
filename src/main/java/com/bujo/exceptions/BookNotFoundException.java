package com.bujo.exceptions;

public class BookNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 202208001L;
	
	public BookNotFoundException(Long id) {
		super("Could not find book " + id);
	}
}
