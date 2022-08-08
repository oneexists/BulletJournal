package com.bujo.services;

import static com.bujo.data.models.AppUserRole.USER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.bujo.data.entities.AppUser;
import com.bujo.data.entities.Author;
import com.bujo.data.entities.Book;
import com.bujo.data.factories.AuthorProvider;
import com.bujo.data.factories.BookProvider;
import com.bujo.data.repositories.BookRepository;

/**
 * @author skylar
 *
 */
@SpringBootTest
class BookServiceTests {
	AppUser appUser;
	Author author;
	Book testBook;
	@Mock
	BookRepository repository;
	BookService service;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		service = new BookServiceImpl(repository);
		appUser = new AppUser("JesseJackson", "pass", USER, true, true, true, true);
		author = AuthorProvider.getFactory().create("Walt Whitman");
		testBook = BookProvider.getFactory().create(4L, "Leaves of Grass", author, "English", 624, appUser);
		when(repository.saveAndFlush(any(Book.class))).thenReturn(testBook);
		when(repository.findById(any(Long.class))).thenReturn(Optional.of(testBook));
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
		service = null;
		appUser = null;
		author = null;
		testBook = null;
	}

	/**
	 * Test method for {@link com.bujo.services.BookServiceImpl#updateBook(com.bujo.data.entities.Book)}.
	 */
	@Test
	void testUpdateBook() {
		ArgumentCaptor<Book> updateArgCaptor = ArgumentCaptor.forClass(Book.class);
		testBook.setPages(22);
		service.updateBook(testBook);
		verify(repository).saveAndFlush(updateArgCaptor.capture());
		assertThat(updateArgCaptor.getValue().getPages()).isEqualTo(testBook.getPages());
	}

}