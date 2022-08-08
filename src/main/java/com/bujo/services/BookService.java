package com.bujo.services;

import java.util.List;

import com.bujo.data.entities.Book;

public interface BookService {
	Book findBookById(Long id);
	List<Book> getAllBooks();
	Book saveBook(Book book);
	Book updateBook(Book book);
	void deleteBook(Long id);
}
