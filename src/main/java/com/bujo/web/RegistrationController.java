package com.bujo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bujo.services.AppUserService;
import com.bujo.web.dto.AppUserRegistrationDto;

@Controller
@RequestMapping("/registration")
public class RegistrationController {
	private AppUserService appUserService;

	@Autowired
	public RegistrationController(AppUserService appUserService) {
		this.appUserService = appUserService;
	}

	@GetMapping
	public String showRegistrationForm(Model model) {
		model.addAttribute("appUser", new AppUserRegistrationDto());
		return "registration";
	}

	@PostMapping
	public String registerUserAccount(@ModelAttribute("appUser") AppUserRegistrationDto registrationDto) {
		appUserService.registerAppUser(registrationDto);
		return "redirect:/registration?success";
	}
}
