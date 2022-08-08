package com.bujo.web;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bujo.data.entities.Log;
import com.bujo.services.LogService;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {
	private final String DASHBOARD_PAGE = "dashboard";
	private final LogService logService;
	
	@Autowired 
	public DashboardController(LogService logService) {
		this.logService = logService;
	}
	
	@GetMapping
	public String getDashboard(Authentication authentication, Model model) {
		List<Log> currentlyReadingLogs = logService.findByUsername(authentication.getName());
		currentlyReadingLogs.removeIf(log -> log.getFinish() != null);
		model.addAttribute("currentlyReading", currentlyReadingLogs);
		
		List<Log> finishedLogs = logService.findByUsername(authentication.getName());
		finishedLogs.removeIf(log -> log.getFinish() == null);
		Map<String, List<Log>> finishedLogMap = finishedLogs.stream().collect(Collectors.groupingBy(log -> log.getBook().getLanguage()));
		model.addAttribute("languages", finishedLogMap);
		return DASHBOARD_PAGE;
	}
}
