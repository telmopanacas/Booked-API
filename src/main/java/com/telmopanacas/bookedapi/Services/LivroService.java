package com.telmopanacas.bookedapi.Services;

import com.telmopanacas.bookedapi.Models.Livro;
import com.telmopanacas.bookedapi.Repositories.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LivroService {

    private final LivroRepository livroRepository;

    @Autowired
    public LivroService(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }


    public void getAllLivros() {
        livroRepository.findAll();
    }

    public void addNewLivro(Livro livro) {
        Optional<Livro> livroOptional = livroRepository.findByIsbn(livro.getIsbn());
        if(livroOptional.isPresent()) {
            throw new IllegalStateException("Livro com ISBN " + livro.getIsbn() + " já se encontra registado");
        }
        livroRepository.save(livro);
    }

    public Livro getLivro(Long livroId) {
        Livro livro = livroRepository.findById(livroId).orElseThrow(null);
        if(livro == null){
            throw new IllegalStateException("Livro com id " + livroId + " não existe");
        }
        return livro;
    }

    public void deleteLivro(Long livroId) {
        boolean exists = livroRepository.existsById(livroId);
        if(exists) {
            livroRepository.deleteById(livroId);
        }else {
            throw new IllegalStateException("Livro com id " + livroId + " não existe");
        }
    }
}
