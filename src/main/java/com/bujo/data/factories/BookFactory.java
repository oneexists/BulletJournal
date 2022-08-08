package com.bujo.data.factories;

import com.bujo.data.entities.AppUser;
import com.bujo.data.entities.Author;
import com.bujo.data.entities.Book;

public interface BookFactory {
	Book create(Long bookId, String title, Author author, String language, int pages, AppUser user);
}
