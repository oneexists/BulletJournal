package com.bujo.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bujo.data.entities.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

	Author findByName(String name);

}
