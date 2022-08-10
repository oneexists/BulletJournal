package com.bujo.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bujo.data.entities.Quote;
import com.bujo.data.repositories.QuoteRepository;
import com.bujo.exceptions.QuoteNotFoundException;

@Service
@Transactional
public class QuoteServiceImpl implements QuoteService {
	private final QuoteRepository quoteRepository;
	
	@Autowired
	public QuoteServiceImpl(QuoteRepository quoteRepository) {
		this.quoteRepository = quoteRepository;
	}
	
	@Override
	public Quote findByQuoteId(Long id) {
		return quoteRepository.findById(id).orElseThrow(() -> new QuoteNotFoundException(id));
	}

	@Override
	public List<Quote> getAllQuotes() {
		return quoteRepository.findAll();
	}

	@Override
	public void deleteQuote(Long id) {
		quoteRepository.deleteById(id);
	}

	@Override
	public Quote saveQuote(Quote quote) {
		return quoteRepository.save(quote);
	}

	@Override
	public Quote updateQuote(Quote quote) {
		return quoteRepository.findById(quote.getId()).map(savedQuote -> {
			savedQuote.setText(quote.getText());
			savedQuote.setCitation(quote.getCitation());
			savedQuote.setBook(quote.getBook());
			return quoteRepository.saveAndFlush(savedQuote);
		}).orElseGet(() -> quoteRepository.save(quote));
	}

}
