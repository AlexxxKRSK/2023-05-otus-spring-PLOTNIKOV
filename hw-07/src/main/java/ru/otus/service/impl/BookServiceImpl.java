package ru.otus.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.domain.Book;
import ru.otus.domain.mappers.BookMapper;
import ru.otus.dto.BookDto;
import ru.otus.dto.BookWithCommentDto;
import ru.otus.repository.BookRepository;
import ru.otus.service.AuthorService;
import ru.otus.service.BookService;
import ru.otus.service.GenreService;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    private final AuthorService authorService;

    private final GenreService genreService;

    private final BookMapper bookMapper;

    @Override
    @Transactional(readOnly = true)
    public List<BookDto> getAllBooks() {
        var books = bookRepository.findAll();
        return books.stream()
                .map(bookMapper::toDto)
                .toList();
    }

    @Transactional
    @Override
    public boolean deleteBookById(Long id) {
        return bookRepository.deleteBookById(id) == 1;
    }

    @Override
    @Transactional
    public BookDto createBook(String bookName, String authorName, String genreName) {
        var author = authorService.getOrCreateAuthorByName(authorName);
        var genre = genreService.getOrCreateGenreByName(genreName);
        var savedBook = bookRepository.save(new Book(bookName, author, genre, Collections.emptyList()));
        return bookMapper.toDto(savedBook);
    }

    @Override
    @Transactional(readOnly = true)
    public BookWithCommentDto getBookById(Long id) {
        var book = bookRepository.getBookById(id).orElse(null);
        return bookMapper.toDtoWithComments(book);
    }

    @Override
    @Transactional
    public BookWithCommentDto updateBook(Long id, String bookName, String authorName, String genreName) {
        var book = bookRepository.getBookById(id).orElseThrow(() -> new RuntimeException("No book with such id!"));
        book.setName(bookName);
        if (authorName != null && !authorName.isEmpty()) {
            var author = authorService.getOrCreateAuthorByName(authorName);
            book.setAuthor(author);
        }
        if (genreName != null && !genreName.isEmpty()) {
            var genre = genreService.getOrCreateGenreByName(genreName);
            book.setGenre(genre);
        }
        return bookMapper.toDtoWithComments(book);
    }

}
