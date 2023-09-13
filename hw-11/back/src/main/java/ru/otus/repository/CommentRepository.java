package ru.otus.repository;


import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import ru.otus.domain.Comment;



public interface CommentRepository extends ReactiveMongoRepository<Comment, Long> {

    int deleteCommentById(String id);

    int deleteCommentsByBookId(String bookId);

    Flux<Comment> findAllByBookId(String bookId);
}