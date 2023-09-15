package ru.otus.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;
import ru.otus.domain.Genre;


public interface GenreRepository extends ReactiveMongoRepository<Genre, Long> {

    Mono<Genre> getGenreByName(String name);

}