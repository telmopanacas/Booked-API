package com.telmopanacas.bookedapi.Services;

import com.telmopanacas.bookedapi.DTOs.AvaliacaoDTO;
import com.telmopanacas.bookedapi.Mappers.AvaliacaoDTOMapper;
import com.telmopanacas.bookedapi.Models.Avaliacao;
import com.telmopanacas.bookedapi.Models.Comentario;
import com.telmopanacas.bookedapi.Repositories.AvaliacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AvaliacaoService {


    private final AvaliacaoRepository avaliacaoRepository;
    private final AvaliacaoDTOMapper avaliacaoDTOMapper;
    @Autowired
    public AvaliacaoService(AvaliacaoRepository avaliacaoRepository, AvaliacaoDTOMapper avaliacaoDTOMapper) {
        this.avaliacaoRepository = avaliacaoRepository;
        this.avaliacaoDTOMapper = avaliacaoDTOMapper;
    }

    public List<AvaliacaoDTO> getAllAvaliacao() {
        return avaliacaoRepository.findAll()
                .stream()
                .map(avaliacaoDTOMapper)
                .collect(Collectors.toList());
    }

    public void addNewAvaliacao(Avaliacao avaliacao) {
        avaliacaoRepository.save(avaliacao);
    }

    public AvaliacaoDTO getAvalicao(Long avaliacaoId) {
        return avaliacaoRepository.findById(avaliacaoId)
                .map(avaliacaoDTOMapper)
                .orElseThrow(() -> new IllegalStateException("Avaliação com id "+ avaliacaoId + " não existe"));
    }

    public void deleteAvaliacao(Long avaliacaoId) {
        boolean exists = avaliacaoRepository.existsById(avaliacaoId);
        if (exists) {
            avaliacaoRepository.deleteById(avaliacaoId);
        }else {
            throw  new IllegalStateException("Avaliação com id "+ avaliacaoId + " não existe");
        }
    }

    public void updateAvaliacao(Long avaliacaoId, String titulo, String review, int rating, int votos) {
        Avaliacao avaliacao = avaliacaoRepository.findById(avaliacaoId)
                .orElseThrow(() -> new IllegalStateException("Avaliação com id "+ avaliacaoId + " não existe"));

        if(titulo != null && titulo.length() > 0 && !avaliacao.getTitulo().equals(titulo)) {
            avaliacao.setTitulo(titulo);
        }

        if(review != null && review.length() > 0 && !avaliacao.getReview().equals(review)) {
            avaliacao.setReview(review);
        }

        if(rating != avaliacao.getRating()) {
            avaliacao.setRating(rating);
        }

        if(votos != avaliacao.getVotos()) {
            avaliacao.setVotos(votos);
        }

        avaliacaoRepository.save(avaliacao);
    }

    public List<Comentario> getAllComentarios(Long avaliacaoId) {
        Avaliacao avaliacao = avaliacaoRepository.findById(avaliacaoId)
                .orElseThrow(() -> new IllegalStateException("Avaliação com id "+ avaliacaoId + " não existe"));

        return avaliacao.getComentarios();
    }
}
