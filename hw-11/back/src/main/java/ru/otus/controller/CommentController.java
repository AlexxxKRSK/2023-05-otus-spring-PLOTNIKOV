package ru.otus.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.dto.CommentDto;
import ru.otus.service.CommentService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {

//    private final CommentService commentService;
//
//    @PostMapping("book/{book-id}/comment")
//    public ResponseEntity<CommentDto> createComment(@PathVariable("book-id") String bookId,
//                                                    @RequestBody CommentDto dto) {
//        return ResponseEntity.ok()
//                .body(commentService.addComment(bookId, dto.getText()));
//    }
//
//    @GetMapping("/book/{book-id}/comment")
//    public ResponseEntity<List<CommentDto>> listCommentsByBookId(@PathVariable("book-id") String bookId) {
//        return ResponseEntity.ok()
//                .body(commentService.getAllCommentsByBookId(bookId));
//    }
//
//    @DeleteMapping("/comment/{id}")
//    public ResponseEntity<Boolean> deleteCommentById(@PathVariable("id") String commentId) {
//        return ResponseEntity.ok()
//                .body(commentService.deleteCommentById(commentId));
//    }
}
