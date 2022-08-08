package com.bujo.data.factories;

import com.bujo.data.entities.Author;

public class AuthorFactoryImpl implements AuthorFactory {

	@Override
	public Author create(String name) {
		return new Author(name);
	}

	@Override
	public Author create(Long id, String name) {
		return new Author(id, name);
	}

}
