package com.telmopanacas.bookedapi.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.telmopanacas.bookedapi.Security.User.User;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Avaliacao {

    @Id
    @GeneratedValue(
        strategy = GenerationType.IDENTITY
    )
    private int id;
    private String titulo;
    private String review;
    private int rating;
    private int votos;
    @Column(name = "created_at")
    @CreationTimestamp
    private Timestamp dataDeRegisto;
    @ManyToOne
    @JoinColumn(name = "livro_id")
    private Livro livro;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "avaliacao_id")
    @JsonIgnore
    private List<Comentario> comentarios = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Avaliacao() {
    }

    public Avaliacao(int id, String titulo, String review, int rating, int votos, Timestamp dataDeRegisto, Livro livro, List<Comentario> comentarios, User user) {
        this.id = id;
        this.titulo = titulo;
        this.review = review;
        this.rating = rating;
        this.votos = votos;
        this.dataDeRegisto = dataDeRegisto;
        this.livro = livro;
        this.comentarios = comentarios;
        this.user = user;
    }

    public Avaliacao(String titulo, String review, int rating, int votos, Livro livro, User user) {
        this.titulo = titulo;
        this.review = review;
        this.rating = rating;
        this.votos = votos;
        this.livro = livro;
        this.user = user;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String avaliacao) {
        this.review = avaliacao;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
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

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Avaliacao{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", review='" + review + '\'' +
                ", rating=" + rating +
                ", votos=" + votos +
                ", dataDeRegisto=" + dataDeRegisto +
                ", livro=" + livro +
                ", comentarios=" + comentarios +
                ", user=" + user +
                '}';
    }
}
