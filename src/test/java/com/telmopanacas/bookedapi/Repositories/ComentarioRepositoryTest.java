package com.telmopanacas.bookedapi.Repositories;

import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ComentarioRepositoryTest {

    @Autowired
    private ComentarioRepository underTest;

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    @Autowired
    private LivroRepository livroRepository;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }


}