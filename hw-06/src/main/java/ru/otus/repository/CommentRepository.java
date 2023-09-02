package ru.otus.repository;


import ru.otus.domain.Comment;

import java.util.Optional;

public interface CommentRepository {

    Comment saveComment(Comment comment);

    Comment updateComment(Comment comment);

    Optional<Comment> getCommentById(Long id);

    boolean deleteCommentById(Long id);
}