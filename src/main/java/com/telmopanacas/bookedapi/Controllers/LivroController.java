package com.telmopanacas.bookedapi.Controllers;

import com.telmopanacas.bookedapi.Models.Avaliacao;
import com.telmopanacas.bookedapi.Models.Livro;
import com.telmopanacas.bookedapi.Services.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/livro")
public class LivroController {

    private final LivroService livroService;

    @Autowired
    public LivroController(LivroService livroService) {
        this.livroService = livroService;
    }

    @GetMapping(path = "/all")
    public List<Livro> getAllLivros() {
        return livroService.getAllLivros();
    }

    @PostMapping(path = "/new")
    public void registerNewLivro(@RequestBody Livro livro) {
        livroService.addNewLivro(livro);
    }

    @GetMapping(path = "/{livroId}")
    public Livro getLivro(@PathVariable Long livroId) {
        return livroService.getLivro(livroId);
    }

    @DeleteMapping(path = "/{livroId}")
    public void deleteLivro(@PathVariable Long livroId) {
        livroService.deleteLivro(livroId);
    }

    @GetMapping(path = "/{livroId}/avaliacoes/all")
    public List<Avaliacao> getAllAvaliacoes(@PathVariable Long livroId) {return livroService.getAllAvaliacoes(livroId);}
}
