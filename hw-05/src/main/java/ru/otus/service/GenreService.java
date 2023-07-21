package ru.otus.service;

import ru.otus.domain.Genre;

public interface GenreService {

    Genre getGenreByName(String genreName);
}
