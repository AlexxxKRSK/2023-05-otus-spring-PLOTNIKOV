package ru.otus.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.domain.Genre;
import ru.otus.domain.mappers.BookMapper;
import ru.otus.dto.BookDto;
import ru.otus.dto.BookWithCommentDto;
import ru.otus.repository.AuthorRepository;
import ru.otus.repository.BookRepository;
import ru.otus.repository.GenreRepository;

import java.util.Collections;

@RestController
@RequiredArgsConstructor
public class BookController {

    private final BookRepository bookRepository;

    private final BookMapper bookMapper;

    private final AuthorRepository authorRepository;

    private final GenreRepository genreRepository;

    @PostMapping("/book")
    public Mono<BookDto> createBook(@RequestBody BookDto dto) {
        var author = authorRepository.getAuthorByName(dto.getAuthor())
                .switchIfEmpty(authorRepository.save(new Author(dto.getAuthor())));
        var genre = genreRepository.getGenreByName(dto.getGenre())
                .switchIfEmpty(genreRepository.save(new Genre(dto.getGenre())));
        return Mono.zip(
                        author,
                        genre,
                        (auth, ge) -> new Book(dto.getName(), auth, ge, Collections.emptyList())
                )
                .flatMap(bookRepository::save)
                .map(bookMapper::toDto);
    }

    @GetMapping("/book")
    public Flux<BookDto> getAllBooks() {
        return bookRepository.findAll().map(bookMapper::toDto);
    }

    @GetMapping("/book/{book-id}")
    public Mono<BookWithCommentDto> getBookById(@PathVariable("book-id") String id) {
        return bookRepository.getBookById(id).map(bookMapper::toDtoWithComments);
    }

    @DeleteMapping("/book/{book-id}")
    public Mono<Boolean> deleteBookById(@PathVariable("book-id") String id) {
        return bookRepository.deleteBookById(id).map(i -> i ==1);
    }

    @PutMapping("/book")
    public Mono<BookWithCommentDto> updateBook(@RequestBody BookDto dto) {
        return bookRepository.getBookById(dto.getId())
                .switchIfEmpty(Mono.fromCallable(() -> {
                    throw new RuntimeException("No book with such id!");
                }))
                .flatMap(b -> {
                    b.setName(dto.getName());
                    Mono.just(dto.getAuthor()).filter(auth -> auth != null && !auth.isEmpty())
                            .flatMap(authorName -> authorRepository.getAuthorByName(authorName)
                                    .switchIfEmpty(authorRepository.save(new Author(authorName)))
                                    .map(author -> {
                                        b.setAuthor(author);
                                        return author;
                                    }));
                    Mono.just(dto.getGenre()).filter(ge -> ge != null && !ge.isEmpty())
                            .flatMap(genreName -> authorRepository.getAuthorByName(genreName)
                                    .switchIfEmpty(authorRepository.save(new Author(genreName)))
                                    .map(author -> {
                                        b.setAuthor(author);
                                        return author;
                                    }));
                    return bookRepository.save(b);
                })
                .map(bookMapper::toDtoWithComments);
    }

}
