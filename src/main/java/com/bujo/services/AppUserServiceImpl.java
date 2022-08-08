package com.bujo.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bujo.data.entities.AppUser;
import com.bujo.data.repositories.AppUserRepository;
import com.bujo.exceptions.AppUserNotFoundException;
import com.bujo.exceptions.AppUserTakenUsernameException;
import com.bujo.web.dto.AppUserRegistrationDto;

@Service
@Transactional
public class AppUserServiceImpl implements AppUserService {
	private final AppUserRepository appUserRepository;
	private final PasswordEncoder passwordEncoder;
	
	@Autowired
	public AppUserServiceImpl(AppUserRepository appUserRepository, PasswordEncoder passwordEncoder) {
		this.appUserRepository = appUserRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public AppUser findAppUserById(Long id) {
		return appUserRepository.findById(id).orElseThrow(() -> new AppUserNotFoundException(id));
	}
	
	@Override
	public AppUser findAppUserByUsername(String username) {
		return appUserRepository.findByUsername(username).orElseThrow(() -> new AppUserNotFoundException(username));
	}

	@Override
	public List<AppUser> getAllAppUsers() {
		return appUserRepository.findAll();
	}
	
	@Override
	public AppUser updateAppUser(AppUser appUser) {
		return appUserRepository.findById(appUser.getId()).map(user -> {
			if (!user.getUsername().equals(appUser.getUsername()) && appUserRepository.existsByUsername(appUser.getUsername())) {
				user.setUsername(appUser.getUsername());				
			}
			if (!user.getPassword().equals(appUser.getPassword()) ) {
				user.setPassword(passwordEncoder.encode(appUser.getPassword()));
			}
			return appUserRepository.saveAndFlush(user);
		}).orElse(appUserRepository.save(appUser));
	}

	@Override
	public void deleteAppUser(Long id) {
		appUserRepository.deleteById(id);		
	}

	@Override
	public AppUser registerAppUser(AppUserRegistrationDto appUserRegistrationDto) {
		if (appUserRepository.existsByUsername(appUserRegistrationDto.getUsername())) {
			throw new AppUserTakenUsernameException(appUserRegistrationDto.getUsername());
		}
		AppUser newUser = new AppUser();
		newUser.setUsername(appUserRegistrationDto.getUsername());
		newUser.setPassword(passwordEncoder.encode(appUserRegistrationDto.getPassword()));
		return appUserRepository.save(newUser);
	}
	
}
