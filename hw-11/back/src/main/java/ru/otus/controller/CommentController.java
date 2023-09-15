package ru.otus.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import ru.otus.domain.Comment;
import ru.otus.repository.CommentRepository;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentRepository commentRepository;

    @GetMapping("/book/{book-id}/comment")
    public Flux<Comment> listCommentsByBookId(@PathVariable("book-id") String bookId) {
        return commentRepository.findAllByBookId(bookId);
    }

}
