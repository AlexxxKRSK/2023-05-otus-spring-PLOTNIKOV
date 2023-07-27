package ru.otus.domain.converters;

import org.springframework.core.convert.converter.Converter;
import ru.otus.domain.Book;

import java.util.stream.Collectors;


public class BookConverter implements Converter<Book, String> {

    @Override
    public String convert(Book source) {
        return
                "id: " +
                        source.getId() +
                        ",\nname: " +
                        source.getName() +
                        "\nAuthor: " +
                        new AuthorConverter().convert(source.getAuthor()) +
                        "\nGenre: " +
                        new GenreConverter().convert(source.getGenre()) +
                        "\nComments: " +
                        source.getCommentList().stream()
                                .map(c -> new CommentConverter().convert(c))
                                .collect(Collectors.joining("\n"))
                ;
    }
}
