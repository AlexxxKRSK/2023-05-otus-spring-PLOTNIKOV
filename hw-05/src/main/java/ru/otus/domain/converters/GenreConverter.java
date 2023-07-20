package ru.otus.domain.converters;

import org.springframework.core.convert.converter.Converter;
import ru.otus.domain.Genre;


public class GenreConverter implements Converter<Genre, String> {

    @Override
    public String convert(Genre source) {
        return
                ("id: " +
                        source.getId() +
                        ", name: " +
                        source.getName() +
                        "\n")
                        .trim();
    }
}
