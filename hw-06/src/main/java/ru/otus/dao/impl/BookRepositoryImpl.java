package ru.otus.dao.impl;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.otus.dao.BookRepository;
import ru.otus.domain.Book;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class BookRepositoryImpl implements BookRepository {

    private final EntityManager em;

    @Override
    public Book saveBook(Book book) {
        if (book.getId() != null) {
            return updateBook(book);
        }
        em.persist(book);
        return book;
    }

    @Override
    public Book updateBook(Book book) {
        return em.merge(book);
    }

    @Override
    public List<Book> getAllBooks() {
        var query = em.createQuery("select distinct b from Book b " +
                        "left join fetch b.author " +
                        "left join fetch b.genre",
                Book.class);
        return query.getResultList();
    }

    @Override
    public Optional<Book> getBookById(Long id) {
        var query = em.find(Book.class, id);
        return Optional.ofNullable(query);
    }

    @Override
    public boolean deleteBookById(Long id) {
        var bookOpt = getBookById(id);
        if (bookOpt.isPresent()) {
            em.remove(bookOpt.get());
            return true;
        } else {
            return false;
        }
    }
}
