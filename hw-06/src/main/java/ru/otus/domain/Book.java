package ru.otus.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "BOOKS")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Fetch(FetchMode.JOIN)
    @ManyToOne(targetEntity = Author.class, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "AUTHOR_ID")
    private Author author;

    @Fetch(FetchMode.JOIN)
    @ManyToOne(targetEntity = Genre.class, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "GENRE_ID")
    private Genre genre;

    @Fetch(FetchMode.JOIN)
    @OneToMany(targetEntity = Comment.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "BOOK_ID", nullable = false)
    private List<Comment> commentList;

    public Book(String name, Author author, Genre genre, List<Comment> commentList) {
        this.name = name;
        this.author = author;
        this.genre = genre;
        this.commentList = commentList;
    }
}