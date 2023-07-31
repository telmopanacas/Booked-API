package com.telmopanacas.bookedapi.Repositories;

import com.telmopanacas.bookedapi.Models.Livro;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class LivroRepositoryTest {

    @Autowired
    private LivroRepository underTest;

    /*
    A cada iteração do teste, o método tearDown() é chamado para limpar a base de dados.
     */
    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void itShouldFindLivroByTitulo() {
        //given
        Livro livro = new Livro (
                "Drácula",
                "Bram Stoker",
                "978-989-52-8741-1"
        );
        underTest.save(livro);

        //when
        boolean expected = underTest.findByTitulo(livro.getTitulo()).isPresent();

        //then
        assertTrue(expected);
    }

    @Test
    void itShouldNotFindLivroByTitulo() {
        //given
        String titulo = "Drácula 2";

        //when
        boolean expected = underTest.findByTitulo(titulo).isPresent();

        //then
        assertFalse(expected);
    }

    @Test
    void itShouldFindLivroByIsbn() {
        //given
        Livro livro = new Livro (
                "Drácula",
                "Bram Stoker",
                "978-989-52-8741-1"
        );
        underTest.save(livro);

        //when
        boolean expected = underTest.findByIsbn(livro.getIsbn()).isPresent();

        //then
        assertTrue(expected);
    }

    @Test
    void itShouldNotFindLivroByIsbn() {
        //given
        String titulo = "978-989-52-8741-1";

        //when
        boolean expected = underTest.findByIsbn(titulo).isPresent();

        //then
        assertFalse(expected);
    }

    @Test
    void itShouldFindLivroByAutor() {
        //given
        Livro livro = new Livro (
                "Drácula",
                "Bram Stoker",
                "978-989-52-8741-1"
        );
        underTest.save(livro);

        //when
        boolean expected = underTest.findByAutor(livro.getAutor()).isPresent();

        //then
        assertTrue(expected);
    }

    @Test
    void itShouldNotFindLivroByAutor() {
        //given
        String titulo = "Bram Stoker";

        //when
        boolean expected = underTest.findByAutor(titulo).isPresent();

        //then
        assertFalse(expected);
    }
}