package ru.otus.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import ru.otus.domain.Book;

@Repository
public interface BookRepository extends ReactiveMongoRepository<Book, Long> {

    Mono<Book> getBookById(String id);

    int deleteBookById(String id);
}