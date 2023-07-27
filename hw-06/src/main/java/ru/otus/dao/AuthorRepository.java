package ru.otus.dao;


import ru.otus.domain.Author;

import java.util.Optional;

public interface AuthorRepository {

    Author saveAuthor(Author author);

    Optional<Author> getAuthorByName(String name);

}