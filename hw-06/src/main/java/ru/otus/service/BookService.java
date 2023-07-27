package ru.otus.service;

import ru.otus.domain.Book;

import java.util.List;

public interface BookService {

    List<Book> getAllBooks();

    boolean deleteBookById(Long id);

    Book createBook(String bookName, String authorName, String genreName);

    Book getBookById(Long id);

    Book updateBook(Long id, String bookName, String authorName, String genreName);

    Book addComment(Long bookId, String commentText);

    Book deleteCommentFromBook(Long bookId, Long commentId);
}
