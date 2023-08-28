package ru.otus.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.otus.dto.GenreDto;
import ru.otus.service.GenreService;

@Controller
@RequiredArgsConstructor
public class GenreController {

    private final GenreService genreService;

    @GetMapping("/genre/create")
    public String createGenreView() {
        return "author/create";
    }

    @PostMapping("/genre/create")
    public String createGenre(GenreDto dto) {
        genreService.getOrCreateGenreByName(dto.getName());
        return "redirect:/create";
    }

}
