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
        var query = em.createQuery("select b.commentList from Book b " +
                "where b.id = :bookId", Comment.class);
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
        var bookOpt = getCommentById(id);
        if (bookOpt.isPresent()) {
            em.remove(bookOpt.get());
            return true;
        } else {
            return false;
        }
    }
}
