package com.telmopanacas.bookedapi.Controllers;

import com.telmopanacas.bookedapi.Models.Comentario;
import com.telmopanacas.bookedapi.Services.ComentarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping(path = "/api/v1/comentario")
public class ComentarioController {

    private final ComentarioService comentarioService;

    @Autowired
    public ComentarioController(ComentarioService comentarioService) {
        this.comentarioService = comentarioService;
    }

    @GetMapping(path = "/all")
    public List<Comentario> getAllComentario() {
        return comentarioService.getAllComentario();
    }

    @PostMapping(path = "/new")
    public void registerNewComentario(@RequestBody Comentario comentario) {
        comentarioService.addNewComentario(comentario);
    }

    @GetMapping(path = "/{comentarioId}")
    public Comentario getComentario(@PathVariable Long comentarioId) {
        return comentarioService.getComentario(comentarioId);
    }

    @DeleteMapping(path = "/{comentarioId}")
    public void deleteComentario(@PathVariable Long comentarioId) {
        comentarioService.deleteComentario(comentarioId);
    }

    @PutMapping(path = "/{comentarioId}")
    public void updateComentario(
            @PathVariable("comentarioId") Long comentarioId,
            @RequestParam(required = false) String mensagem,
            @RequestParam(required = false) int votos
    ) {
        comentarioService.updateComentario(comentarioId, mensagem, votos);
    }
}
