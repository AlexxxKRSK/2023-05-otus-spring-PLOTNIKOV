package ru.otus.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Book {

    private Long id;

    private String name;

    private Author author;

    private Genre genre;

    public Book(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Book(String name, Author author, Genre genre) {
        this.name = name;
        this.author = author;
        this.genre = genre;
    }
}