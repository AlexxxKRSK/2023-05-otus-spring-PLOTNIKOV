package ru.otus.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.dao.BookRepository;
import ru.otus.domain.Book;
import ru.otus.domain.Comment;
import ru.otus.service.AuthorService;
import ru.otus.service.BookService;
import ru.otus.service.GenreService;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    private final AuthorService authorService;

    private final GenreService genreService;

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.getAllBooks();
    }

    @Transactional
    @Override
    public boolean deleteBookById(Long id) {
        return bookRepository.deleteBookById(id);
    }

    @Override
    @Transactional
    public Book createBook(String bookName, String authorName, String genreName) {
        var author = authorService.getOrCreateAuthorByName(authorName);
        var genre = genreService.getOrCreateGenreByName(genreName);
        return bookRepository.saveBook(new Book(bookName, author, genre, Collections.emptyList()));
    }

    @Override
    @Transactional(readOnly = true)
    public Book getBookById(Long id) {
        return bookRepository.getBookById(id).orElse(null);
    }

    @Override
    @Transactional
    public Book updateBook(Long id, String bookName, String authorName, String genreName) {
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
        return book;
    }

    @Override
    @Transactional
    public Book addComment(Long bookId, String commentText) {
        var comment = new Comment(commentText);
        var book = bookRepository.getBookById(bookId).orElseThrow(() -> new RuntimeException("No book with such id!"));
        book.getCommentList().add(comment);
        return book;
    }

    @Override
    @Transactional
    public Book deleteCommentFromBook(Long bookId, Long commentId) {
        var book = bookRepository.getBookById(bookId).orElseThrow(() -> new RuntimeException("No book with such id!"));
        book.getCommentList().removeIf(c -> Objects.equals(commentId, c.getId()));
        return book;
    }
}
