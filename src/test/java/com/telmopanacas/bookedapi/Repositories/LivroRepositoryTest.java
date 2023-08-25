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
                "Bram Stoker"
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
        String titulo = "Drácula";

        //when
        boolean expected = underTest.findByTitulo(titulo).isPresent();

        //then
        assertFalse(expected);
    }

    @Test
    void itShouldFindLivroByAutor() {
        //given
        Livro livro = new Livro (
                "Drácula",
                "Bram Stoker"
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

    @Test
    void itShouldFindLivroByTituloEAutor() {
        //given
        Livro livro = new Livro(
                "Drácula",
                "Bram Stoker"
        );
        underTest.save(livro);

        //when
        boolean expected = underTest.findByTituloAndAutor(livro.getTitulo(), livro.getAutor()).isPresent();

        //then
        assertTrue(expected);
    }

    @Test
    void itShouldNotFindLivroByTituloEAutor() {
        //given
        String titulo = "Drácula";
        String autor = "Bram Stoker";

        //when
        boolean expected = underTest.findByTituloAndAutor(titulo, autor).isPresent();

        //then
        assertFalse(expected);
    }
}