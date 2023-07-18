package ru.otus.dao.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.dao.BookRepository;
import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class BookRepositoryImpl implements BookRepository {

    private final NamedParameterJdbcOperations jdbc;

    @Override
    public Book saveBook(Book book) {
        if (book.getId() != null) {
            return updateBook(book);
        }
        var keyHolder = new GeneratedKeyHolder();
        var params = new MapSqlParameterSource();
        params.addValue("name", book.getName());
        params.addValue("author_id", book.getAuthor().getId());
        params.addValue("genre_id", book.getGenre().getId());
        jdbc.update(
                "INSERT INTO BOOKS (NAME, AUTHOR_ID, GENRE_ID) " +
                        "VALUES (:name, :author_id, :genre_id)",
                params,
                keyHolder
        );
        book.setId((Long) keyHolder.getKey());
        return book;
    }

    @Override
    public Book updateBook(Book book) {
        var params = Map.of(
                "id", book.getId(),
                "name", book.getName(),
                "author_id", book.getAuthor().getId(),
                "genre_id", book.getGenre().getId());
        jdbc.update(
                "UPDATE BOOKS SET NAME=:name, AUTHOR_ID=:author_id, GENRE_ID=:genre_id " +
                        "WHERE ID=:id",
                params
        );
        return book;
    }

    @Override
    public List<Book> getAllBooks() {
        return jdbc.query(
                "SELECT b.ID, b.NAME, " +
                        "a.ID as AUTHOR_ID, a.NAME as AUTHOR_NAME, " +
                        "g.ID as GENRE_ID, g.NAME as GENRE_NAME " +
                        "FROM BOOKS b " +
                        "LEFT JOIN AUTHORS a on a.ID = b.AUTHOR_ID " +
                        "LEFT JOIN GENRES g on g.ID = b.GENRE_ID", new BookMapper());
    }

    @Override
    public Optional<Book> getBookById(Long id) {
        try {
            return Optional.ofNullable(
                    jdbc.queryForObject(
                            "SELECT b.ID, b.NAME, " +
                                    "a.ID as AUTHOR_ID, a.NAME as AUTHOR_NAME, " +
                                    "g.ID as GENRE_ID, g.NAME as GENRE_NAME " +
                                    "FROM BOOKS b " +
                                    "LEFT JOIN AUTHORS a on a.ID = b.AUTHOR_ID " +
                                    "LEFT JOIN GENRES g on g.ID = b.GENRE_ID " +
                                    "WHERE b.ID=:id",
                            Map.of("id", id),
                            new BookMapper()
                    )
            );
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public boolean deleteBookById(Long id) {
        int count = jdbc.update("DELETE FROM BOOKS WHERE ID= :id", Map.of("id", id));
        return count == 1;
    }

    private static class BookMapper implements RowMapper<Book> {

        @Override
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            var author = new Author(
                    rs.getLong("AUTHOR_ID"),
                    rs.getString("AUTHOR_NAME")
            );
            var genre = new Genre(
                    rs.getLong("GENRE_ID"),
                    rs.getString("GENRE_NAME")
            );

            return new Book(
                    rs.getLong("ID"),
                    rs.getString("NAME"),
                    author,
                    genre
            );
        }
    }
}
