package ru.otus.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.service.BookService;

import java.util.stream.Collectors;

@ShellComponent
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    private final ConversionService conversionService;

    @ShellMethod(value = "Create book", key = "create")
    public String createBook() {
        var book = bookService.createBook();
        return conversionService.convert(book, String.class);
    }

    @ShellMethod(value = "Get all books", key = "books")
    public String getAllBooks() {
        var books = bookService.getAllBooks();
        return books
                .stream()
                .map(b -> conversionService.convert(b, String.class))
                .collect(Collectors.joining("\n-------\n"));
    }

    @ShellMethod(value = "Get book by id", key = "book")
    public String getBookById(@ShellOption Long id) {
        var book = bookService.getBookById(id);
        return conversionService.convert(book, String.class);
    }

    @ShellMethod(value = "Delete book by id", key = "delete")
    public boolean deleteBookById(@ShellOption Long id) {
        return bookService.deleteBookById(id);
    }

    @ShellMethod(value = "Update book by id", key = "update")
    public String updateBookById(@ShellOption Long id) {
        var book = bookService.updateBook(id);
        return conversionService.convert(book, String.class);
    }
}
