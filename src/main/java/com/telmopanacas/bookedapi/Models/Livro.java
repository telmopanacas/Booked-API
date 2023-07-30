package com.telmopanacas.bookedapi.Models;

import jakarta.persistence.*;

import java.time.format.DateTimeFormatter;
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
    private String dataDeRegisto;

    public Livro() {
    }

    public Livro(long id, String titulo, String autor, String isbn, String dataDeRegisto) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.dataDeRegisto = dataDeRegisto;
    }

    public Livro(String titulo, String autor, String isbn, String dataDeRegisto) {
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.dataDeRegisto = dataDeRegisto;
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

    public String getDataDeRegisto() {
        return dataDeRegisto;
    }

    public void setDataDeRegisto(String dataDeRegisto) {
        this.dataDeRegisto = dataDeRegisto;
    }

    @Override
    public String toString() {
        return "Livro{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", isbn='" + isbn + '\'' +
                ", dataDeRegisto='" + dataDeRegisto + '\'' +
                '}';
    }

    public Map<String, String> toJson() {
        return Map.of(
                "id", String.valueOf(id),
                "titulo", titulo,
                "autor", autor,
                "isbn", isbn,
                "dataDeRegisto", dataDeRegisto
        );
    }
}
