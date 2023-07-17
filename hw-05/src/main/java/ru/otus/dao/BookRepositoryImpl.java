package ru.otus.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.domain.Book;

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
        return null;
    }

    @Override
    public List<Book> getAllBooks() {
        return jdbc.getJdbcOperations().query(
                "SELECT * FROM BOOKS", new BookMapper());
    }

    @Override
    public Optional<Book> getBookById(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean deleteBookById(Long id) {
        int count = jdbc.update("DELETE FROM BOOKS WHERE ID= :id", Map.of("id", id));
        return count == 1;
    }

    private static class BookMapper implements RowMapper<Book> {

        @Override
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Book(
                    rs.getLong("ID"),
                    rs.getString("NAME")
            );
        }
    }
}
