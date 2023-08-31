package ru.otus.domain.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.otus.domain.Book;
import ru.otus.dto.BookDto;
import ru.otus.dto.BookWithCommentDto;

@Mapper(componentModel = "spring")
public interface BookMapper {

    @Mappings({
            @Mapping(target = "author", source = "author.name"),
            @Mapping(target = "genre", source = "genre.name")
    })
    BookDto toDto(Book source);

    @Mappings({
            @Mapping(target = "author", source = "source.author.name"),
            @Mapping(target = "genre", source = "source.genre.name")
    })
    BookWithCommentDto toDtoWithComments(Book source);

}
