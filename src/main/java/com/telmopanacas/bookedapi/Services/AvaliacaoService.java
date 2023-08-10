package com.telmopanacas.bookedapi.Services;

import com.telmopanacas.bookedapi.Models.Avaliacao;
import com.telmopanacas.bookedapi.Models.Comentario;
import com.telmopanacas.bookedapi.Repositories.AvaliacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AvaliacaoService {


    private final AvaliacaoRepository avaliacaoRepository;
    @Autowired
    public AvaliacaoService(AvaliacaoRepository avaliacaoRepository) {
        this.avaliacaoRepository = avaliacaoRepository;
    }

    public List<Avaliacao> getAllAvaliacao() {
        return avaliacaoRepository.findAll();
    }

    public void addNewAvaliacao(Avaliacao avaliacao) {
        avaliacaoRepository.save(avaliacao);
    }

    public Avaliacao getAvalicao(Long avaliacaoId) {
        return avaliacaoRepository.findById(avaliacaoId)
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
