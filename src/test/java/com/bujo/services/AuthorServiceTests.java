package com.bujo.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.bujo.data.entities.Author;
import com.bujo.data.factories.AuthorProvider;
import com.bujo.data.repositories.AuthorRepository;

/**
 * @author skylar
 *
 */
@SpringBootTest
class AuthorServiceTests {
	Author author;
	String authorName = "Walt Whitman";
	@Mock
	AuthorRepository repository;
	AuthorService service;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		service = new AuthorServiceImpl(repository);
		author = AuthorProvider.getFactory().create(authorName);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
		service = null;
		author = null;
	}

	/**
	 * Test method for {@link com.bujo.services.AuthorServiceImpl#findByAuthorName(java.lang.String)}.
	 */
	@Test
	void testFindByAuthorName() {
		service.findByAuthorName(authorName);
		ArgumentCaptor<String> nameArgCaptor = ArgumentCaptor.forClass(String.class);
		verify(repository).findByName(nameArgCaptor.capture());
		assertThat(nameArgCaptor.getValue()).isEqualTo(authorName);
	}

	/**
	 * Test method for {@link com.bujo.services.AuthorServiceImpl#updateAuthor(com.bujo.data.entities.Author)}.
	 */
	@Test
	void testUpdateAuthor() {
		given(repository.findById(author.getId())).willReturn(Optional.of(author));
		ArgumentCaptor<Author> updateArgCaptor = ArgumentCaptor.forClass(Author.class);
		author.setName("new name");
		service.updateAuthor(author);
		verify(repository).saveAndFlush(updateArgCaptor.capture());
		assertThat(updateArgCaptor.getValue()).isEqualTo(author);
	}

}
