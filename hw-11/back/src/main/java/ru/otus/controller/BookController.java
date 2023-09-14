package ru.otus.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.domain.Book;
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
    public Mono<Book> createBook(@RequestBody BookDto dto) {
        var book = Flux.zip(
                authorRepository.getAuthorByName(dto.getAuthor()),
                genreRepository.getGenreByName(dto.getGenre()),
                (a, b) -> new Book(dto.getName(), a, b, Collections.emptyList())
        ).blockFirst();
        return bookRepository.save(book);
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
    public ResponseEntity<Boolean> deleteBookById(@PathVariable("book-id") String id) {
        return ResponseEntity.ok().body(bookRepository.deleteBookById(id) == 1);
    }
//
//    @PutMapping("/book")
//    public ResponseEntity<BookWithCommentDto> updateBook(@RequestBody BookDto dto) {
//        return ResponseEntity.ok()
//                .body(bookService.updateBook(dto.getId(), dto.getName(), dto.getAuthor(), dto.getGenre()));
//    }

}
