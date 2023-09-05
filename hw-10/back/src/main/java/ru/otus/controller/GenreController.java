package ru.otus.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.domain.mappers.GenreMapper;
import ru.otus.dto.GenreDto;
import ru.otus.service.GenreService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class GenreController {

    private final GenreService genreService;

    private final GenreMapper genreMapper;

    @GetMapping("/genre")
    public ResponseEntity<List<GenreDto>> getGenreList() {
        return ResponseEntity.ok()
                .body(genreService.getAllGenres());
    }

    @PostMapping("/genre")
    public ResponseEntity<GenreDto> createGenre(@RequestBody GenreDto dto) {
        var genre = genreService.getOrCreateGenreByName(dto.getName());
        return ResponseEntity.ok()
                .body(genreMapper.toDto(genre));
    }

}
