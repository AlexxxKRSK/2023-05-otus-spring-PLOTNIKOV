package ru.otus.service;

public interface CommentService {

    String addComment(Long bookId, String commentText);

    boolean deleteCommentById(Long commentId);

}
