package ru.otus.service;

import ru.otus.domain.Genre;

public interface GenreService {

    Genre getOrCreateGenreByName(String genreName);
}
