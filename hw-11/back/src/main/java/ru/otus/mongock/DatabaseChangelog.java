package ru.otus.mongock;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.domain.Comment;
import ru.otus.domain.Genre;
import ru.otus.repository.AuthorRepository;
import ru.otus.repository.BookRepository;
import ru.otus.repository.CommentRepository;
import ru.otus.repository.GenreRepository;

import java.util.ArrayList;
import java.util.List;

@ChangeLog
public class DatabaseChangelog {

    private static final List<Genre> GENRES = List.of(
            new Genre("Роман"),
            new Genre("Фантастика")
    );

    private static final List<Author> AUTHORS = List.of(
            new Author("Михаил Булгаков"),
            new Author("Александр Пушкин"),
            new Author("Лев Толстой")
    );

    @ChangeSet(order = "001", id = "dropDb", author = "plotnikov", runAlways = true)
    public void dropDb(MongoDatabase db) {
        db.drop();
    }

    @ChangeSet(order = "002", id = "insert-authors", author = "plotnikov")
    public void insertAuthors(AuthorRepository authorRepository) {
        authorRepository.saveAll(AUTHORS).subscribe();
    }

    @ChangeSet(order = "003", id = "insert-genres", author = "plotnikov")
    public void insertGenres(GenreRepository genreRepository) {
        genreRepository.saveAll(GENRES).subscribe();
    }

    @ChangeSet(order = "004", id = "insert-books-and-comments", author = "plotnikov")
    public void insertAuthors(BookRepository bookRepository, CommentRepository commentRepository) {
        List<Book> books = List.of(
                new Book("Мастер и Маргарита", AUTHORS.get(0), GENRES.get(1), new ArrayList<>()),
                new Book("Евгений Онегин", AUTHORS.get(1), GENRES.get(0), new ArrayList<>()),
                new Book("Война и мир", AUTHORS.get(2), GENRES.get(0), new ArrayList<>())
        );
        bookRepository.saveAll(books).subscribe();
        var b0 = books.get(0);
        var c1 = new Comment("Воланд есть несомненный главный герой произведения, ведь его образ – своего " +
                "рода энергетический узел всей сложной композиционной структуры романа.", b0);
        var c2 = new Comment("Сатана действует в мире лишь постольку, поскольку ему дозволяется " +
                "то попущением Всевышнего.", b0);
        b0.getCommentList().addAll(List.of(c1, c2));
        var b1 = books.get(1);
        var c3 = new Comment("Иронический характер первых стихов и лирический вторых более или менее " +
                "понятен всякому читателю", b1);
        b1.getCommentList().add(c3);
        commentRepository.save(c1).subscribe();
//                .subscribe(r -> bookRepository.save(b0).subscribe());
//        commentRepository.save(c2)
//                .subscribe(r -> bookRepository.save(b1).subscribe());

    }
}
