package ru.otus;

import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.domain.Comment;
import ru.otus.domain.Genre;

import java.util.Collections;
import java.util.List;

public class BookProvider {

    public static Book getExistingBook() {
        var author = new Author(1L, "TEST AUTHOR");
        var genre = new Genre(1L, "TEST GENRE");
        return new Book(1L, "TEST BOOK", author, genre, Collections.emptyList());
    }

    public static Book getExistingBookWithComment() {
        var author = new Author(1L, "TEST AUTHOR");
        var genre = new Genre(1L, "TEST GENRE");
        var comment = new Comment("TEST COMMENT");
        var book = new Book(1L, "TEST BOOK", author, genre, List.of(comment));
        comment.setBook(book);
        return book;
    }
}
