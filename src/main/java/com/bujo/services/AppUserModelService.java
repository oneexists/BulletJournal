package com.bujo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AppUserModelService implements UserDetailsService {
	private final AppUserModelDao appUserModelDao;
	
	@Autowired
	public AppUserModelService(@Qualifier("appUser") AppUserModelDao appUserModelDao) {
		this.appUserModelDao = appUserModelDao;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return appUserModelDao.selectApplicationUserByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("Invalid credentials."));
	}
}
