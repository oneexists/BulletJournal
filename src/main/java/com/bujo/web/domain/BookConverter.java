package com.bujo.web.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bujo.data.entities.Book;
import com.bujo.data.factories.BookProvider;
import com.bujo.services.AppUserService;
import com.bujo.services.AuthorService;
import com.bujo.web.dto.BookDto;

@Component
public class BookConverter {
	private final AppUserService appUserService;
	private final AuthorService authorService;
	
	@Autowired
	public BookConverter(AppUserService appUserService, AuthorService authorService) {
		this.appUserService = appUserService;
		this.authorService = authorService;
	}
	
	public BookDto bookToClient(Book book) {
		return new BookDto(book.getId(), book.getTitle(), book.getAuthor().getName(), 
				book.getLanguage(), book.getPages(), book.getAppUser().getUsername());
	}
	
	public Book clientToBook(BookDto bookDTO) {
		return BookProvider.getFactory().create(bookDTO.getBookId(), bookDTO.getTitle(),
				authorService.findByAuthorName(bookDTO.getAuthor()), bookDTO.getLanguage(), bookDTO.getPages(),
				appUserService.findAppUserByUsername(bookDTO.getUsername()));
	}
}
