package ru.otus.service;

public interface BookService {

    String getAllBooksString();

    boolean deleteBookById(Long id);

    String createBook(String bookName, String authorName, String genreName);

    String getBookById(Long id);

    String updateBook(Long id, String bookName, String authorName, String genreName);

    String addComment(Long bookId, String commentText);

    boolean deleteCommentFromBook(Long bookId, Long commentId);
}
