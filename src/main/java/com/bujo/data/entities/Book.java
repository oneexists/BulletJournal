package com.bujo.data.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Book implements Serializable {
	private static final long serialVersionUID = 202208001L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String title;
	@ManyToOne
	private Author author;
	private int pages;
	private String language;
	@OneToMany(mappedBy="book")
	private Set<Log> logs = new HashSet<>();
	@ManyToOne
	private AppUser appUser;
	
	public Book() {
	}

	public Book(Long id, String title, Author author, String language, int pages, AppUser appUser) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.language = language;
		this.pages = pages;
		this.appUser = appUser;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public Set<Log> getLogs() {
		return logs;
	}

	public void setLogs(Set<Log> logs) {
		this.logs = logs;
	}

	public AppUser getAppUser() {
		return appUser;
	}

	public void setAppUser(AppUser appUser) {
		this.appUser = appUser;
	}

	public Long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", author=" + author + ", pages=" + pages + ", langauge="
				+ language + ", appUser=" + appUser + "]";
	}
	
}
