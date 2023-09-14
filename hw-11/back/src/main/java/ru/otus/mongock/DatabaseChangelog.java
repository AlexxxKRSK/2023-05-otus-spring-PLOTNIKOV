package ru.otus.mongock;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.DBRef;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.List;

@ChangeLog
public class DatabaseChangelog {

    private List<Document> authors;

    private List<Document> genres;

    private List<Document> books;

    @ChangeSet(order = "001", id = "dropDb", author = "plotnikov", runAlways = true)
    public void dropDb(MongoDatabase db) {
        db.drop();
    }

    @ChangeSet(order = "002", id = "insert_authors", author = "plotnikov")
    public void insertAuthors(MongoDatabase db) {
        MongoCollection<Document> myCollection = db.getCollection("authors");
        authors = List.of(
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
        myCollection.insertMany(authors);
    }

    @ChangeSet(order = "003", id = "insert_genres", author = "plotnikov")
    public void insertGenres(MongoDatabase db) {
        MongoCollection<Document> myCollection = db.getCollection("genres");
        genres = List.of(
                new Document()
//                        .append("_id", 1)
                        .append("name", "Роман"),
                new Document()
//                        .append("_id", 2)
                        .append("name", "Фантастика")
        );
        myCollection.insertMany(genres);
    }

    @ChangeSet(order = "004", id = "insert_books", author = "plotnikov")
    public void insertBooks(MongoDatabase db) {
        MongoCollection<Document> myCollection = db.getCollection("books");
        books = List.of(
                new Document()
                        .append("name", "Мастер и Маргарита")
                        .append("author", authors.get(0).get("_id"))
                        .append("genre", genres.get(1).get("_id"))
                        .append("commentList", "[]")
                ,
                new Document()
                        .append("name", "Евгений Онегин")
                        .append("author", authors.get(1).get("_id"))
                        .append("genre", genres.get(0).get("_id"))
                        .append("commentList", "[]"),
                new Document()
                        .append("name", "Война и мир")
                        .append("author", authors.get(2).get("_id"))
                        .append("genre", genres.get(0).get("_id"))
                        .append("commentList", "[]")
        );
        myCollection.insertMany(books);
    }

    @ChangeSet(order = "005", id = "insert_comments", author = "plotnikov")
    public void insertComments(MongoDatabase db) {
        MongoCollection<Document> myCollection = db.getCollection("comments");
        var b0 = books.get(0);
        var b1 = books.get(1);
        var c1 = new Document()
                .append("text", "Воланд есть несомненный главный герой произведения, ведь его образ – своего " +
                        "рода энергетический узел всей сложной композиционной структуры романа.")
                .append("book", b0.get("_id"));
        var c2 =
                new Document()
                        .append("text", "Сатана действует в мире лишь постольку, поскольку ему дозволяется " +
                                "то попущением Всевышнего.")
                        .append("book", b0.get("_id"));
        var c3 = new Document()
                .append("text", "Иронический характер первых стихов и лирический вторых более или менее " +
                        "понятен всякому читателю")
                .append("book", b1.get("_id"));
        var comments = List.of(c1, c2, c3);

        myCollection.insertMany(comments);

        MongoCollection<Document> booksCollection = db.getCollection("books");
        b0.append("commentList",
                List.of(
                        new DBRef("comments", new ObjectId(c1.get("_id").toString())),
                        new DBRef("comments", new ObjectId(c2.get("_id").toString()))
                )
        );
        booksCollection.replaceOne(new Document().append("_id", b0.get("_id")), b0);
        b1.append("commentList",
                List.of(
                        new DBRef("comments", new ObjectId(c3.get("_id").toString()))
                )
        );
        booksCollection.replaceOne(new Document().append("_id", b1.get("_id")), b1);

    }
}
