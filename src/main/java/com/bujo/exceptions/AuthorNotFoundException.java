package com.bujo.exceptions;

public class AuthorNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 202208001L;

	public AuthorNotFoundException(Long id) {
		super("Could not find author " + id);
	}
}
