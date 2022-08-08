package com.bujo.data.factories;

public class BookProvider {
	public static BookFactory getFactory() {
		return new BookFactoryImpl();
	}
}
