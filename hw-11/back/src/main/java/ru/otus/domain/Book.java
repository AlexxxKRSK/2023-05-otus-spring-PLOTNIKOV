package ru.otus.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "books")
public class Book {

    @Id
    private String id;

    private String name;

    @DocumentReference(lazy = true)
    private Author author;

    @DocumentReference(lazy = true)
    private Genre genre;

    @DBRef(lazy = true)
    private List<Comment> commentList;

    public Book(String name, Author author, Genre genre, List<Comment> commentList) {
        this.name = name;
        this.author = author;
        this.genre = genre;
        this.commentList = commentList;
    }
}