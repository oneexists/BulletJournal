package com.bujo.data.factories;

import com.bujo.data.entities.Author;

public interface AuthorFactory {
	Author create(String name);
	Author create(Long id, String name);

}
