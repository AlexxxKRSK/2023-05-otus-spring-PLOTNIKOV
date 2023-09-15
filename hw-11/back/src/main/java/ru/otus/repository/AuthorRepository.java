package ru.otus.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;
import ru.otus.domain.Author;

public interface AuthorRepository extends ReactiveMongoRepository<Author, Long> {

    Mono<Author> getAuthorByName(String name);

}