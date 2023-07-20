package ru.otus.domain.converters;

import org.springframework.core.convert.converter.Converter;
import ru.otus.domain.Author;

public class AuthorConverter implements Converter<Author, String> {

    @Override
    public String convert(Author source) {
        return
                ("id: " +
                        source.getId() +
                        ", name: " +
                        source.getName() +
                        "\n")
                        .trim();
    }
}
