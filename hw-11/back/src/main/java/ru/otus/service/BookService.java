package ru.otus.service;

import ru.otus.dto.BookDto;
import ru.otus.dto.BookWithCommentDto;

import java.util.List;

public interface BookService {

    List<BookDto> getAllBooks();

    boolean deleteBookById(String id);

    BookDto createBook(String bookName, String authorName, String genreName);

    BookWithCommentDto getBookById(String id);

    BookWithCommentDto updateBook(String id, String bookName, String authorName, String genreName);

}
