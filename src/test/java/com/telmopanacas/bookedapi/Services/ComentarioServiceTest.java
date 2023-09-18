package com.telmopanacas.bookedapi.Services;

import com.telmopanacas.bookedapi.Models.Avaliacao;
import com.telmopanacas.bookedapi.Models.Comentario;
import com.telmopanacas.bookedapi.Models.Livro;
import com.telmopanacas.bookedapi.Repositories.AvaliacaoRepository;
import com.telmopanacas.bookedapi.Repositories.ComentarioRepository;
import com.telmopanacas.bookedapi.Repositories.LivroRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
/*
@ExtendWith(MockitoExtension.class)
class ComentarioServiceTest {

    @Mock
    private ComentarioRepository comentarioRepository;

    private ComentarioService underTest;

    @BeforeEach
    void setUp() {
        underTest = new ComentarioService(comentarioRepository);
    }

    @Test
    void canGetAllComentario() {
        //when
        underTest.getAllComentario();

        //then
        verify(comentarioRepository).findAll();
    }

    @Test
    void canAddNewComentario() {
        //given
        Livro livro = new Livro (
                "Drácula",
                "Bram Stoker"
        );
        Avaliacao avaliacao = new Avaliacao(
                "Review",
                "Oscar Wilde",
                "Actually pretty good storytelling",
                5,
                1000,
                livro
        );
        Comentario comentario = new Comentario(
                "Oscar Wilde",
                "Kinda based no?",
                0,
                avaliacao
        );

        //when
        underTest.addNewComentario(comentario);

        //then
        ArgumentCaptor<Comentario> comentarioArgumentCaptor = ArgumentCaptor.forClass(Comentario.class);

        //É utilizado o ArgumentCaptor para capturar o argumento
        //que é passado para o método save do comentarioRepository.
        verify(comentarioRepository).save(comentarioArgumentCaptor.capture());

        Comentario capturedComentario = comentarioArgumentCaptor.getValue();
        assertEquals(comentario, capturedComentario);
    }

    @Test
    void canGetComentario() {
        //given
        Livro livro = new Livro (
                "Drácula",
                "Bram Stoker"
        );
        Avaliacao avaliacao = new Avaliacao(
                "Review",
                "Oscar Wilde",
                "Actually pretty good storytelling",
                5,
                1000,
                livro
        );
        Long comentarioId = 1L;
        Comentario comentario = new Comentario(
                "Oscar Wilde",
                "Kinda based no?",
                0,
                avaliacao
        );
        given(comentarioRepository.findById(comentarioId)).willReturn(Optional.of(comentario));

        //when
        Comentario result = underTest.getComentario(comentarioId);

        //then
        assertEquals(comentario, result);


        //Confirmar que a função `findById` foi chamada com o valor certo no Id
        verify(comentarioRepository).findById(comentarioId);
    }

    @Test
    void willThrownWhenComentarioNotFound() {
        //given
        Long comentarioId = 1L;
        given(comentarioRepository.findById(comentarioId)).willReturn(Optional.empty());

        //when
        //then
        assertThatThrownBy(() -> underTest.getComentario(comentarioId))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("Comentário com id "+ comentarioId + " não existe");
    }

    @Test
    void canDeleteComentario() {
        //given
        Long comentarioId = 1L;
        given(comentarioRepository.existsById(comentarioId)).willReturn(true);

        //when
        underTest.deleteComentario(comentarioId);

        //then
        verify(comentarioRepository).deleteById(comentarioId);
    }

    @Test
    void willThrowWhenDeleteComentarioNotFound() {
        //given
        Long comentarioId = 1L;
        given(comentarioRepository.existsById(comentarioId)).willReturn(false);

        //when
        //then
        assertThatThrownBy(() -> underTest.deleteComentario(comentarioId))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("Comentário com id "+ comentarioId + " não existe");

    }
    @Test
    void canUpdateComentario() {
        //given
        Livro livro = new Livro (
                "Drácula",
                "Bram Stoker"
        );
        Avaliacao avaliacao = new Avaliacao(
                "Review",
                "Oscar Wilde",
                "Actually pretty good storytelling",
                5,
                1000,
                livro
        );
        Long comentarioId = 1L;
        Comentario comentario = new Comentario(
                "Oscar Wilde",
                "Kinda based no?",
                0,
                avaliacao
        );
        given(comentarioRepository.findById(comentarioId)).willReturn(Optional.of(comentario));

        //when
        underTest.updateComentario(comentarioId, "Kinda based no?\nedit: Actually not based at all.", 1);

        //then
        ArgumentCaptor<Comentario> comentarioArgumentCaptor = ArgumentCaptor.forClass(Comentario.class);
        verify(comentarioRepository).save(comentarioArgumentCaptor.capture());

        Comentario capturedComentario = comentarioArgumentCaptor.getValue();

        assertEquals("Kinda based no?\nedit: Actually not based at all.", capturedComentario.getMensagem());
        assertEquals(1, capturedComentario.getVotos());
    }

    @Test
    void willThrowWhenUpdateComentarioNotFound() {
        //given
        Long comentarioId = 1L;
        given(comentarioRepository.findById(comentarioId)).willReturn(Optional.empty());

        //when
        //then
        assertThatThrownBy(() -> underTest.updateComentario(comentarioId, "Kinda based no?\nedit: Actually not based at all.", 0))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("Comentário com id "+ comentarioId + " não existe");
    }
}

 */
