package ru.otus.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import ru.otus.domain.mappers.GenreMapper;
import ru.otus.dto.GenreDto;
import ru.otus.repository.GenreRepository;

@RestController
@RequiredArgsConstructor
public class GenreController {

    private final GenreRepository genreRepository;

    private final GenreMapper genreMapper;

    @GetMapping("/genre")
    public Flux<GenreDto> getGenreList() {
        return genreRepository.findAll()
                .map(genreMapper::toDto);
    }

}
