package com.biblioteca.livros.repositories;

import com.biblioteca.livros.models.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepository extends JpaRepository<Livro, Long> {
}
