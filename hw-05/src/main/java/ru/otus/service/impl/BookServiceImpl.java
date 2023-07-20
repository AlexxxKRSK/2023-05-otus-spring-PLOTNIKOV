package ru.otus.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.dao.BookRepository;
import ru.otus.domain.Book;
import ru.otus.service.AuthorService;
import ru.otus.service.BookService;
import ru.otus.service.GenreService;
import ru.otus.service.IOService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final IOService ioService;

    private final BookRepository bookRepository;

    private final AuthorService authorService;

    private final GenreService genreService;

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.getAllBooks();
    }

    @Override
    public boolean deleteBookById(Long id) {
        return bookRepository.deleteBookById(id);
    }

    @Override
    @Transactional
    public Book createBook() {
        var bookName = ioService.readStringWithPrompt("Enter book name");
        var author = authorService.getAuthor();
        var genre = genreService.getGenre();
        return bookRepository.saveBook(new Book(bookName, author, genre));
    }

    @Override
    public Book getBookById(Long id) {
        return bookRepository.getBookById(id).orElse(null);
    }

    @Override
    @Transactional
    public Book updateBook(Long id) {
        var book = bookRepository.getBookById(id).orElseThrow(() -> new RuntimeException("No book with such id!"));
        var bookName = ioService.readStringWithPrompt("Enter book name");
        var author = authorService.getAuthor();
        var genre = genreService.getGenre();
        book.setName(bookName);
        book.setAuthor(author);
        book.setGenre(genre);
        return bookRepository.updateBook(book);
    }
}
