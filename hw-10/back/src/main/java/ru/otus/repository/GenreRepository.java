package ru.otus.repository;


import org.springframework.data.repository.CrudRepository;
import ru.otus.domain.Genre;

import java.util.Optional;
import java.util.List;

public interface GenreRepository extends CrudRepository<Genre, Long> {

    List<Genre> findAll();

    Optional<Genre> getGenreByName(String name);

}