package ru.otus.service;

import ru.otus.domain.Book;

import java.util.List;

public interface BookService {

    List<Book> getAllBooks();

    boolean deleteBookById(Long id);

    Book createBook();

    Book getBookById(Long id);

    Book updateBook(Long id);
}
