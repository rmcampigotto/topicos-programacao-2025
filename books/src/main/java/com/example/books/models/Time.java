package com.example.books.models;

import com.example.books.models.Competicao;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Time {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    // Relacionamento muitos-para-muitos com Competicao
    @ManyToMany
    @JoinTable(
            name = "time_competicao",
            joinColumns = @JoinColumn(name = "time_id"),
            inverseJoinColumns = @JoinColumn(name = "competicao_id")
    )
    private Set<Competicao> competicoes = new HashSet<>();

    // Construtor adicional sem 'id', por comodidade
    public Time(String nome) {
        this.nome = nome;
    }
}
