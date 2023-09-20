package com.telmopanacas.bookedapi.Services;

import com.telmopanacas.bookedapi.Exceptions.ApiRequestException;
import com.telmopanacas.bookedapi.Models.Avaliacao;
import com.telmopanacas.bookedapi.Models.Livro;
import com.telmopanacas.bookedapi.Repositories.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LivroService {

    private final LivroRepository livroRepository;

    @Autowired
    public LivroService(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }


    public List<Livro> getAllLivros() {
        return livroRepository.findAll();
    }

    public void addNewLivro(Livro livro) {
        Optional<Livro> livroOptional = livroRepository.findByTituloAndAutor(livro.getTitulo(), livro.getAutor());
        if(livroOptional.isPresent()) {
            throw new ApiRequestException("Book with title " + livro.getTitulo() + " and author " + livro.getAutor() + " already exists.");
        }
        livroRepository.save(livro);
    }

    public Livro getLivro(Long livroId) {

        return livroRepository.findById(livroId)
                .orElseThrow(() -> new ApiRequestException("Book with id " + livroId + " doesn't exist.")
        );
    }

    public void deleteLivro(Long livroId) {
        boolean exists = livroRepository.existsById(livroId);
        if(exists) {
            livroRepository.deleteById(livroId);
        }else {
            throw new ApiRequestException("Book with id " + livroId + " doesn't exist.");
        }
    }

    public List<Avaliacao> getAllAvaliacoes(Long livroId) {
        Livro livro = livroRepository.findById(livroId)
                .orElseThrow(() -> new ApiRequestException("Book with id "+ livroId + " doesn't exist."));

        return livro.getAvaliacoes();
    }

    public Livro findLivroByTitleAndAuthor(String titulo, String autor) {

        return livroRepository.findByTituloAndAutor(titulo, autor).
                orElseThrow(() -> new ApiRequestException("Book with title " + titulo + " and author " + autor + " doesn't exist."));
    }
}
