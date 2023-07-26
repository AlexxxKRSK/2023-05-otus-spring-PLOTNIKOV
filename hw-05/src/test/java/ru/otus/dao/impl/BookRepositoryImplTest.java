package ru.otus.dao.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.BookProvider;
import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.domain.Genre;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@JdbcTest
@Import(BookRepositoryImpl.class)
class BookRepositoryImplTest {

    private static final int EXPECTED_BOOKS_COUNT = 1;

    @Autowired
    private BookRepositoryImpl bookRepository;

    @Test
    void saveBookTest() {
        final long EXPECTED_ID = 2L;
        var bookToSave = new Book(
                "NEW BOOK",
                new Author(2L,"TEST AUTHOR 2"),
                new Genre(2L,"TEST GENRE 2")
        );
        bookRepository.saveBook(bookToSave);
        assertEquals(EXPECTED_ID, bookToSave.getId());
    }

    @Test
    void updateBookTest() {
        final String NEW_BOOK_NAME = "Updated book name";
        var bookToUpdate = BookProvider.getExistingBook();
        var actualBook = bookRepository.getBookById(bookToUpdate.getId());
        assertThat(actualBook).isPresent().get().usingRecursiveComparison().isEqualTo(bookToUpdate);

        bookToUpdate.setName(NEW_BOOK_NAME);
        bookRepository.updateBook(bookToUpdate);
        actualBook = bookRepository.getBookById(bookToUpdate.getId());
        assertThat(actualBook).isPresent().get().usingRecursiveComparison().isEqualTo(bookToUpdate);
    }

    @Test
    void getAllBooksTest() {
        List<Book> booksInDB = bookRepository.getAllBooks();
        assertEquals(EXPECTED_BOOKS_COUNT, booksInDB.size());
        assertThat(booksInDB).containsExactlyInAnyOrder(BookProvider.getExistingBook());
    }

    @Test
    void getBookByIdTest() {
        var expectedBook = BookProvider.getExistingBook();
        var actualBook = bookRepository.getBookById(expectedBook.getId());
        assertThat(actualBook).isPresent().get().usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @Test
    void deleteBookById() {
        var bookToDelete = BookProvider.getExistingBook();
        boolean res = bookRepository.deleteBookById(bookToDelete.getId());
        assertEquals(Boolean.TRUE, res);
        var deletedBook = bookRepository.getBookById(bookToDelete.getId());
        assertEquals(Optional.empty(), deletedBook);
    }
}