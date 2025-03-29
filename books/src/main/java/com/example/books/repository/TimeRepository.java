package com.example.books.repository;

import com.example.crudmvc.model.Time;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeRepository<Time> extends JpaRepository<Time, Long> {
}
