package com.bujo.web.dto;

import java.time.LocalDate;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

public class BookDto {
	private Long bookId;
	private Long logId;
	@NotBlank(message = "Title cannot be empty.")
	private String title;
	@NotBlank(message = "Author cannot be empty.")
	private String author;
	@NotBlank(message = "Language cannot be empty.")
	@Size(min = 2, message = "Language must be at least two characters long.")
	private String language;
	@Min(value=0, message = "Pages must be greater than or equal to 0.")
	private int pages;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@PastOrPresent
	private LocalDate start;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@PastOrPresent
	private LocalDate finish;
	private String username;

	public BookDto() {
	}

	public BookDto(String username) {
		this.username = username;
	}

	public BookDto(Long bookId, String title, String author, String language, int pages, String username) {
		this.bookId = bookId;
		this.title = title;
		this.author = author;
		this.language = language;
		this.pages = pages;
		this.username = username;
	}

	public BookDto(Long bookId, Long logId, String title, String author, String language, int pages, LocalDate start, LocalDate finish,
			String username) {
		this.bookId = bookId;
		this.logId = logId;
		this.title = title;
		this.author = author;
		this.language = language;
		this.pages = pages;
		this.start = start;
		this.finish = finish;
		this.username = username;
	}

	public Long getBookId() {
		return bookId;
	}

	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}

	public Long getLogId() {
		return logId;
	}

	public void setLogId(Long logId) {
		this.logId = logId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public LocalDate getStart() {
		return start;
	}

	public void setStart(LocalDate start) {
		this.start = start;
	}

	public LocalDate getFinish() {
		return finish;
	}

	public void setFinish(LocalDate finish) {
		this.finish = finish;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
