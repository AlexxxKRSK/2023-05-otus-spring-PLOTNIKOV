package ru.otus.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.domain.mappers.GenreMapper;
import ru.otus.dto.GenreDto;
import ru.otus.repository.GenreRepository;
import ru.otus.domain.Genre;
import ru.otus.service.GenreService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    private final GenreMapper genreMapper;

    @Override
    @Transactional
    public Genre getOrCreateGenreByName(String genreName) {
        return genreRepository.getGenreByName(genreName).orElseGet(() -> createGenre(genreName));
    }

    @Override
    @Transactional(readOnly = true)
    public List<GenreDto> getAllGenres() {
        var genres = genreRepository.findAll();
        return genreMapper.toDto(genres);
    }

    private Genre createGenre(String name) {
        var genre = new Genre(name);
        return genreRepository.save(genre);
    }
}
