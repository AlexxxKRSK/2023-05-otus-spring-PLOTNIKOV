package ru.otus.service;

import org.springframework.transaction.annotation.Transactional;
import ru.otus.dto.CommentDto;

import java.util.List;

public interface CommentService {

    CommentDto addComment(Long bookId, String commentText);

    boolean deleteCommentById(Long commentId);

    @Transactional(readOnly = true)
    List<CommentDto> getAllCommentsByBookId(Long bookId);
}
