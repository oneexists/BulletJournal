package com.bujo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.bujo.services.AppUserModelService;

@Configuration
public class SecurityConfig {
	private final AppUserModelService appUserModelService;
	private final PasswordEncoder passwordEncoder;
	
	@Autowired
	public SecurityConfig(AppUserModelService appUserModelService, PasswordEncoder passwordEncoder) {
		this.appUserModelService = appUserModelService;
		this.passwordEncoder = passwordEncoder;
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setPasswordEncoder(passwordEncoder);
		provider.setUserDetailsService(appUserModelService);
		return provider;
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
		return authConfig.getAuthenticationManager();
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/", "/registration")
				.permitAll()
				.anyRequest()
				.authenticated()
			.and().formLogin().loginPage("/login")
				.defaultSuccessUrl("/dashboard", true)
				.failureUrl("/login?error")
				.permitAll()
			.and().logout()
				.invalidateHttpSession(true)
				.clearAuthentication(true)
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/login?logout")
				.permitAll();
		return http.build();
	}
}
