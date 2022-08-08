package com.bujo.services;

import java.util.List;

import com.bujo.data.entities.Log;

public interface LogService {
	Log findByLogId(Long id);
	List<Log> findByUsername(String username);
	List<Log> getAllLogs();
	Log saveLog(Log log);
	Log updateLog(Log log);
	void deleteLog(Long id);
}
