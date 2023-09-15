package ru.otus.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import ru.otus.domain.mappers.AuthorMapper;
import ru.otus.dto.AuthorDto;
import ru.otus.repository.AuthorRepository;


@RestController
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorRepository authorRepository;

    private final AuthorMapper authorMapper;

    @GetMapping("/author")
    public Flux<AuthorDto> getAuthorList() {
        return authorRepository.findAll()
                .map(authorMapper::toDto);
    }

}
