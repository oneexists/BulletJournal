package com.bujo.data.factories;

import com.bujo.data.entities.AppUser;
import com.bujo.data.entities.Author;
import com.bujo.data.entities.Book;

public class BookFactoryImpl implements BookFactory {
	@Override
	public Book create(Long bookId, String title, Author author, String language, int pages, AppUser user) {
		return new Book(bookId, title, author, language, pages, user);
	}
}
