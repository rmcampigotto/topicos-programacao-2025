package com.example.times.e.campeonatos.repository;

import com.example.times.e.campeonatos.model.Campeonato;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CampeonatoRepository extends JpaRepository<Campeonato, Long> {
}