package ru.otus.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.dao.GenreRepository;
import ru.otus.domain.Genre;
import ru.otus.service.GenreService;
import ru.otus.service.IOService;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final IOService ioService;

    private final GenreRepository genreRepository;

    @Override
    public Genre getGenre() {
        var genreName = ioService.readStringWithPrompt("Enter genre name");
        return genreRepository.getGenreByName(genreName).orElseGet(() -> createGenre(genreName));
    }

    private Genre createGenre(String name) {
        var genre = new Genre(name);
        return genreRepository.saveGenre(genre);
    }
}
