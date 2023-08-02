package ru.otus.dao.impl;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.otus.dao.CommentRepository;
import ru.otus.domain.Comment;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CommentRepositoryImpl implements CommentRepository {

    private final EntityManager em;

    @Override
    public Comment saveComment(Comment comment) {
        if (comment.getId() != null) {
            return updateComment(comment);
        }
        em.persist(comment);
        return comment;
    }

    @Override
    public Comment updateComment(Comment comment) {
        return em.merge(comment);
    }

    @Override
    public List<Comment> getCommentsByBookId(Long id) {
        var query = em.createQuery("select c from Comment c " +
                "where c.book.id = :bookId", Comment.class);
        query.setParameter("bookId", id);
        return query.getResultList();
    }

    @Override
    public Optional<Comment> getCommentById(Long id) {
        var comment = em.find(Comment.class, id);
        return Optional.ofNullable(comment);
    }

    @Override
    public boolean deleteCommentById(Long id) {
        var commentOpt = getCommentById(id);
        if (commentOpt.isPresent()) {
            em.remove(commentOpt.get());
            return true;
        } else {
            return false;
        }
    }
}
