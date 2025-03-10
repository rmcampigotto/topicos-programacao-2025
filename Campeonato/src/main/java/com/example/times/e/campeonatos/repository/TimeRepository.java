package com.example.times.e.campeonatos.repository;

import com.example.times.e.campeonatos.model.Time;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeRepository extends JpaRepository<Time, Long> {
}