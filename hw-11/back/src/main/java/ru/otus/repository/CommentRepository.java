package ru.otus.repository;


import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.domain.Comment;


public interface CommentRepository extends ReactiveMongoRepository<Comment, Long> {

    Mono<Long> deleteCommentsByBookId(String bookId);

    Flux<Comment> findAllByBookId(String bookId);
}