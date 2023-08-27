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
import static ru.otus.BookProvider.getExistingBook;
import static ru.otus.BookProvider.getExistingBookDto;
import static ru.otus.BookProvider.getExistingBookWithComment;
import static ru.otus.BookProvider.getExistingBookWithCommentDto;

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
        when(bookRepository.findAll()).thenReturn(List.of(expectedBook));
        var books = bookService.getAllBooks();
        assertThat(books).usingRecursiveFieldByFieldElementComparator().containsExactlyInAnyOrder(expectedResult);
    }

    @Test
    void deleteBookByIdTest() {
        var testBook = getExistingBookDto();
        when(bookRepository.deleteBookById(testBook.getId())).thenReturn(1);
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
        when(bookRepository.save(any())).thenReturn(expectedBook);
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
        final String updatedName = "Updated name";
        final String updatedAuthorName = "Updated author";
        final String updatedGenreName = "Updated genre";
        var testBook = getExistingBookWithComment();
        var expectedResult = getExistingBookWithCommentDto();
        expectedResult.setName(updatedName);
        expectedResult.setAuthor(updatedAuthorName);
        expectedResult.setGenre(updatedGenreName);
        var expectedBook = getExistingBookWithComment();
        expectedBook.setName(updatedName);
        expectedBook.getAuthor().setName(updatedAuthorName);
        expectedBook.getGenre().setName(updatedGenreName);
        when(bookRepository.getBookById(testBook.getId())).thenReturn(Optional.of(testBook));
        when(authorService.getOrCreateAuthorByName(expectedBook.getAuthor().getName())).thenReturn(expectedBook.getAuthor());
        when(genreService.getOrCreateGenreByName(expectedBook.getGenre().getName())).thenReturn(expectedBook.getGenre());
        var resultBook = bookService.updateBook(
                testBook.getId(),
                expectedBook.getName(),
                expectedBook.getAuthor().getName(),
                expectedBook.getGenre().getName()
        );
        assertThat(resultBook).usingRecursiveComparison().isEqualTo(expectedResult);
    }
}