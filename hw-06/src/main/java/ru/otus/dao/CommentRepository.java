package ru.otus.dao;


import ru.otus.domain.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepository {

    Comment saveComment(Comment comment);

    Comment updateComment(Comment Comment);

    List<Comment> getCommentsByBookId(Long id);

    Optional<Comment> getCommentById(Long id);

    boolean deleteCommentById(Long id);
}