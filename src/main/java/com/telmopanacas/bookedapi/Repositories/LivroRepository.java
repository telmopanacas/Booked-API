package com.telmopanacas.bookedapi.Repositories;

import com.telmopanacas.bookedapi.Models.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {

    @Query("SELECT livro FROM Livro livro WHERE livro.titulo = ?1")
    Optional<Livro> findByTitulo(String titulo);

    @Query("SELECT livro FROM Livro livro WHERE livro.isbn = ?1")
    Optional<Livro> findByIsbn(String isbn);
}
