package ru.otus.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.domain.Comment;
import ru.otus.domain.mappers.CommentMapper;
import ru.otus.dto.CommentDto;
import ru.otus.repository.BookRepository;
import ru.otus.repository.CommentRepository;
import ru.otus.service.CommentService;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final BookRepository bookRepository;

    private final CommentRepository commentRepository;

    private final CommentMapper commentMapper;
    
    @Override
    @Transactional
    public CommentDto addComment(String bookId, String commentText) {
        var comment = new Comment(commentText);
        var book = bookRepository.getBookById(bookId).orElseThrow(() -> new RuntimeException("No book with such id!"));
        book.getCommentList().add(comment);
        comment.setBook(book);
        commentRepository.save(comment);
        bookRepository.save(book);
        return commentMapper.toDto(comment);
    }

    @Override
    @Transactional
    public boolean deleteCommentById(String commentId) {
        return commentRepository.deleteCommentById(commentId) == 1;
    }
}
