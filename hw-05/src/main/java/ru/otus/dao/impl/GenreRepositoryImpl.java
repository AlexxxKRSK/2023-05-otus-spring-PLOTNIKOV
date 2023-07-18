package ru.otus.dao.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.dao.GenreRepository;
import ru.otus.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
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
            var res = jdbc.queryForObject(
                    "SELECT ID, NAME FROM GENRES WHERE NAME=:name",
                    Map.of("name", name),
                    new GenreMapper()
            );
            return Optional.ofNullable(res);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    private static class GenreMapper implements RowMapper<Genre> {

        @Override
        public Genre mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Genre(rs.getLong("ID"), rs.getString("NAME"));
        }
    }
}
