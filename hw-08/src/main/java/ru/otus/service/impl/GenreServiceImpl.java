package ru.otus.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.domain.Genre;
import ru.otus.repository.GenreRepository;
import ru.otus.service.GenreService;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    @Override
    @Transactional
    public Genre getOrCreateGenreByName(String genreName) {
        return genreRepository.getGenreByName(genreName).orElseGet(() -> createGenre(genreName));
    }

    private Genre createGenre(String name) {
        var genre = new Genre(name);
        return genreRepository.save(genre);
    }
}
