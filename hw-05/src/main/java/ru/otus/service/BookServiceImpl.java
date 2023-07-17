package ru.otus.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.dao.BookRepository;
import ru.otus.domain.Book;

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
}
