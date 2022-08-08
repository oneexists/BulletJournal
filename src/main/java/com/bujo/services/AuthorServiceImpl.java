package com.bujo.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bujo.data.entities.Author;
import com.bujo.data.repositories.AuthorRepository;
import com.bujo.exceptions.AuthorNotFoundException;

@Service
@Transactional
public class AuthorServiceImpl implements AuthorService {
	private final AuthorRepository authorRepository;
	
	@Autowired
	public AuthorServiceImpl(AuthorRepository authorRepository) {
		this.authorRepository = authorRepository;
	}
	
	@Override
	public Author findByAuthorId(Long id) {
		return authorRepository.findById(id).orElseThrow(() -> new AuthorNotFoundException(id));
	}
	
	@Override
	public Author findByAuthorName(String name) {
		return authorRepository.findByName(name);
	}

	@Override
	public List<Author> getAllAuthors() {
		return authorRepository.findAll();
	}

	@Override
	public void deleteAuthor(Long id) {
		authorRepository.deleteById(id);
	}

	@Override
	public Author saveAuthor(Author author) {
		return authorRepository.save(author);
	}

	@Override
	public Author updateAuthor(Author author) {
		return authorRepository.findById(author.getId()).map(savedAuthor -> {
			savedAuthor.setName(author.getName());
			savedAuthor.setBooks(author.getBooks());
			return authorRepository.saveAndFlush(savedAuthor);
		}).orElseGet(() -> authorRepository.save(author));
	}

}
