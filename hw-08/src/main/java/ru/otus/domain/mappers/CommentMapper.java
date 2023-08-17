package ru.otus.domain.mappers;

import org.mapstruct.Mapper;
import ru.otus.domain.Comment;
import ru.otus.dto.CommentDto;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    CommentDto toDto(Comment source);

}
