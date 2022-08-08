package com.bujo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bujo.data.entities.Book;
import com.bujo.data.entities.Log;
import com.bujo.data.repositories.LogRepository;
import com.bujo.exceptions.LogNotFoundException;

@Service
@Transactional
public class LogServiceImpl implements LogService {
	private final LogRepository logRepository;
	private final AppUserService appUserService;
	
	@Autowired
	public LogServiceImpl(LogRepository logRepository, AppUserService appUserService) {
		this.logRepository = logRepository;
		this.appUserService = appUserService;
	}
	
	@Override
	public Log findByLogId(Long id) {
		return logRepository.findById(id).orElseThrow(() -> new LogNotFoundException(id));
	}
	
	@Override
	public List<Log> findByUsername(String username) {
		Set<Book> userBooks = appUserService.findAppUserByUsername(username).getBooks();
		List<Log> bookLogs = new ArrayList<>();
		userBooks.forEach(book -> bookLogs.addAll(book.getLogs()));
		return bookLogs;
	}

	@Override
	public List<Log> getAllLogs() {
		return logRepository.findAll();
	}

	@Override
	public Log saveLog(Log log) {
		return logRepository.save(log);
	}

	@Override
	public Log updateLog(Log log) {
		return logRepository.findById(log.getId()).map(savedLog -> {
			savedLog.setBook(log.getBook());
			savedLog.setStart(log.getStart());
			savedLog.setFinish(log.getFinish());
			return logRepository.saveAndFlush(savedLog);
		}).orElseGet(() -> logRepository.save(log));
	}

	@Override
	public void deleteLog(Long id) {
		logRepository.deleteById(id);
	}

}
