package com.bujo.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bujo.data.entities.Quote;

@Repository
public interface QuoteRepository extends JpaRepository<Quote, Long>{

}
