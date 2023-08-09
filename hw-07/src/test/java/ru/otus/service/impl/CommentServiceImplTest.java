package ru.otus.service.impl;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.repository.BookRepository;
import ru.otus.service.CommentService;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static ru.otus.BookProvider.getCommentDto;
import static ru.otus.BookProvider.getExistingBook;

@SpringBootTest
class CommentServiceImplTest {

    @Mock
    private BookRepository bookRepository;

    @Autowired
    private CommentService commentService;

    @Test
    void addComment() {
        var testBook = getExistingBook();
        var expectedResult = getCommentDto();
        when(bookRepository.getBookById(any())).thenReturn(Optional.of(testBook));
        var result = commentService.addComment(testBook.getId(),
                expectedResult.getText());
        assertThat(result).usingRecursiveComparison().isEqualTo(expectedResult);
    }
}