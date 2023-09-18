package com.telmopanacas.bookedapi.Services;

import com.telmopanacas.bookedapi.DTOs.AvaliacaoDTO;
import com.telmopanacas.bookedapi.Mappers.AvaliacaoDTOMapper;
import com.telmopanacas.bookedapi.Models.Avaliacao;
import com.telmopanacas.bookedapi.Models.Comentario;
import com.telmopanacas.bookedapi.Models.Livro;
import com.telmopanacas.bookedapi.Repositories.AvaliacaoRepository;
import com.telmopanacas.bookedapi.Repositories.LivroRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
/*
@ExtendWith(MockitoExtension.class)
class AvaliacaoServiceTest {

    @Mock
    private AvaliacaoRepository avaliacaoRepository;

    @Mock
    private LivroRepository livroRepository;
    private final AvaliacaoDTOMapper avaliacaoDTOMapper = new AvaliacaoDTOMapper();

    private AvaliacaoService underTest;

    @BeforeEach
    void setUp() {
        underTest = new AvaliacaoService(avaliacaoRepository, avaliacaoDTOMapper);
    }

    @Test
    void canGetAllAvaliacao() {
        //when
        underTest.getAllAvaliacao();

        //then
        verify(avaliacaoRepository).findAll();
    }

    @Test
    void canAddNewAvaliacao() {
        //given
        Livro livro = new Livro (
                "Drácula",
                "Bram Stoker"
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

        //when
        underTest.addNewAvaliacao(avaliacao);

        //then
        ArgumentCaptor<Avaliacao> avaliacaoArgumentCaptor = ArgumentCaptor.forClass(Avaliacao.class);

        //É utilizado o ArgumentCaptor para capturar o argumento
        //que é passado para o método save do avaliacaoRepository.
        verify(avaliacaoRepository).save(avaliacaoArgumentCaptor.capture());

        Avaliacao capturedAvaliacao = avaliacaoArgumentCaptor.getValue();
        assertEquals(avaliacao, capturedAvaliacao);
    }

    @Test
    void canGetAvalicao() {
        //given
        Livro livro = new Livro (
                "Review",
                "Drácula"
        );
        livroRepository.save(livro);
        Long avaliacaoId = 1L;
        Avaliacao avaliacao = new Avaliacao(
                "Review",
                "Oscar Wilde",
                "Actually pretty good storytelling",
                5,
                1000,
                livro
        );
        avaliacao.setDataDeRegisto(new Timestamp(System.currentTimeMillis()));
        given(avaliacaoRepository.findById(avaliacaoId)).willReturn(Optional.of(avaliacao));

        AvaliacaoDTO expected = avaliacaoDTOMapper.apply(avaliacao);

        //when
        AvaliacaoDTO result = underTest.getAvalicao(avaliacaoId);

        //then
        assertEquals(expected, result);


        //Confirmar que a função `findById` foi chamada com o valor certo no Id
        verify(avaliacaoRepository).findById(avaliacaoId);
    }

    @Test
    void willThrowWhenAvaliacaoNotFound() {
        //given
        Long avaliacaoId = 1L;
        given(avaliacaoRepository.findById(avaliacaoId)).willReturn(Optional.empty());

        //when
        //then
        assertThatThrownBy(() -> underTest.getAvalicao(avaliacaoId))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("Avaliação com id "+ avaliacaoId + " não existe");
    }

    @Test
    void canDeleteAvaliacao() {
        //given
        Long avaliacaoId = 1L;
        given(avaliacaoRepository.existsById(avaliacaoId)).willReturn(true);

        //when
        underTest.deleteAvaliacao(avaliacaoId);

        //then
        verify(avaliacaoRepository).deleteById(avaliacaoId);
    }

    @Test
    void willThrowWhenDeleteAvaliacaoNotFound() {
        //given
        Long avaliacaoId = 1L;
        given(avaliacaoRepository.existsById(avaliacaoId)).willReturn(false);

        //when
        //then
        assertThatThrownBy(() -> underTest.deleteAvaliacao(avaliacaoId))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("Avaliação com id "+ avaliacaoId + " não existe");
    }

    @Test
    void canUpdateAvaliacao() {
        //given
        Livro livro = new Livro (
                "Drácula",
                "Bram Stocker"
        );
        livroRepository.save(livro);
        Long avaliacaoId = 1L;
        Avaliacao avaliacao = new Avaliacao(
                "Review",
                "Oscar Wilde",
                "Actually pretty good storytelling",
                5,
                1000,
                livro
        );
        given(avaliacaoRepository.findById(avaliacaoId)).willReturn(Optional.of(avaliacao));

        //when
        underTest.updateAvaliacao(avaliacaoId, "Review 1","Not that great", 3, 500);

        //then
        ArgumentCaptor<Avaliacao> avaliacaoArgumentCaptor = ArgumentCaptor.forClass(Avaliacao.class);
        verify(avaliacaoRepository).save(avaliacaoArgumentCaptor.capture());

        Avaliacao capturedAvaliacao = avaliacaoArgumentCaptor.getValue();

        assertEquals("Review 1", capturedAvaliacao.getTitulo());
        assertEquals("Not that great", capturedAvaliacao.getReview());
        assertEquals(3, capturedAvaliacao.getRating());
        assertEquals(500, capturedAvaliacao.getVotos());
    }

    @Test
    void willThrowWhenUpdateAvaliacaoNotFound() {
        //given
        Long avaliacaoId = 1L;
        given(avaliacaoRepository.findById(avaliacaoId)).willReturn(Optional.empty());

        //when
        //then
        assertThatThrownBy(() -> underTest.updateAvaliacao(avaliacaoId, "Review","Not that great", 3, 500))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("Avaliação com id "+ avaliacaoId + " não existe");
    }

    @Test
    void canGetAllComentarios() {
        //given
        Long avaliacaoId = 1L;
        //given
        Livro livro = new Livro(
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
        avaliacao.getComentarios().add(comentario);
        given(avaliacaoRepository.findById(avaliacaoId)).willReturn(Optional.of(avaliacao));

        //when
        List<Comentario> result = underTest.getAllComentarios(avaliacaoId);

        //then
        assertEquals(avaliacao.getComentarios(), result);
    }

}
*/