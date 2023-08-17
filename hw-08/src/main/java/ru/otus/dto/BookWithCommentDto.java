package ru.otus.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class BookWithCommentDto extends BookDto {

    private List<CommentDto> commentList;

    public BookWithCommentDto(String id, String name, String author, String genre, List<CommentDto> commentList) {
        super(id, name, author, genre);
        this.commentList = commentList;
    }
}