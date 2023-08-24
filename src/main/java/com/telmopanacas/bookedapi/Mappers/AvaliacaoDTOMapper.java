package com.telmopanacas.bookedapi.Mappers;

import com.telmopanacas.bookedapi.DTOs.AvaliacaoDTO;
import com.telmopanacas.bookedapi.Models.Avaliacao;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.function.Function;

@Service
public class AvaliacaoDTOMapper implements Function<Avaliacao, AvaliacaoDTO> {
    @Override
    public AvaliacaoDTO apply(Avaliacao avaliacao) {
        /*
        We need to format the date and time to a more readable format

        So firstly we split the date and time into two different strings.
        Then for the date we use the SimpleDateFormat class to parse the date into a Date object
        Then we use the SimpleDateFormat class again to format the date into the format we want

        For the time we just split the string and get the first two elements of the array and join them with a ":"
         */
        String[] dataHoraOriginal = avaliacao.getDataDeRegisto().toString().split(" ");

        SimpleDateFormat formatoOriginal = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat formatoNovo = new SimpleDateFormat("dd-MM-yyyy");
        Date data;
        try {
            data = formatoOriginal.parse(dataHoraOriginal[0]);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        String dataFormatada = formatoNovo.format(data);

        String[] hora = dataHoraOriginal[1].split(":");
        String horaFormatada = hora[0] + ":" + hora[1];

        return new AvaliacaoDTO(
                avaliacao.getId(),
                avaliacao.getTitulo(),
                avaliacao.getAutor(),
                avaliacao.getReview(),
                avaliacao.getRating(),
                dataFormatada,
                horaFormatada,
                avaliacao.getLivro().getTitulo(),
                avaliacao.getLivro().getAutor()
        );
    }
}
