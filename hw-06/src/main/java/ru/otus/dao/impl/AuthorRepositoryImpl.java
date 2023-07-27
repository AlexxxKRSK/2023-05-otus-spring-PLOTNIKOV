package ru.otus.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.otus.dao.AuthorRepository;
import ru.otus.domain.Author;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AuthorRepositoryImpl implements AuthorRepository {

    private final EntityManager em;

    @Override
    public Author saveAuthor(Author author) {
        em.persist(author);
        return author;
    }

    @Override
    public Optional<Author> getAuthorByName(String name) {
        var query = em.createQuery("select a from Author a where a.name = : name", Author.class);
        query.setParameter("name", name);
        try {
            return Optional.of(query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }
}
