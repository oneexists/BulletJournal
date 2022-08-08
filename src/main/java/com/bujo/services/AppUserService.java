package com.bujo.services;

import java.util.List;

import com.bujo.data.entities.AppUser;
import com.bujo.web.dto.AppUserRegistrationDto;

public interface AppUserService {
	AppUser findAppUserById(Long id);
	AppUser findAppUserByUsername(String username);
	List<AppUser> getAllAppUsers();
	AppUser updateAppUser(AppUser appUser);
	void deleteAppUser(Long id);
	AppUser registerAppUser(AppUserRegistrationDto appUserRegistrationDto);
}
