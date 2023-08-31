package ru.otus.service;

import org.springframework.transaction.annotation.Transactional;
import ru.otus.domain.Genre;
import ru.otus.dto.GenreDto;

import java.util.List;

public interface GenreService {

    Genre getOrCreateGenreByName(String genreName);

    @Transactional(readOnly = true)
    List<GenreDto> getAllGenres();
}
