package ru.otus.dao.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.dao.AuthorRepository;
import ru.otus.domain.Author;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AuthorRepositoryImpl implements AuthorRepository {

    private final NamedParameterJdbcOperations jdbc;

    @Override
    public Author saveAuthor(Author author) {
        var keyHolder = new GeneratedKeyHolder();
        jdbc.update(
                "INSERT INTO AUTHORS (NAME) VALUES (:name)",
                new MapSqlParameterSource("name", author.getName()),
                keyHolder
        );
        author.setId((Long) keyHolder.getKey());
        return author;
    }

    @Override
    public Optional<Author> getAuthorByName(String name) {
        try {
            return Optional.ofNullable(
                    jdbc.queryForObject(
                            "SELECT ID, NAME FROM AUTHORS WHERE NAME=:name",
                            Map.of("name", name),
                            new AuthorMapper()
                    )
            );
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    private static class AuthorMapper implements RowMapper<Author> {

        @Override
        public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Author(rs.getLong("ID"), rs.getString("NAME"));
        }
    }
}
