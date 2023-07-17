package ru.otus.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.domain.Genre;

import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class GenreRepositoryImpl implements GenreRepository {

    private final NamedParameterJdbcOperations jdbc;

    @Override
    public Genre saveGenre(Genre genre) {
        var keyHolder = new GeneratedKeyHolder();
        jdbc.update(
                "INSERT INTO GENRES (NAME) VALUES (:name)",
                new MapSqlParameterSource("name", genre.getName()),
                keyHolder
        );
        genre.setId((Long) keyHolder.getKey());
        return genre;
    }

    @Override
    public Optional<Genre> getGenreByName(String name) {
        try {
            return Optional.ofNullable(
                    jdbc.queryForObject(
                            "SELECT ID, NAME FROM GENRES WHERE NAME=:name",
                            Map.of("name", name),
                            Genre.class
                    )
            );
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }
}
