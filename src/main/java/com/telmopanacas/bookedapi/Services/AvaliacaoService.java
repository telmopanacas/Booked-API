package com.telmopanacas.bookedapi.Services;

import com.telmopanacas.bookedapi.DTOs.AvaliacaoDTO;
import com.telmopanacas.bookedapi.Exceptions.ApiRequestException;
import com.telmopanacas.bookedapi.Mappers.AvaliacaoDTOMapper;
import com.telmopanacas.bookedapi.Models.Avaliacao;
import com.telmopanacas.bookedapi.Models.Comentario;
import com.telmopanacas.bookedapi.Models.VoteRequest;
import com.telmopanacas.bookedapi.Repositories.AvaliacaoRepository;
import com.telmopanacas.bookedapi.Security.User.User;
import com.telmopanacas.bookedapi.Security.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AvaliacaoService {


    private final AvaliacaoRepository avaliacaoRepository;
    private final UserRepository userRepository;
    private final AvaliacaoDTOMapper avaliacaoDTOMapper;
    @Autowired
    public AvaliacaoService(AvaliacaoRepository avaliacaoRepository, UserRepository userRepository, AvaliacaoDTOMapper avaliacaoDTOMapper) {
        this.avaliacaoRepository = avaliacaoRepository;
        this.userRepository = userRepository;
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

    public void upvoteAvaliacao(VoteRequest voteRequest) {
        Integer userId = voteRequest.getUserId();
        Integer avaliacaoId = voteRequest.getAvaliacaoId();

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ApiRequestException("User with id " + userId + " doesn't exist."));
        Avaliacao avaliacao = avaliacaoRepository.findById(Long.valueOf(avaliacaoId))
                .orElseThrow(() -> new ApiRequestException("Avaliacao with id " + avaliacaoId + " doesn't exist."));

        if ( user.getUpvotedAvaliacoesIds().contains(avaliacaoId) ) {
            user.getUpvotedAvaliacoes().remove(avaliacao);
            avaliacao.setVotos(avaliacao.getVotos() - 1);
            userRepository.save(user);
            avaliacaoRepository.save(avaliacao);
            return;
        }

        user.getDownvotedAvaliacoes().remove(avaliacao);
        user.getUpvotedAvaliacoes().add(avaliacao);
        avaliacao.setVotos(avaliacao.getVotos() + 1);
        userRepository.save(user);
        avaliacaoRepository.save(avaliacao);
    }

    public void downvoteAvaliacao(VoteRequest voteRequest) {
        Integer userId = voteRequest.getUserId();
        Integer avaliacaoId = voteRequest.getAvaliacaoId();

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ApiRequestException("User with id " + userId + " doesn't exist."));
        Avaliacao avaliacao = avaliacaoRepository.findById(Long.valueOf(avaliacaoId))
                .orElseThrow(() -> new ApiRequestException("Avaliacao with id " + avaliacaoId + " doesn't exist."));

        if ( user.getDownvotedAvaliacoesIds().contains(avaliacaoId) ) {
            user.getDownvotedAvaliacoes().remove(avaliacao);
            avaliacao.setVotos(avaliacao.getVotos() + 1);
            userRepository.save(user);
            avaliacaoRepository.save(avaliacao);
            return;
        }
        user.getUpvotedAvaliacoes().remove(avaliacao);
        user.getDownvotedAvaliacoes().add(avaliacao);
        avaliacao.setVotos(avaliacao.getVotos() - 1);
        userRepository.save(user);
        avaliacaoRepository.save(avaliacao);
    }
}
