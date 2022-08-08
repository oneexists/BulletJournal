package com.bujo.services;

import static com.bujo.data.models.AppUserRole.USER;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import com.bujo.data.entities.AppUser;
import com.bujo.data.entities.Author;
import com.bujo.data.entities.Book;
import com.bujo.data.entities.Log;
import com.bujo.data.factories.AuthorProvider;
import com.bujo.data.factories.BookProvider;
import com.bujo.data.repositories.LogRepository;

/**
 * @author skylar
 *
 */
@SpringBootTest
class LogServiceTests {
	AppUser appUser;
	Author author;
	String authorName = "Walt Whitman";
	Book book;
	Log testLog;
	@Mock
	LogRepository repository;
	@Mock
	AppUserService appUserService;
	LogService service;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		service = new LogServiceImpl(repository, appUserService);
		appUser = new AppUser("JesseJackson", "pass", USER, true, true, true, true);
		author = AuthorProvider.getFactory().create(authorName);
		book = BookProvider.getFactory().create(4L, "Leaves of Grass", author, "Leaves of Grass", 624, appUser);
		testLog = new Log();
		testLog.setBook(book);
		testLog.setStart(LocalDate.now().minusDays(3));
		testLog.setFinish(LocalDate.now());
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
		service = null;
		appUser = null;
		author = null;
		book = null;
		testLog = null;
	}

	/**
	 * Test method for {@link com.bujo.services.LogServiceImpl#updateLog(com.bujo.data.entities.Log)}.
	 */
	@Test
	void testUpdateLog() {
		given(repository.findById(testLog.getId())).willReturn(Optional.of(testLog));
		testLog.setFinish(LocalDate.now().minusDays(1));
		service.updateLog(testLog);
		ArgumentCaptor<Log> updateArgCaptor = ArgumentCaptor.forClass(Log.class);
		verify(repository).saveAndFlush(updateArgCaptor.capture());
		assertThat(updateArgCaptor.getValue()).isEqualTo(testLog);
	}

}
