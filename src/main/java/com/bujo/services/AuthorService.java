package com.bujo.services;

import java.util.List;

import com.bujo.data.entities.Author;

public interface AuthorService {
	Author findByAuthorId(Long id);
	Author findByAuthorName(String name);
	List<Author> getAllAuthors();
	void deleteAuthor(Long id);
	Author saveAuthor(Author author);
	Author updateAuthor(Author author);
}
