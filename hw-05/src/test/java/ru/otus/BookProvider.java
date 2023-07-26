package ru.otus;

import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.domain.Genre;

public class BookProvider {

    public static Book getExistingBook() {
        var author = new Author(1L, "TEST AUTHOR");
        var genre = new Genre(1L, "TEST GENRE");
        return new Book(1L, "TEST BOOK", author, genre);

    }
}
