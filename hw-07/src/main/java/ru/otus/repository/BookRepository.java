package ru.otus.repository;


import org.springframework.data.repository.CrudRepository;
import ru.otus.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends CrudRepository<Book, Long> {

    List<Book> findAll();

    Optional<Book> getBookById(Long id);

    int deleteBookById(Long id);
}