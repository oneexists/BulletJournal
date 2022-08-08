package com.bujo.exceptions;

public class AppUserTakenUsernameException extends RuntimeException {
	private static final long serialVersionUID = 202208001L;

	public AppUserTakenUsernameException(String username) {
		super("Username is taken (" + username + ")");
	}
}
