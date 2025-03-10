package com.example.times.e.campeonatos.service;

import com.example.times.e.campeonatos.model.Campeonato;
import com.example.times.e.campeonatos.model.Time;
import com.example.times.e.campeonatos.repository.CampeonatoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CampeonatoService {
    private final CampeonatoRepository campeonatoRepository;

    public CampeonatoService(CampeonatoRepository campeonatoRepository) {
        this.campeonatoRepository = campeonatoRepository;
    }

    public List<Campeonato> listarCampeonatos() {
        return campeonatoRepository.findAll();
    }

    public Campeonato salvarCampeonato(Campeonato campeonato) {
        return campeonatoRepository.save(campeonato);
    }

    public Campeonato adicionarTime(Long campeonatoId, Time time) {
        Campeonato campeonato = campeonatoRepository.findById(campeonatoId)
                .orElseThrow(() -> new RuntimeException("Campeonato não encontrado"));

        campeonato.adicionarTime(time);
        return campeonatoRepository.save(campeonato);
    }
}