package com.bujo.web;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bujo.data.entities.Author;
import com.bujo.data.entities.Book;
import com.bujo.data.factories.AuthorProvider;
import com.bujo.data.factories.BookProvider;
import com.bujo.services.AppUserService;
import com.bujo.services.AuthorService;
import com.bujo.services.BookService;
import com.bujo.services.LogService;
import com.bujo.web.domain.BookConverter;
import com.bujo.web.dto.BookDto;

@Controller
@RequestMapping("/books")
public class BookController {
	private final String BOOKSHELF_PAGE = "books/bookshelf";
	private final String ADD_BOOK_PAGE = "books/add-book";
	private final String EDIT_BOOK_PAGE = "books/edit-book";
	private final String VIEW_BOOK_PAGE = "books/view-book";
	private final String REDIRECT_VIEW_BOOK_PAGE = "redirect:/books/";
	private final String REDIRECT_BOOKSHELF = "redirect:/books/bookshelf";

	private final BookConverter bookConverter;
	private final BookService bookService;
	private final AppUserService appUserService;
	private final AuthorService authorService;
	private final LogService logService;

	@Autowired
	public BookController(BookConverter bookConverter, BookService bookService, AppUserService appUserService, AuthorService authorService,
			LogService logService) {
		this.bookConverter = bookConverter;
		this.bookService = bookService;
		this.appUserService = appUserService;
		this.authorService = authorService;
		this.logService = logService;
	}
	
	@GetMapping("/bookshelf")
	public String bookshelf(Authentication authentication, Model model) {
		List<Book> books = bookService.getAllBooks().stream().filter(book -> book.getAppUser().getUsername().equals(authentication.getName())).collect(Collectors.toList());
		model.addAttribute("books", books);
		return BOOKSHELF_PAGE;
	}

	@ModelAttribute("/book")
	public BookDto book() {
		return new BookDto();
	}

	@PostMapping("/save")
	public String addBook(@ModelAttribute("book") @Valid final BookDto bookDto, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			return ADD_BOOK_PAGE;
		}
		if (authorService.findByAuthorName(bookDto.getAuthor()) == null) {
			authorService.saveAuthor(AuthorProvider.getFactory().create(bookDto.getAuthor()));
		}
		Book newBook = bookService.saveBook(bookConverter.clientToBook(bookDto));
		return REDIRECT_VIEW_BOOK_PAGE + newBook.getId();
	}
	
	@GetMapping("/edit{id}")
	public String editBook(@PathVariable("id") Long id, Model model) {
		model.addAttribute("book", bookConverter.bookToClient(bookService.findBookById(id)));
		return EDIT_BOOK_PAGE;
	}
	
	@PostMapping("/update")
	public String updateBook(@ModelAttribute("book") @Valid final BookDto bookDto, BindingResult result, 
			Model model, Authentication authentication) {
		if (result.hasErrors()) {
			return EDIT_BOOK_PAGE;
		}
		Author savedAuthor = bookService.findBookById(bookDto.getBookId()).getAuthor();
		if (savedAuthor.getName() != bookDto.getAuthor()) {
			savedAuthor.setName(bookDto.getAuthor());
			authorService.updateAuthor(savedAuthor);
		}
		bookService.updateBook(BookProvider.getFactory().create(bookDto.getBookId(), bookDto.getTitle(), 
				savedAuthor, bookDto.getLanguage(), bookDto.getPages(), 
				appUserService.findAppUserByUsername(authentication.getName())));
		return REDIRECT_VIEW_BOOK_PAGE + bookDto.getBookId();
	}

	@GetMapping("/add")
	public String addBook(Authentication authentication, Model model) {
		model.addAttribute("book", new BookDto(authentication.getName()));
		return ADD_BOOK_PAGE;
	}

	@GetMapping("/delete{id}")
	public String deleteBook(@PathVariable("id") Long id, Model model) {
		bookService.findBookById(id).getLogs().forEach(log -> logService.deleteLog(log.getId()));
		bookService.deleteBook(id);
		return REDIRECT_BOOKSHELF;
	}

	@GetMapping("/{id}")
	public String viewBook(@PathVariable("id") Long id, Model model) {
		Book book = bookService.findBookById(id);
		model.addAttribute("book", book);
		model.addAttribute("logs", book.getLogs());
		return VIEW_BOOK_PAGE;
	}
}
