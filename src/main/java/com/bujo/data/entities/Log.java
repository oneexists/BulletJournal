package com.bujo.data.entities;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Log implements Serializable {
	private static final long serialVersionUID = 202207001L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	private Book book;
	private LocalDate start;
	private LocalDate finish;
	
	public Log() {
	}
	
	public Log(Long id, Book book, LocalDate start, LocalDate finish) {
		this.id = id;
		this.book = book;
		this.start = start;
		this.finish = finish;
	}

	public Long getId() {
		return id;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
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

	@Override
	public String toString() {
		return "Log [id=" + id + ", book=" + book.getTitle() + ", start=" + start + ", finish=" + finish + "]";
	}
	
	
}
