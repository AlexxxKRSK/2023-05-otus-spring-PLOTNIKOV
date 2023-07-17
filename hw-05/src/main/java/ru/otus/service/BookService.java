package ru.otus.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.dao.BookRepository;
import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.domain.Genre;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final IOService ioService;

    private final BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.getAllBooks();
    }

    public boolean deleteBookById(Long id) {
        return bookRepository.deleteBookById(id);
    }

    public Book createBook() {
        var bookName = ioService.readStringWithPrompt("Enter book name");
        var authorName = ioService.readStringWithPrompt("Enter author");
        var genreName = ioService.readStringWithPrompt("Enter genre");
        var author = new Author(authorName);
        var genre = new Genre(genreName);
        var book = new Book(bookName, author, genre);

        return book;
    }
}
