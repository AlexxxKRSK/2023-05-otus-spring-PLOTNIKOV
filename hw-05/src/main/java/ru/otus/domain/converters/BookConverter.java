package ru.otus.domain.converters;

import org.springframework.core.convert.converter.Converter;
import ru.otus.domain.Book;


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
                        new GenreConverter().convert(source.getGenre());
    }
}
