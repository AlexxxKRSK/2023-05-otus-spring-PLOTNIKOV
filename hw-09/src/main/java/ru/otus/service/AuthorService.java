package ru.otus.service;

import ru.otus.domain.Author;
import ru.otus.dto.AuthorDto;

import java.util.List;

public interface AuthorService {

    Author getOrCreateAuthorByName(String authorName);

    List<AuthorDto> getAllAuthors();
}
