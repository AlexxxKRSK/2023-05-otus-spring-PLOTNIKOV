package ru.otus.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.otus.dao.GenreRepository;
import ru.otus.domain.Genre;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class GenreRepositoryImpl implements GenreRepository {

    private final EntityManager em;

    @Override
    public Genre saveGenre(Genre genre) {
        em.persist(genre);
        return genre;
    }

    @Override
    public Optional<Genre> getGenreByName(String name) {
        var query = em.createQuery("select g from Genre g where g.name = : name", Genre.class);
        query.setParameter("name", name);
        try {
            return Optional.of(query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }
}
