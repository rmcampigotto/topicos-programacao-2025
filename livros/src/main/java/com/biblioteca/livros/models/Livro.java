package com.biblioteca.livros.models;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "livros")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    String titulo;
    Date data_lancamento;
    String autor;
    Integer qtde_paginas;
    Double preco;

    public Livro(String titulo, Date data_lancamento, String autor, Integer qtde_paginas, Double preco) {
        this.titulo = titulo;
        this.data_lancamento = data_lancamento;
        this.autor = autor;
        this.qtde_paginas = qtde_paginas;
        this.preco = preco;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Date getData_lancamento() {
        return data_lancamento;
    }

    public void setData_lancamento(Date data_lancamento) {
        this.data_lancamento = data_lancamento;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Integer getQtde_paginas() {
        return qtde_paginas;
    }

    public void setQtde_paginas(Integer qtde_paginas) {
        this.qtde_paginas = qtde_paginas;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }
}
