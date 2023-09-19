package com.telmopanacas.bookedapi.Repositories;

import com.telmopanacas.bookedapi.Models.Avaliacao;
import com.telmopanacas.bookedapi.Models.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long>{

    @Query(
            "SELECT avaliacao FROM Avaliacao avaliacao JOIN Livro livro ON avaliacao.livro.id = livro.id WHERE livro.titulo = ?1"
    )
    Optional<Avaliacao> findByLivroTitle(String livroTitle);

}
