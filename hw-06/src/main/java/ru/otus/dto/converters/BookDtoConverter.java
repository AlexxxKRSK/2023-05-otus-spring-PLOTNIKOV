package ru.otus.dto.converters;

import org.springframework.core.convert.converter.Converter;
import ru.otus.dto.BookDto;
import ru.otus.dto.BookWithCommentDto;

import java.util.stream.Collectors;


public class BookDtoConverter implements Converter<BookDto, String> {

    @Override
    public String convert(BookDto source) {
        var result =
                "id: " +
                        source.getId() +
                        ",\nname: " +
                        source.getName() +
                        "\nAuthor: " +
                        source.getAuthor() +
                        "\nGenre: " +
                        source.getGenre();
        if (source instanceof BookWithCommentDto) {
            result += "\nComments:\n" +
                    ((BookWithCommentDto) source).getCommentList()
                            .stream()
                            .map(c -> new CommentDtoConverter().convert(c))
                            .collect(Collectors.joining("\n"));
        }

        return result;
    }
}
