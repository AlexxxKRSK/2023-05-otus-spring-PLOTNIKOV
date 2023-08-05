package ru.otus.service;

import ru.otus.dto.BookDto;
import ru.otus.dto.BookWithCommentDto;

import java.util.List;

public interface BookService {

    List<BookDto> getAllBooks();

    boolean deleteBookById(Long id);

    BookDto createBook(String bookName, String authorName, String genreName);

    BookWithCommentDto getBookById(Long id);

    BookWithCommentDto updateBook(Long id, String bookName, String authorName, String genreName);

}
