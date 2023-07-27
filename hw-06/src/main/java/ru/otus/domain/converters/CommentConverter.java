package ru.otus.domain.converters;

import org.springframework.core.convert.converter.Converter;
import ru.otus.domain.Comment;


public class CommentConverter implements Converter<Comment, String> {

    @Override
    public String convert(Comment source) {
        return
                ("id: " +
                        source.getId() +
                        ", name: " +
                        source.getText() +
                        "\n")
                        .trim();
    }
}
