package com.telmopanacas.bookedapi.Models;

import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Entity
public class Comentario {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private long id;
    private String autor;
    private String mensagem;
    private int votos;
    @Column(name = "created_at")
    @CreationTimestamp
    private Timestamp dataDeRegisto;

    @ManyToOne
    @JoinColumn(name = "avaliacao_id")
    private Avaliacao avaliacao;

    public Comentario() {
    }

    public Comentario(long id, String autor, String mensagem, int votos, Timestamp dataDeRegisto, Avaliacao avaliacao) {
        this.id = id;
        this.autor = autor;
        this.mensagem = mensagem;
        this.votos = votos;
        this.dataDeRegisto = dataDeRegisto;
        this.avaliacao = avaliacao;
    }

    public Comentario(String autor, String mensagem, int votos, Avaliacao avaliacao) {
        this.autor = autor;
        this.mensagem = mensagem;
        this.votos = votos;
        this.avaliacao = avaliacao;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public int getVotos() {
        return votos;
    }

    public void setVotos(int votos) {
        this.votos = votos;
    }

    public Timestamp getDataDeRegisto() {
        return dataDeRegisto;
    }

    public void setDataDeRegisto(Timestamp dataDeRegisto) {
        this.dataDeRegisto = dataDeRegisto;
    }

    public Avaliacao getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(Avaliacao avaliacao) {
        this.avaliacao = avaliacao;
    }

    @Override
    public String toString() {
        return "Comentario{" +
                "id=" + id +
                ", autor='" + autor + '\'' +
                ", mensagem='" + mensagem + '\'' +
                ", votos=" + votos +
                ", dataDeRegisto=" + dataDeRegisto +
                ", avaliacao=" + avaliacao.getId() +
                '}';
    }
}
