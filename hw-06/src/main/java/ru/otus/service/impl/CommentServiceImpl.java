package ru.otus.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.dao.BookRepository;
import ru.otus.dao.CommentRepository;
import ru.otus.domain.Comment;
import ru.otus.service.CommentService;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final BookRepository bookRepository;

    private final CommentRepository commentRepository;

    private final ConversionService conversionService;
    
    @Override
    @Transactional
    public String addComment(Long bookId, String commentText) {
        var comment = new Comment(commentText);
        var book = bookRepository.getBookById(bookId).orElseThrow(() -> new RuntimeException("No book with such id!"));
        book.getCommentList().add(comment);
        comment.setBook(book);
        return conversionService.convert(book, String.class);
    }

    @Override
    @Transactional
    public boolean deleteCommentById(Long commentId) {
        return commentRepository.deleteCommentById(commentId);
    }
}
