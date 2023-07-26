package ru.otus.controller;

import lombok.NonNull;
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

    @ShellMethod(value = "Create book. Example: create book-name author-name genre-name", key = "create")
    public String createBook(
            @ShellOption @NonNull String bookName,
            @ShellOption @NonNull String authorName,
            @ShellOption @NonNull String genreName) {
        var book = bookService.createBook(bookName, authorName, genreName);
        return conversionService.convert(book, String.class);
    }

    @ShellMethod(value = "Get all books. Example: books", key = "books")
    public String getAllBooks() {
        var books = bookService.getAllBooks();
        return books
                .stream()
                .map(b -> conversionService.convert(b, String.class))
                .collect(Collectors.joining("\n-------\n"));
    }

    @ShellMethod(value = "Get book by id. Example: book id", key = "book")
    public String getBookById(@ShellOption Long id) {
        var book = bookService.getBookById(id);
        return conversionService.convert(book, String.class);
    }

    @ShellMethod(value = "Delete book by id. Example: delete id", key = "delete")
    public boolean deleteBookById(@ShellOption Long id) {
        return bookService.deleteBookById(id);
    }

    @ShellMethod(
            value = "Update book by id. Example: update id book-name author-name genre-name; update id book-name",
            key = "update"
    )
    public String updateBookById(@ShellOption Long id,
                                 @ShellOption @NonNull String bookName,
                                 @ShellOption String authorName,
                                 @ShellOption String genreName) {
        var book = bookService.updateBook(id, bookName, authorName, genreName);
        return conversionService.convert(book, String.class);
    }
}
