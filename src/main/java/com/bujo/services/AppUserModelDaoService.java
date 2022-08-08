package com.bujo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bujo.data.entities.AppUser;
import com.bujo.data.models.AppUserModel;
import com.bujo.data.repositories.AppUserRepository;
import static com.bujo.data.models.AppUserRole.*;

@Service("appUser")
public class AppUserModelDaoService implements AppUserModelDao {
	private final AppUserRepository appUserRepository;
	private final PasswordEncoder passwordEncoder;
	
	@Autowired
	public AppUserModelDaoService(AppUserRepository appUserRepository, PasswordEncoder passwordEncoder) {
		this.appUserRepository = appUserRepository;
		this.passwordEncoder = passwordEncoder;
	}
	
	@Override
	public Optional<AppUserModel> selectApplicationUserByUsername(String username) {
		return getAppUsers().stream()
				.filter(applicationUser -> username.equals(applicationUser.getUsername()))
				.findFirst();
	}

	public List<AppUserModel> getAppUsers() {
		List<AppUser> appUsers = appUserRepository.findAll();
		List<AppUserModel> applicationUsers = new ArrayList<>();
		appUsers.forEach(appUser -> applicationUsers.add(new AppUserModel(appUser.getUsername(), 
				appUser.getPassword(), appUser.getUserRole().getGrantedAuthorities(), 
				appUser.isAccountNonExpired(), appUser.isAccountNonLocked(), appUser.isCredentialsNonExpired(), 
				appUser.isEnabled())));
		return applicationUsers;
	}
	
	public void setAppUsers() {
		appUserRepository.save(new AppUser("jesse", passwordEncoder.encode("pass"), USER, true, true, true, true));
		appUserRepository.save(new AppUser("mike", passwordEncoder.encode("security"), MANAGER, true, true, true, true));
		appUserRepository.save(new AppUser("gus", passwordEncoder.encode("pollo"), ADMIN, true, true, true, true));
	}
}
