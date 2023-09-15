package ru.otus.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;
import ru.otus.domain.Book;


public interface BookRepository extends ReactiveMongoRepository<Book, Long> {

    Mono<Book> getBookById(String id);

    Mono<Long> deleteBookById(String id);

}