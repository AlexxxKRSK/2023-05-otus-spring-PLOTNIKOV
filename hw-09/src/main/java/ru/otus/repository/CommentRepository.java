package ru.otus.repository;


import org.springframework.data.repository.CrudRepository;
import ru.otus.domain.Comment;


public interface CommentRepository extends CrudRepository<Comment, Long> {

    int deleteCommentById(Long id);
}