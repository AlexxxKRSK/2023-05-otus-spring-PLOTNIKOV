package ru.otus.domain.mappers;

import org.mapstruct.Mapper;
import ru.otus.domain.Genre;
import ru.otus.dto.GenreDto;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GenreMapper {

    List<GenreDto> toDto(List<Genre> source);

}
