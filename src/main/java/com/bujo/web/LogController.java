package com.bujo.web;

import java.time.LocalDate;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bujo.data.entities.Book;
import com.bujo.data.entities.Log;
import com.bujo.data.factories.LogProvider;
import com.bujo.services.BookService;
import com.bujo.services.LogService;
import com.bujo.web.domain.BookConverter;
import com.bujo.web.dto.BookDto;

@Controller
@RequestMapping("/logs")
public class LogController {
	private final String ADD_LOG_PAGE = "logs/add-log";
	private final String VIEW_LOG_PAGE = "logs/view-logs";
	private final String EDIT_LOG_PAGE = "logs/edit-log";
	private final String REDIRECT_VIEW_BOOK_PAGE = "redirect:/books/";
	
	private final BookConverter bookConverter;
	private final LogService logService;
	private final BookService bookService;
	
	@Autowired
	public LogController(BookConverter bookConverter, LogService logService, BookService bookService) {
		this.bookConverter = bookConverter;
		this.logService = logService;
		this.bookService = bookService;
	}

	@ModelAttribute("/log")
	public BookDto log() {
		return new BookDto();
	}
	
	@GetMapping("/all")
	public String viewLogs(Authentication authentication, Model model) {
		model.addAttribute("logs", logService.findByUsername(authentication.getName()));
		return VIEW_LOG_PAGE;
	}
	
	@GetMapping("add{id}")
	public String addLog(@PathVariable("id") Long id, Authentication authentication, Model model) throws NotFoundException {
		BookDto bookDto = bookConverter.bookToClient(bookService.findBookById(id));
		bookDto.setStart(LocalDate.now());
		model.addAttribute("book", bookDto);
		return ADD_LOG_PAGE;
	}
	
	@PostMapping("/save")
	public String saveLog(@Valid @ModelAttribute("book") final BookDto bookDto, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return ADD_LOG_PAGE;
		}
		Book book = bookService.findBookById(bookDto.getBookId());
		logService.saveLog(LogProvider.getFactory().create(book, bookDto.getStart(), bookDto.getFinish()));
		return "redirect:/books/" + book.getId();
	}
	
	@GetMapping("/edit{id}")
	public String editBook(@PathVariable("id") Long id, Model model) {
		Log log = logService.findByLogId(id);
		BookDto bookDto = bookConverter.bookToClient(log.getBook());
		bookDto.setLogId(log.getId());
		bookDto.setStart(log.getStart());
		bookDto.setFinish(log.getFinish());
		model.addAttribute("book", bookDto);
		return EDIT_LOG_PAGE;
	}
	
	@PostMapping("/update")
	public String updateLog(@Valid @ModelAttribute("book") final BookDto bookDto, BindingResult result, 
			Model model) {
		if (result.hasErrors()) {
			return EDIT_LOG_PAGE;
		}
		logService.updateLog(LogProvider.getFactory().create(bookDto.getLogId(), 
				bookService.findBookById(bookDto.getBookId()), bookDto.getStart(), bookDto.getFinish()));
		return REDIRECT_VIEW_BOOK_PAGE + bookDto.getBookId();
	}
	
	@GetMapping("/delete{id}")
	public String deleteLog(@PathVariable("id") Long id, Model model) {
		Long bookId = bookService.findBookById(logService.findByLogId(id).getBook().getId()).getId();
		logService.deleteLog(id);
		return "redirect:/books/" + bookId;
	}
}
