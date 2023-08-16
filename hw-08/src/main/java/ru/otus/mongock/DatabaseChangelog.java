package ru.otus.mongock;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
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

    @ChangeSet(order = "001", id = "dropDb", author = "plotnikov", runAlways = true)
    public void dropDb(MongoDatabase db) {
        db.drop();
    }

    @ChangeSet(order = "002", id = "insert_authors", author = "plotnikov")
    public void insertAuthors(MongoDatabase db) {
        MongoCollection<Document> myCollection = db.getCollection("authors");
        List<Document> docs = List.of(
                new Document()
//                        .append("_id", 1)
                        .append("name", "Михаил Булгаков"),
                new Document()
//                        .append("_id", 2)
                        .append("name", "Александр Пушкин"),
                new Document()
//                        .append("_id", 3)
                        .append("name", "Лев Толстой")
        );
        myCollection.insertMany(docs);
    }

    @ChangeSet(order = "003", id = "insert_genres", author = "plotnikov")
    public void insertGenres(MongoDatabase db) {
        MongoCollection<Document> myCollection = db.getCollection("genres");
        List<Document> docs = List.of(
                new Document()
//                        .append("_id", 1)
                        .append("name", "Роман"),
                new Document()
//                        .append("_id", 2)
                        .append("name", "Фантастика")
        );
        myCollection.insertMany(docs);
    }

    @ChangeSet(order = "004", id = "insert_books", author = "plotnikov")
    public void insertBooks(BookRepository bookRepository,
                            AuthorRepository authorRepository,
                            GenreRepository genreRepository) {
        List<Author> authors = authorRepository.findAll();
        List<Genre> genres = genreRepository.findAll();
        bookRepository.saveAll(List.of(
                        new Book("Мастер и Маргарита", authors.get(0), genres.get(1), new ArrayList<>()),
                        new Book("Евгений Онегин", authors.get(1), genres.get(0), new ArrayList<>()),
                        new Book("Война и мир", authors.get(2), genres.get(0), new ArrayList<>())
                )
        );
    }

    @ChangeSet(order = "005", id = "insert_comments", author = "plotnikov")
    public void insertComments(BookRepository bookRepository,
                               CommentRepository commentRepository) {
        List<Book> books = bookRepository.findAll();

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

        commentRepository.saveAll(List.of(c1, c2, c3));
        bookRepository.saveAll(List.of(b0, b1));
    }
}
