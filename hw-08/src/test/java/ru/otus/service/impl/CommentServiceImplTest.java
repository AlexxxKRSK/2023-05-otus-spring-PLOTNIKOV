package ru.otus.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.domain.Comment;
import ru.otus.repository.BookRepository;
import ru.otus.repository.CommentRepository;
import ru.otus.service.CommentService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static ru.otus.BookProvider.getExistingBook;

@SpringBootTest
class CommentServiceImplTest {

    @MockBean
    private BookRepository bookRepository;

    @MockBean
    private CommentRepository commentRepository;

    @Autowired
    private CommentService commentService;

    @Test
    void addComment() {
        var testBook = getExistingBook();
        var commentText = "TEST COMMENT";
        when(bookRepository.getBookById(any())).thenReturn(Optional.of(testBook));
        when(commentRepository.save(any())).thenReturn(new Comment(commentText));
        var result = commentService.addComment(testBook.getId(), commentText);
        assertEquals(result.getText(), commentText);
    }
}