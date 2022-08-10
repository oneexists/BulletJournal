package com.bujo.services;

import java.util.List;

import com.bujo.data.entities.Quote;

public interface QuoteService {
	Quote findByQuoteId(Long id);
	List<Quote> getAllQuotes();
	void deleteQuote(Long id);
	Quote saveQuote(Quote quote);
	Quote updateQuote(Quote quote);
}
