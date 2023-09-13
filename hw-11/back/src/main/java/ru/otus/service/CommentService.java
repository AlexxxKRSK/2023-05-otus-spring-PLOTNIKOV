package ru.otus.service;

import ru.otus.dto.CommentDto;

import java.util.List;

public interface CommentService {

    CommentDto addComment(String bookId, String commentText);

    boolean deleteCommentById(String commentId);

    List<CommentDto> getAllCommentsByBookId(String bookId);
}
