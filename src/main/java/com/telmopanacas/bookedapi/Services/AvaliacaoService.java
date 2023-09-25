package com.telmopanacas.bookedapi.Services;

import com.telmopanacas.bookedapi.DTOs.AvaliacaoDTO;
import com.telmopanacas.bookedapi.Exceptions.ApiRequestException;
import com.telmopanacas.bookedapi.Mappers.AvaliacaoDTOMapper;
import com.telmopanacas.bookedapi.Models.Avaliacao;
import com.telmopanacas.bookedapi.Models.Comentario;
import com.telmopanacas.bookedapi.Repositories.AvaliacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
                .orElseThrow(() -> new ApiRequestException("Review with id "+ avaliacaoId + " doesn't exist."));
    }

    public void deleteAvaliacao(Long avaliacaoId) {
        boolean exists = avaliacaoRepository.existsById(avaliacaoId);
        if (exists) {
            avaliacaoRepository.deleteById(avaliacaoId);
        }else {
            throw  new ApiRequestException("Review with id "+ avaliacaoId + " doesn't exist.");
        }
    }

    @Transactional
    public void updateAvaliacao(Long avaliacaoId, String titulo, String review, Integer  rating, Integer votos) {
        Avaliacao avaliacao = avaliacaoRepository.findById(avaliacaoId)
                .orElseThrow(() -> new ApiRequestException("Review with id "+ avaliacaoId + " doesn't exist."));

        if(titulo != null && !titulo.isEmpty() && !avaliacao.getTitulo().equals(titulo)) {
            avaliacao.setTitulo(titulo);
        }

        if(review != null && !review.isEmpty() && !avaliacao.getReview().equals(review)) {
            avaliacao.setReview(review);
        }

        if(rating != null && rating != avaliacao.getRating()) {
            avaliacao.setRating(rating);
        }

        if(votos != null && votos != avaliacao.getVotos()) {
            avaliacao.setVotos(votos);
        }

        avaliacaoRepository.save(avaliacao);
    }

    public List<Comentario> getAllComentarios(Long avaliacaoId) {
        Avaliacao avaliacao = avaliacaoRepository.findById(avaliacaoId)
                .orElseThrow(() -> new ApiRequestException("Review with id "+ avaliacaoId + " doesn't exist."));

        return avaliacao.getComentarios();
    }

    public List<AvaliacaoDTO> searchAvaliacao(String searchInput) {
        return avaliacaoRepository.findAll()
                .stream()
                .filter(
                        avaliacao -> avaliacao.getTitulo().toLowerCase().contains(searchInput.toLowerCase())
                        || avaliacao.getLivro().getAutor().toLowerCase().contains(searchInput.toLowerCase())
                        || avaliacao.getLivro().getTitulo().toLowerCase().contains(searchInput.toLowerCase())
                        || avaliacao.getUser().getDisplayName().toLowerCase().contains(searchInput.toLowerCase())
                )
                .map(avaliacaoDTOMapper)
                .collect(Collectors.toList());
    }
}
