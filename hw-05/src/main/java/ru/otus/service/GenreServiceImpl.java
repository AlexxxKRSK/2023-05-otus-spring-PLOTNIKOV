package ru.otus.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.dao.GenreRepository;
import ru.otus.domain.Genre;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final IOService ioService;

    private final GenreRepository genreRepository;

    @Override
    public Genre getGenre() {
        var genreName = ioService.readStringWithPrompt("Enter genre name");
        return genreRepository.getGenreByName(genreName).orElse(createGenre(genreName));
    }

    private Genre createGenre(String name) {
        var genre = new Genre(name);
        return genreRepository.saveGenre(genre);
    }
}
