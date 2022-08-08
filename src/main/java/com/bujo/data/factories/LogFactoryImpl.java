package com.bujo.data.factories;

import java.time.LocalDate;

import com.bujo.data.entities.Book;
import com.bujo.data.entities.Log;

public class LogFactoryImpl implements LogFactory {

	@Override
	public Log create(Book book, LocalDate start, LocalDate finish) {
		Log newLog = new Log();
		newLog.setBook(book);
		newLog.setStart(start);
		newLog.setFinish(finish);
		return newLog;
	}
	
	@Override
	public Log create(Long id, Book book, LocalDate start, LocalDate finish) {
		return new Log(id, book, start, finish);
	}

}
