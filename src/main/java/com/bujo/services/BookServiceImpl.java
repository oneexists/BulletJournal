package com.bujo.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bujo.data.entities.Book;
import com.bujo.data.repositories.BookRepository;
import com.bujo.exceptions.BookNotFoundException;

@Service
@Transactional
public class BookServiceImpl implements BookService {
	private final BookRepository bookRepository;
	
	@Autowired
	public BookServiceImpl(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}
	
	@Override
	public Book findBookById(Long id) {
		return bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
	}

	@Override
	public List<Book> getAllBooks() {
		return bookRepository.findAll();
	}

	@Override
	public Book saveBook(Book book) {
		return bookRepository.save(book);
	}

	@Override
	public Book updateBook(Book book) {
		return bookRepository.findById(book.getId()).map(savedBook -> {
			savedBook.setTitle(book.getTitle());
			savedBook.setAuthor(book.getAuthor());
			savedBook.setLanguage(book.getLanguage());
			savedBook.setPages(book.getPages());
			savedBook.setLogs(book.getLogs());
			savedBook.setAppUser(book.getAppUser());
			return bookRepository.saveAndFlush(savedBook);
		}).orElseThrow(() -> new BookNotFoundException(book.getId()));
	}

	@Override
	public void deleteBook(Long id) {
		bookRepository.deleteById(id);
	}

}
