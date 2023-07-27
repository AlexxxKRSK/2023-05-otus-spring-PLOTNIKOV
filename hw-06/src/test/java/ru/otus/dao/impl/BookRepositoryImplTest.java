package ru.otus.dao.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.BookProvider;
import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.domain.Comment;
import ru.otus.domain.Genre;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@Import(BookRepositoryImpl.class)
class BookRepositoryImplTest {

    private static final int EXPECTED_BOOKS_COUNT = 1;

    private static final Long EXISTING_BOOK_ID = 1L;

    @Autowired
    private BookRepositoryImpl bookRepository;

    @Autowired
    TestEntityManager em;

    @Test
    void saveBookTest() {
        var bookToSave = new Book(
                "NEW BOOK",
                new Author("TEST AUTHOR 3"),
                new Genre("TEST GENRE 3"),
                List.of(new Comment("TEST COMMENT"))
        );
        var savedBook = bookRepository.saveBook(bookToSave);
        var actualBook = em.find(Book.class, savedBook.getId());
        assertThat(savedBook).usingRecursiveComparison().isEqualTo(actualBook);
    }

    @Test
    void updateBookTest() {
        final String NEW_BOOK_NAME = "Updated book name";
        var bookToUpdate = em.find(Book.class, EXISTING_BOOK_ID);
        em.detach(bookToUpdate);

        bookToUpdate.setName(NEW_BOOK_NAME);
        var updatedBook = bookRepository.updateBook(bookToUpdate);
        var actualBook = em.find(Book.class, EXISTING_BOOK_ID);
        assertThat(actualBook).usingRecursiveComparison().isEqualTo(updatedBook);
    }

    @Test
    void getAllBooksTest() {
        List<Book> booksInDB = bookRepository.getAllBooks();
        assertEquals(EXPECTED_BOOKS_COUNT, booksInDB.size());
        var bp = BookProvider.getExistingBook();
        assertThat(booksInDB).usingRecursiveFieldByFieldElementComparator().containsExactlyInAnyOrder(bp);
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