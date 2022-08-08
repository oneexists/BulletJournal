package com.bujo.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bujo.data.entities.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

}
