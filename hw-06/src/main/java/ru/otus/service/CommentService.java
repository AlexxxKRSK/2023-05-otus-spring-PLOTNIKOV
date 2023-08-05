package ru.otus.service;

import ru.otus.dto.CommentDto;

public interface CommentService {

    CommentDto addComment(Long bookId, String commentText);

    boolean deleteCommentById(Long commentId);

}
