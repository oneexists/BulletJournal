package com.bujo.data.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Quote implements Serializable {
	private static final long serialVersionUID = 202208001L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String text;
	private String citation;
	@ManyToOne
	private Book book;

	public Quote() {
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getCitation() {
		return citation;
	}

	public void setCitation(String citation) {
		this.citation = citation;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Quote [id=" + id + ", text=" + text + ", citation=" + citation + ", book=" + book.getTitle() + "]";
	}
	
	
}
