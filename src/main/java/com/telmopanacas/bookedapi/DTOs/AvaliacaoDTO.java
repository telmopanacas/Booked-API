package com.telmopanacas.bookedapi.DTOs;

public record AvaliacaoDTO (
       Integer id,
       String tituloAvaliacao,
       String autorAvaliacao,
       String review,
       Integer rating,
       Integer votos,
       String dataAvaliacao,
       String horaAvaliacao,
       String tituloLivro,
       String autorLivro
) {
}
