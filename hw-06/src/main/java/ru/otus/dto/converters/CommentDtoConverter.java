package ru.otus.dto.converters;

import org.springframework.core.convert.converter.Converter;
import ru.otus.dto.CommentDto;


public class CommentDtoConverter implements Converter<CommentDto, String> {

    @Override
    public String convert(CommentDto source) {
        return
                ("id: " +
                        source.getId() +
                        ", name: " +
                        source.getText() +
                        "\n")
                        .trim();
    }
}
