package ru.otus.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.dao.BookRepository;
import ru.otus.domain.Book;
import ru.otus.service.AuthorService;
import ru.otus.service.BookService;
import ru.otus.service.GenreService;

import java.util.Collections;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    private final AuthorService authorService;

    private final GenreService genreService;

    private final ConversionService conversionService;

    @Override
    @Transactional(readOnly = true)
    public String getAllBooksString() {
        var books = bookRepository.getAllBooks();
        return books
                .stream()
                .map(b -> conversionService.convert(b, String.class))
                .collect(Collectors.joining("\n-------\n"));
    }

    @Transactional
    @Override
    public boolean deleteBookById(Long id) {
        return bookRepository.deleteBookById(id);
    }

    @Override
    @Transactional
    public String createBook(String bookName, String authorName, String genreName) {
        var author = authorService.getOrCreateAuthorByName(authorName);
        var genre = genreService.getOrCreateGenreByName(genreName);
        var savedBook = bookRepository.saveBook(new Book(bookName, author, genre, Collections.emptyList()));
        return conversionService.convert(savedBook, String.class);
    }

    @Override
    @Transactional(readOnly = true)
    public String getBookById(Long id) {
        var book = bookRepository.getBookById(id).orElse(null);
        return conversionService.convert(book, String.class);
    }

    @Override
    @Transactional
    public String updateBook(Long id, String bookName, String authorName, String genreName) {
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
        return conversionService.convert(book, String.class);
    }

}
