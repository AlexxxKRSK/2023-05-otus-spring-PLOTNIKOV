package ru.otus.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.domain.mappers.AuthorMapper;
import ru.otus.dto.AuthorDto;
import ru.otus.service.AuthorService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    private final AuthorMapper authorMapper;

    @GetMapping("/author")
    public ResponseEntity<List<AuthorDto>> getAuthorList() {
        return ResponseEntity.ok()
                .body(authorService.getAllAuthors());
    }

    @PostMapping("/author")
    public ResponseEntity<AuthorDto> createAuthor(@RequestBody AuthorDto dto) {
        var author = authorService.getOrCreateAuthorByName(dto.getName());
        return ResponseEntity.ok()
                .body(authorMapper.toDto(author));
    }

}
