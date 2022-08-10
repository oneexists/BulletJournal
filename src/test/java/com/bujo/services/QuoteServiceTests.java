package com.bujo.services;

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

import com.bujo.data.entities.Quote;
import com.bujo.data.repositories.QuoteRepository;

/**
 * @author skylar
 *
 */
@SpringBootTest
class QuoteServiceTests {
	Quote testQuote;
	Quote quote;
	String quoteText = "This is a quote";
	String quoteCitation = "A citation";
	@Mock
	QuoteRepository repository;
	QuoteService service;
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		service = new QuoteServiceImpl(repository);
		testQuote = new Quote();
		testQuote.setText(quoteText);
		testQuote.setCitation(quoteCitation);
		
		quote = new Quote();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
		service = null;
		testQuote = null;
		quote = null;
	}

	/**
	 * Test method for {@link com.bujo.services.QuoteServiceImpl#updateQuote(com.bujo.data.entities.Quote)}.
	 */
	@Test
	void testUpdateQuote() {
		when(repository.findById(any())).thenReturn(Optional.of(testQuote));

		ArgumentCaptor<Quote> updateArgCaptor = ArgumentCaptor.forClass(Quote.class);
		testQuote.setText("New quote");
		service.updateQuote(testQuote);
		verify(repository).saveAndFlush(updateArgCaptor.capture());
		assertThat(updateArgCaptor.getValue()).isEqualTo(testQuote);
	}
	
	@Test
	void testUpdateMissingQuote() {
		when(repository.save(any(Quote.class))).thenReturn(testQuote);
		
		ArgumentCaptor<Quote> saveUpdateArgCaptor = ArgumentCaptor.forClass(Quote.class);
		service.updateQuote(quote);
		verify(repository).save(saveUpdateArgCaptor.capture());
		assertThat(saveUpdateArgCaptor.getValue()).isEqualTo(quote);
	}

}
