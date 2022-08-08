package com.bujo.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bujo.data.entities.Log;

@Repository
public interface LogRepository extends JpaRepository<Log, Long> {

}
