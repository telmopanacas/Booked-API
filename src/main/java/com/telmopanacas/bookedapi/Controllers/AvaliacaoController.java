package com.telmopanacas.bookedapi.Controllers;

import com.telmopanacas.bookedapi.DTOs.AvaliacaoDTO;
import com.telmopanacas.bookedapi.Models.Avaliacao;
import com.telmopanacas.bookedapi.Models.Comentario;
import com.telmopanacas.bookedapi.Models.VoteRequest;
import com.telmopanacas.bookedapi.Services.AvaliacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/avaliacao")
public class AvaliacaoController {

    private final AvaliacaoService avaliacaoService;

    @Autowired
    public AvaliacaoController(AvaliacaoService avaliacaoService) {
        this.avaliacaoService = avaliacaoService;
    }

    @GetMapping(path = "/all")
    public List<AvaliacaoDTO> getAllAvaliacao() {
        return avaliacaoService.getAllAvaliacao();
    }

    @PostMapping(path = "/new")
    public void registerNewAvliacao(@RequestBody Avaliacao avaliacao) {
        avaliacaoService.addNewAvaliacao(avaliacao);
    }

    @GetMapping(path = "/{avaliacaoId}")
    public AvaliacaoDTO getAvaliacao(@PathVariable Long avaliacaoId) {
        return avaliacaoService.getAvalicao(avaliacaoId);
    }

    @DeleteMapping(path = "/{avaliacaoId}")
    public void deleteAvaliacao(@PathVariable Long avaliacaoId) {
        avaliacaoService.deleteAvaliacao(avaliacaoId);
    }

    @PutMapping(path = "/{avaliacaoId}")
    public void updateAvaliacao(
            @PathVariable("avaliacaoId") Long avaliacaoId,
            @RequestParam(required = false) String titulo,
            @RequestParam(required = false) String review,
            @RequestParam(required = false) Integer rating,
            @RequestParam(required = false) Integer votos
    ) {
        avaliacaoService.updateAvaliacao(avaliacaoId, titulo, review, rating, votos);
    }

    @GetMapping(path = "/{avaliacaoId}/comentarios/all")
    public List<Comentario> getAllComentarios(@PathVariable Long avaliacaoId) {
        return avaliacaoService.getAllComentarios(avaliacaoId);
    }

    @GetMapping("/search")
    public List<AvaliacaoDTO> findAvalicao(@RequestParam("searchInput") String searchInput) {
        return avaliacaoService.searchAvaliacao(searchInput);
    }

    @PostMapping("/upvote")
    public void upvoteAvaliacao(@RequestBody VoteRequest voteRequest) {
        avaliacaoService.upvoteAvaliacao(voteRequest);
    }

    @PostMapping("/downvote")
    public void downvoteAvaliacao(@RequestBody VoteRequest voteRequest) {
        avaliacaoService.downvoteAvaliacao(voteRequest);
    }
}
