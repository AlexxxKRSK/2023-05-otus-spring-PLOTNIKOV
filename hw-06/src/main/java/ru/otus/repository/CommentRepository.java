package ru.otus.repository;


import ru.otus.domain.Comment;

import java.util.Optional;

public interface CommentRepository {

    Comment saveComment(Comment comment);

    Comment updateComment(Comment Comment);

    Optional<Comment> getCommentById(Long id);

    boolean deleteCommentById(Long id);
}