package com.telmopanacas.bookedapi.Services;

import com.telmopanacas.bookedapi.Exceptions.ApiRequestException;
import com.telmopanacas.bookedapi.Models.Comentario;
import com.telmopanacas.bookedapi.Repositories.ComentarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComentarioService {

    private final ComentarioRepository comentarioRepository;

    @Autowired
    public ComentarioService(ComentarioRepository comentarioRepository) {
        this.comentarioRepository = comentarioRepository;
    }


    public List<Comentario> getAllComentario() {
        return comentarioRepository.findAll();
    }

    public void addNewComentario(Comentario comentario) {
        comentarioRepository.save(comentario);
    }

    public Comentario getComentario(Long comentarioId) {

        return comentarioRepository.findById(comentarioId)
                .orElseThrow(() -> new ApiRequestException("Comment with id "+ comentarioId + " doesn't exist."));
    }

    public void deleteComentario(Long comentarioId) {
        boolean exists = comentarioRepository.existsById(comentarioId);
        if(exists) {
            comentarioRepository.deleteById(comentarioId);
        } else {
            throw new ApiRequestException("Comment with id "+ comentarioId + " doesn't exist.");
        }
    }

    public void updateComentario(Long comentarioId, String mensagem, int votos) {
        Comentario comentario = comentarioRepository.findById(comentarioId)
                .orElseThrow(() -> new ApiRequestException("Comment with id "+ comentarioId + " doesn't exist."));

        if(mensagem != null && !mensagem.isEmpty() && !comentario.getMensagem().equals(mensagem)) {
            comentario.setMensagem(mensagem);
        }

        if(votos != comentario.getVotos()) {
            comentario.setVotos(votos);
        }

        comentarioRepository.save(comentario);
    }
}
