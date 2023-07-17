package ru.otus.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.domain.Book;
import ru.otus.service.BookService;

import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @ShellMethod(value = "Ping-pong", key = "ping")
    public String doSmthng() {
        return "pong";
    }

    @ShellMethod(value = "Get all books", key = "books")
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @ShellMethod(value = "Delete book by id", key = "delete")
    public boolean deleteBookById(@ShellOption Long id) {
        return bookService.deleteBookById(id);
    }

    @ShellMethod(value = "Create book", key = "create")
    public Book createBook() {
        return bookService.createBook();
    }
}
