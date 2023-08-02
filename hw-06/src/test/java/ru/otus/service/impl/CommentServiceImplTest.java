package ru.otus.service.impl;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.convert.ConversionService;
import ru.otus.dao.BookRepository;
import ru.otus.service.CommentService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static ru.otus.BookProvider.getExistingBook;
import static ru.otus.BookProvider.getExistingBookWithComment;

@SpringBootTest
class CommentServiceImplTest {

    @Mock
    private BookRepository bookRepository;

    @Autowired
    private ConversionService conversionService;

    @Autowired
    private CommentService commentService;

    @Test
    void addComment() {
        var testBook = getExistingBook();
        var expectedBook = getExistingBookWithComment();
        when(bookRepository.getBookById(any())).thenReturn(Optional.of(testBook));
        var result = commentService.addComment(testBook.getId(),
                expectedBook.getCommentList().get(0).getText());
        assertEquals(conversionService.convert(expectedBook, String.class), result);
    }
}