package ru.otus.service;

import ru.otus.dto.CommentDto;

public interface CommentService {

    CommentDto addComment(String bookId, String commentText);

    boolean deleteCommentById(String commentId);

}
