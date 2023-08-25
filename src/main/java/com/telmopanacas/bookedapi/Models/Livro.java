package com.telmopanacas.bookedapi.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Entity
public class Livro {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private long id;
    private String titulo;
    private String autor;
    private String isbn;

    @Column(name = "created_at")
    @CreationTimestamp
    private Timestamp dataDeRegisto;

    @OneToMany
    @JoinColumn(name = "livro_id")
    @JsonIgnore
    private List<Avaliacao> avaliacoes = new ArrayList<>();

    public Livro() {
    }

    public Livro(long id, String titulo, String autor, String isbn, Timestamp dataDeRegisto, List<Avaliacao> avaliacoes) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.dataDeRegisto = dataDeRegisto;
        this.avaliacoes = avaliacoes;
    }

    public Livro(String titulo, String autor, String isbn, List<Avaliacao> avaliacoes) {
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.avaliacoes = avaliacoes;
    }

    public Livro(String titulo, String autor, String isbn) {
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Timestamp getDataDeRegisto() {
        return dataDeRegisto;
    }

    public void setDataDeRegisto(Timestamp dataDeRegisto) {
        this.dataDeRegisto = dataDeRegisto;
    }

    public List<Avaliacao> getAvaliacoes() {
        return avaliacoes;
    }

    public void setAvaliacoes(List<Avaliacao> avaliacoes) {
        this.avaliacoes = avaliacoes;
    }



    @Override
    public String toString() {
        return "Livro{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", isbn='" + isbn + '\'' +
                ", dataDeRegisto='" + dataDeRegisto + '\'' +
                ", avaliacoes=" + avaliacoes +
                '}';
    }
}
