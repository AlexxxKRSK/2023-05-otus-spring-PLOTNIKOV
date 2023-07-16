package ru.otus.dao;


import java.awt.print.Book;
import java.util.List;
import java.util.Optional;

public interface BookRepository {

    Book saveBook(Book book);

    List<Book> getAllBooks();

    Optional<Book> getBookById(Long id);

    boolean deleteBookById(Long id);
}