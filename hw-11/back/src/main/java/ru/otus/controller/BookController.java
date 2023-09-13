package ru.otus.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import ru.otus.domain.mappers.BookMapper;
import ru.otus.dto.BookDto;
import ru.otus.repository.BookRepository;

@RestController
@RequiredArgsConstructor
public class BookController {

    private final BookRepository bookRepository;

    private final BookMapper bookMapper;

//    private final BookService bookService;

//    @PostMapping("/book")
//    public ResponseEntity<BookDto> createBook(@RequestBody BookDto dto) {
//        return ResponseEntity.ok()
//                .body(bookService.createBook(dto.getName(), dto.getAuthor(), dto.getGenre()));
//    }

    @GetMapping("/book")
    public Flux<BookDto> getAllBooks() {
//        return ResponseEntity.ok().body(bookService.getAllBooks());
        return bookRepository.findAll().map(bookMapper::toDto);
    }
//
//    @GetMapping("/book/{book-id}")
//    public ResponseEntity<BookWithCommentDto> getBookById(@PathVariable("book-id") String id) {
//        return ResponseEntity.ok()
//                .body(bookService.getBookById(id));
//    }
//
//    @DeleteMapping("/book/{book-id}")
//    public ResponseEntity<Boolean> deleteBookById(@PathVariable("book-id") String id) {
//        return ResponseEntity.ok()
//                .body(bookService.deleteBookById(id));
//    }
//
//    @PutMapping("/book")
//    public ResponseEntity<BookWithCommentDto> updateBook(@RequestBody BookDto dto) {
//        return ResponseEntity.ok()
//                .body(bookService.updateBook(dto.getId(), dto.getName(), dto.getAuthor(), dto.getGenre()));
//    }

}
