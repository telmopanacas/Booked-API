package com.telmopanacas.bookedapi.Repositories;

import com.telmopanacas.bookedapi.Models.Avaliacao;
import com.telmopanacas.bookedapi.Models.Livro;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class AvaliacaoRepositoryTest {

    @Autowired
    private AvaliacaoRepository underTest;

    @Autowired
    private LivroRepository livroRepository;


    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void itShouldFindByLivroTitle() {
        //given
        Livro livro = new Livro (
                "Drácula",
                "Bram Stoker",
                "978-989-52-8741-1"
        );
        livroRepository.save(livro);
        Avaliacao avaliacao = new Avaliacao(
                "Review",
                "Oscar Wilde",
                "Actually pretty good storytelling",
                5,
                1000,
                livro
        );
        underTest.save(avaliacao);

        //when
        boolean expected = underTest.findByLivroTitle(avaliacao.getLivro().getTitulo()).isPresent();

        //then
        assertTrue(expected);
    }

    @Test
    void itShouldNotFindByLivroTitle() {
        //given
        String titulo = "Drácula";

        //when
        boolean expected = underTest.findByLivroTitle(titulo).isPresent();

        //then
        assertFalse(expected);
    }

    @Test
    void itShouldFindByAuthor() {
        //given
        Livro livro = new Livro (
                "Drácula",
                "Bram Stoker",
                "978-989-52-8741-1"
        );
        livroRepository.save(livro);
        Avaliacao avaliacao = new Avaliacao(
                "Review",
                "Oscar Wilde",
                "Actually pretty good storytelling",
                5,
                1000,
                livro
        );
        underTest.save(avaliacao);

        //when
        boolean expected = underTest.findByAuthor(avaliacao.getAutor()).isPresent();

        //then
        assertTrue(expected);
    }

    @Test
    void itShouldNotFindByAutor() {
        //given
        String autor = "Oscar Wilde";

        //when
        boolean expected = underTest.findByAuthor(autor).isPresent();

        //then
        assertFalse(expected);
    }
}