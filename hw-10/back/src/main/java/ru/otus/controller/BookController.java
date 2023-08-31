package ru.otus.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.dto.BookDto;
import ru.otus.dto.BookWithCommentDto;
import ru.otus.service.BookService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping("/book/create")
    public ResponseEntity<BookDto> createBook(@RequestBody BookDto dto) {
        return ResponseEntity.ok()
                .body(bookService.createBook(dto.getName(), dto.getAuthor(), dto.getGenre()));
    }

    @GetMapping("/book/list")
    public ResponseEntity<List<BookDto>> getAllBooks() {
        return ResponseEntity.ok().body(bookService.getAllBooks());
    }

    @GetMapping("/book/{book-id}/get")
    public ResponseEntity<BookWithCommentDto> getBookById(@PathVariable("book-id") Long id) {
        return ResponseEntity.ok()
                .body(bookService.getBookById(id));
    }

    @DeleteMapping("/book/{book-id}/delete")
    public ResponseEntity<Boolean> deleteBookById(@PathVariable("book-id") Long id) {
        return ResponseEntity.ok()
                .body(bookService.deleteBookById(id));
    }

    @PutMapping("/book/update")
    public ResponseEntity<BookWithCommentDto> updateBook(@RequestBody BookDto dto) {
        return ResponseEntity.ok()
                .body(bookService.updateBook(dto.getId(), dto.getName(), dto.getAuthor(), dto.getGenre()));
    }

}
