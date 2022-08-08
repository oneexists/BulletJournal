package com.bujo.services;

import static com.bujo.data.models.AppUserRole.USER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.bujo.data.entities.AppUser;
import com.bujo.data.repositories.AppUserRepository;
import com.bujo.web.dto.AppUserRegistrationDto;

/**
 * @author skylar
 *
 */
@SpringBootTest
class AppUserServiceTests {
	AppUser testAppUser;
	AppUserRegistrationDto appUserRegistrationDto;
	String username = "JesseJackson";
	String password = "pass";
	@Mock
	AppUserRepository repository;
	@Mock
	PasswordEncoder passwordEncoder;
	AppUserService service;
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		service = new AppUserServiceImpl(repository, passwordEncoder);
		testAppUser = new AppUser(username, password, USER, true, true, true, true);
		appUserRegistrationDto = new AppUserRegistrationDto();
		appUserRegistrationDto.setUsername(username);
		appUserRegistrationDto.setPassword(password);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
		service = null;
		testAppUser = null;
		appUserRegistrationDto = null;
	}

	/**
	 * Test method for {@link com.bujo.services.AppUserServiceImpl#findAppUserByUsername(java.lang.String)}.
	 */
	@Test
	void testFindAppUserByUsername() {
		given(repository.findByUsername(username)).willReturn(Optional.of(testAppUser));
		service.findAppUserByUsername(username);
		ArgumentCaptor<String> usernameArgCaptor = ArgumentCaptor.forClass(String.class);
		verify(repository).findByUsername(usernameArgCaptor.capture());
		assertThat(usernameArgCaptor.getValue()).isEqualTo(username);
	}

	/**
	 * Test method for {@link com.bujo.services.AppUserServiceImpl#updateAppUser(com.bujo.data.entities.AppUser)}.
	 */
	@Test
	void testUpdateAppUser() {
		given(repository.findById(testAppUser.getId())).willReturn(Optional.of(testAppUser));
		testAppUser.setUsername("AaronPaul");
		service.updateAppUser(testAppUser);
		ArgumentCaptor<AppUser> updateArgCaptor = ArgumentCaptor.forClass(AppUser.class);
		verify(repository).saveAndFlush(updateArgCaptor.capture());
		assertThat(updateArgCaptor.getValue()).isEqualTo(testAppUser);
	}

	/**
	 * Test method for {@link com.bujo.services.AppUserServiceImpl#registerAppUser(com.bujo.web.dto.AppUserRegistrationDto)}.
	 */
	@Test
	void testRegisterAppUser() {
		service.registerAppUser(appUserRegistrationDto);
		ArgumentCaptor<AppUser> registerArgCaptor = ArgumentCaptor.forClass(AppUser.class);
		verify(repository).save(registerArgCaptor.capture());
		assertThat(registerArgCaptor.getValue().getUsername()).isEqualTo(username);
		assertThat(registerArgCaptor.getValue().getPassword()).isNotEqualTo(password);
	}

	@Test
	void testDuplicateUsername() {
		given(repository.existsByUsername(username)).willReturn(true);
		assertThatThrownBy(() -> service.registerAppUser(appUserRegistrationDto)).isInstanceOf(Exception.class);
		verify(repository, never()).save(any());
	}
}
