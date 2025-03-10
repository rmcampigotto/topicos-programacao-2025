package com.example.times.e.campeonatos.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Time {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @ManyToMany(mappedBy = "times")
    private Set<Campeonato> campeonatos = new HashSet<>();

    // Construtor vazio
    public Time() {}

    // Construtor com parâmetros
    public Time(String nome) {
        this.nome = nome;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public Set<Campeonato> getCampeonatos() { return campeonatos; }
    public void setCampeonatos(Set<Campeonato> campeonatos) { this.campeonatos = campeonatos; }
}