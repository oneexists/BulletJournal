package com.bujo.data.factories;

public class AuthorProvider {
	public static AuthorFactory getFactory() {
		return new AuthorFactoryImpl();
	}
}
