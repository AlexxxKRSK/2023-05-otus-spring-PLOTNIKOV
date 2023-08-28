package ru.otus;

import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.domain.Comment;
import ru.otus.domain.Genre;
import ru.otus.dto.*;

import java.util.ArrayList;
import java.util.List;

public class TestDataProvider {

    private static final String BOOK_NAME = "TEST BOOK";

    private static final String AUTHOR_NAME = "TEST AUTHOR";

    private static final String GENRE_NAME = "TEST GENRE";

    private static final String COMMENT_TEXT = "TEST COMMENT";

    private static final String NEW_BOOK_NAME = "NEW TEST BOOK";

    private static final String NEW_AUTHOR_NAME = "NEW TEST AUTHOR";

    private static final String NEW_GENRE_NAME = "NEW TEST GENRE";

    public static Book getExistingBook() {
        var author = new Author(1L, AUTHOR_NAME);
        var genre = new Genre(1L, GENRE_NAME);
        return new Book(1L, "TEST BOOK", author, genre, new ArrayList<>());
    }

    public static Book getExistingBookWithComment() {
        var author = new Author(1L, AUTHOR_NAME);
        var genre = new Genre(1L, GENRE_NAME);
        var comment = new Comment("TEST COMMENT");
        var book = new Book(1L, BOOK_NAME, author, genre, List.of(comment));
        comment.setBook(book);
        return book;
    }

    public static BookDto getExistingBookDto() {
        return new BookDto(1L, BOOK_NAME, AUTHOR_NAME, GENRE_NAME);
    }

    public static BookWithCommentDto getExistingBookWithCommentDto() {
        var comment = new CommentDto(null, COMMENT_TEXT);
        return new BookWithCommentDto(1L, BOOK_NAME, AUTHOR_NAME, GENRE_NAME, List.of(comment));
    }

    public static AuthorDto getExistingAuthorDto() {
        return new AuthorDto(1L, AUTHOR_NAME);
    }

    public static GenreDto getExistingGenrerDto() {
        return new GenreDto(1L, GENRE_NAME);
    }

    public static BookDto getNewBookDto() {
        return new BookDto(null, NEW_BOOK_NAME, NEW_AUTHOR_NAME, NEW_GENRE_NAME);
    }

}
