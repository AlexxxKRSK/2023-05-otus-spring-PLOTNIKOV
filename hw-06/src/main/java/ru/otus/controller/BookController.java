package ru.otus.controller;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.service.BookService;
import ru.otus.service.CommentService;

@ShellComponent
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    private final CommentService commentService;

    @ShellMethod(value = "Create book. Example: create book-name author-name genre-name", key = "create")
    public String createBook(
            @ShellOption @NonNull String bookName,
            @ShellOption @NonNull String authorName,
            @ShellOption @NonNull String genreName) {
        return bookService.createBook(bookName, authorName, genreName);
    }

    @ShellMethod(value = "Get all books. Example: books", key = "books")
    public String getAllBooks() {
        return bookService.getAllBooksString();
    }

    @ShellMethod(value = "Get book by id. Example: book id", key = "book")
    public String getBookById(@ShellOption Long id) {
        return bookService.getBookById(id);
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
        return bookService.updateBook(id, bookName, authorName, genreName);
    }

    @ShellMethod(
            value = "Add comment to book by id. Example: add-comment book-id comment-text",
            key = "add-comment"
    )
    public String addCommentToBook(@ShellOption @NonNull Long bookId,
                                   @ShellOption @NonNull String commentText) {
        return commentService.addComment(bookId, commentText);
    }

    @ShellMethod(
            value = "Remove comment from book. Example: rm-comment book-id comment-id",
            key = "rm-comment"
    )
    public boolean deleteCommentById(@ShellOption @NonNull Long commentId) {
        return commentService.deleteCommentById(commentId);
    }
}
