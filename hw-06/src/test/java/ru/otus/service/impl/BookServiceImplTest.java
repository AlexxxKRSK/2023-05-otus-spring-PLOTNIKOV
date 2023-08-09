package ru.otus.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.repository.BookRepository;
import ru.otus.service.AuthorService;
import ru.otus.service.BookService;
import ru.otus.service.GenreService;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static ru.otus.BookProvider.*;

@SpringBootTest
class BookServiceImplTest {

    @MockBean
    private AuthorService authorService;

    @MockBean
    private GenreService genreService;

    @MockBean
    private BookRepository bookRepository;

    @Autowired
    private BookService bookService;

    @Test
    void getAllBooksTest() {
        var expectedBook = getExistingBook();
        var expectedResult = getExistingBookDto();
        when(bookRepository.getAllBooks()).thenReturn(List.of(expectedBook));
        var books = bookService.getAllBooks();
        assertThat(books).usingRecursiveFieldByFieldElementComparator().containsExactlyInAnyOrder(expectedResult);
    }

    @Test
    void deleteBookByIdTest() {
        var testBook = getExistingBookDto();
        when(bookRepository.deleteBookById(testBook.getId())).thenReturn(true);
        var result = bookService.deleteBookById(testBook.getId());
        verify(bookRepository).deleteBookById(testBook.getId());
        assertTrue(result);
    }

    @Test
    void createBook() {
        var testBook = getExistingBook();
        testBook.setId(null);
        var expectedBook = getExistingBook();
        var expectedResult = getExistingBookDto();
        when(authorService.getOrCreateAuthorByName(testBook.getAuthor().getName())).thenReturn(testBook.getAuthor());
        when(genreService.getOrCreateGenreByName(testBook.getGenre().getName())).thenReturn(testBook.getGenre());
        when(bookRepository.saveBook(any())).thenReturn(expectedBook);
        var resultBook = bookService.createBook(
                testBook.getName(),
                testBook.getAuthor().getName(),
                testBook.getGenre().getName()
        );

        assertThat(resultBook).usingRecursiveComparison().isEqualTo(expectedResult);
    }

    @Test
    void getBookById() {
        var expectedBook = getExistingBookWithComment();
        var expectedResult = getExistingBookWithCommentDto();
        when(bookRepository.getBookById(expectedBook.getId())).thenReturn(Optional.of(expectedBook));
        var resultBook = bookService.getBookById(expectedBook.getId());

        assertThat(resultBook).usingRecursiveComparison().isEqualTo(expectedResult);
    }

    @Test
    void updateBook() {
        final String UPDATED_NAME = "Updated name";
        final String UPDATED_AUTHOR_NAME = "Updated author";
        final String UPDATED_GENRE_NAME = "Updated genre";
        var testBook = getExistingBookWithComment();
        var expectedResult = getExistingBookWithCommentDto();
        expectedResult.setName(UPDATED_NAME);
        expectedResult.setAuthor(UPDATED_AUTHOR_NAME);
        expectedResult.setGenre(UPDATED_GENRE_NAME);
        var expectedBook = getExistingBookWithComment();
        expectedBook.setName(UPDATED_NAME);
        expectedBook.getAuthor().setName(UPDATED_AUTHOR_NAME);
        expectedBook.getGenre().setName(UPDATED_GENRE_NAME);

        when(bookRepository.getBookById(testBook.getId())).thenReturn(Optional.of(testBook));
        when(authorService.getOrCreateAuthorByName(expectedBook.getAuthor().getName())).thenReturn(expectedBook.getAuthor());
        when(genreService.getOrCreateGenreByName(expectedBook.getGenre().getName())).thenReturn(expectedBook.getGenre());
        when(bookRepository.updateBook(expectedBook)).thenReturn(expectedBook);
        var resultBook = bookService.updateBook(
                testBook.getId(),
                expectedBook.getName(),
                expectedBook.getAuthor().getName(),
                expectedBook.getGenre().getName()
        );

        assertThat(resultBook).usingRecursiveComparison().isEqualTo(expectedResult);
    }
}