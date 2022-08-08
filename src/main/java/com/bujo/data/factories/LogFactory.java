package com.bujo.data.factories;

import java.time.LocalDate;

import com.bujo.data.entities.Book;
import com.bujo.data.entities.Log;

public interface LogFactory {
	Log create(Book book, LocalDate start, LocalDate finish);

	Log create(Long id, Book book, LocalDate start, LocalDate finish);
}
