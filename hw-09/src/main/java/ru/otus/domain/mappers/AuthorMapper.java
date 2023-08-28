package ru.otus.domain.mappers;

import org.mapstruct.Mapper;
import ru.otus.domain.Author;
import ru.otus.dto.AuthorDto;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

    List<AuthorDto> toDto(List<Author> source);

}
