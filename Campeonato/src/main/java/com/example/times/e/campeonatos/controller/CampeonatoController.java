package com.example.times.e.campeonatos.controller;

import com.example.times.e.campeonatos.model.Campeonato;
import com.example.times.e.campeonatos.model.Time;
import com.example.times.e.campeonatos.service.CampeonatoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/campeonatos")
public class CampeonatoController {
    private final CampeonatoService campeonatoService;

    public CampeonatoController(CampeonatoService campeonatoService) {
        this.campeonatoService = campeonatoService;
    }

    @GetMapping
    public List<Campeonato> listarCampeonatos() {
        return campeonatoService.listarCampeonatos();
    }

    @PostMapping
    public Campeonato criarCampeonato(@RequestBody Campeonato campeonato) {
        return campeonatoService.salvarCampeonato(campeonato);
    }

    @PostMapping("/{id}/adicionar-time")
    public Campeonato adicionarTime(@PathVariable Long id, @RequestBody Time time) {
        return campeonatoService.adicionarTime(id, time);
    }
}