package com.telmopanacas.bookedapi.Services;

import com.telmopanacas.bookedapi.Models.Avaliacao;
import com.telmopanacas.bookedapi.Models.Livro;
import com.telmopanacas.bookedapi.Repositories.LivroRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class LivroServiceTest {

    @Mock
    /*
    Como o LivroRepository já teve os seus testes feitos, não é necessário testar novamente.
    Logo podemos utilizar o @Mock para simular o comportamento do LivroRepository.
     */
    private LivroRepository livroRepository;

    private LivroService underTest;

    @BeforeEach
    void setUp() {
        underTest = new LivroService(livroRepository);
    }

    @Test
    void canGetAllLivros() {
        //when
        underTest.getAllLivros();
        //then
        verify(livroRepository).findAll();
    }

    @Test
    void canAddNewLivro() {
        //given
        Livro livro = new Livro (
                "Drácula",
                "Bram Stoker",
                "978-989-52-8741-1"
        );

        //when
        underTest.addNewLivro(livro);

        //then
        ArgumentCaptor<Livro> livroArgumentCaptor = ArgumentCaptor.forClass(Livro.class);
        /*
        É utilizado o ArgumentCaptor para capturar o argumento
        que é passado para o método save do livroRepository.
         */
        verify(livroRepository).save(livroArgumentCaptor.capture());

        Livro capturedLivro = livroArgumentCaptor.getValue();
        assertEquals(livro, capturedLivro);
    }

    @Test
    void willThrowWhenIsbnExists() {
        //given
        Livro livro = new Livro(
                "Drácula",
                "Bram Stoker",
                "978-989-52-8741-1"
        );

        // Simulando que o livro com o mesmo ISBN já existe no repositório
        given(livroRepository.findByIsbn(livro.getIsbn()))
                .willReturn(Optional.of(livro));

        //when
        //then
        assertThatThrownBy(() -> underTest.addNewLivro(livro))
                .hasMessage("Livro com ISBN " + livro.getIsbn() + " já se encontra registado");

        // Confirma que o método save nunca foi chamado
        verify(livroRepository, never()).save(any());
    }


    @Test
    public void canGetLivro() {
        // given
        Long livroId = 1L;
        Livro expected = new Livro(
                "Drácula",
                "Bram Stoker",
                "978-989-52-8741-1"
        );
        given(livroRepository.findById(livroId)).willReturn(Optional.of(expected));

        // when
        Livro result = underTest.getLivro(livroId);

        // then
        assertEquals(expected, result);
        verify(livroRepository).findById(livroId);
    }

    @Test
    public void willThrowWhenLivroNotFound() {
        //given
        Long livroId = 1L;
        given(livroRepository.findById(livroId)).willReturn(Optional.empty());

        //when
        //then
        assertThatThrownBy(() -> underTest.getLivro(livroId))
                .hasMessage("Livro com id " + livroId + " não existe");
    }


    @Test
    void canDeleteLivro() {
        //given
        Long livroId = 1L;
        given(livroRepository.existsById(livroId)).willReturn(true);

        //when
        underTest.deleteLivro(livroId);

        //then
        verify(livroRepository).deleteById(livroId);
    }

    @Test
    void willThrowWhenDeleteLivroNotFound() {
        //given
        Long livroId = 1L;
        given(livroRepository.existsById(livroId)).willReturn(false);

        //when
        //then
        assertThatThrownBy(() -> underTest.deleteLivro(livroId))
                .hasMessage("Livro com id " + livroId + " não existe");
    }

    @Test
    void canGetAvaliacoes() {
        //given
        Long livroId = 1L;
        Livro livro = new Livro(
                "Drácula",
                "Bram Stoker",
                "978-989-52-8741-1"
        );
        Avaliacao avaliacao = new Avaliacao(
                "Review",
                "Oscar Wilde",
                "Actually pretty good storytelling",
                5,
                1000,
                livro
        );
        livro.getAvaliacoes().add(avaliacao);
        given(livroRepository.findById(livroId)).willReturn(Optional.of(livro));

        //when
        List<Avaliacao> result = underTest.getAllAvaliacoes(livroId);

        //then
        assertEquals(livro.getAvaliacoes(), result);
    }
}