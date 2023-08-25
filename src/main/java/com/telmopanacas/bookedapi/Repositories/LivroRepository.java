package com.telmopanacas.bookedapi.Repositories;

import com.telmopanacas.bookedapi.Models.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {

    @Query("SELECT livro FROM Livro livro WHERE livro.titulo = ?1")
    Optional<Livro> findByTitulo(String titulo);

    @Query("SELECT livro FROM Livro livro WHERE livro.autor = ?1")
    Optional<Livro> findByAutor(String autor);

    @Query("SELECT livro FROM Livro livro WHERE livro.titulo = ?1 AND livro.autor = ?2")
    Optional<Livro> findByTituloAndAutor(String titulo, String autor);
}
