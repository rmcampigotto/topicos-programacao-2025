package com.example.times.e.campeonatos.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.*;

@Entity
public class Campeonato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private LocalDate dataInicio;
    private LocalDate dataFim;

    @ManyToMany
    @JoinTable(
            name = "campeonato_time",
            joinColumns = @JoinColumn(name = "campeonato_id"),
            inverseJoinColumns = @JoinColumn(name = "time_id")
    )
    private Set<Time> times = new HashSet<>();

    @ElementCollection
    @CollectionTable(name = "pontuacao", joinColumns = @JoinColumn(name = "campeonato_id"))
    @MapKeyJoinColumn(name = "time_id")
    @Column(name = "pontos")
    private Map<Time, Integer> classificacao = new HashMap<>();

    // Construtor vazio
    public Campeonato() {}

    // Construtor com parâmetros
    public Campeonato(String nome, LocalDate dataInicio, LocalDate dataFim) {
        this.nome = nome;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }

    // Métodos de negócio
    public void adicionarTime(Time time) {
        this.times.add(time);
        this.classificacao.put(time, 0);
    }

    public void registrarPontuacao(Time time, int pontos) {
        this.classificacao.put(time, this.classificacao.getOrDefault(time, 0) + pontos);
    }

    public List<Map.Entry<Time, Integer>> getClassificacaoOrdenada() {
        return this.classificacao.entrySet()
                .stream()
                .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
                .toList();
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public LocalDate getDataInicio() { return dataInicio; }
    public void setDataInicio(LocalDate dataInicio) { this.dataInicio = dataInicio; }

    public LocalDate getDataFim() { return dataFim; }
    public void setDataFim(LocalDate dataFim) { this.dataFim = dataFim; }

    public Set<Time> getTimes() { return times; }
    public void setTimes(Set<Time> times) { this.times = times; }

    public Map<Time, Integer> getClassificacao() { return classificacao; }
    public void setClassificacao(Map<Time, Integer> classificacao) { this.classificacao = classificacao; }
}