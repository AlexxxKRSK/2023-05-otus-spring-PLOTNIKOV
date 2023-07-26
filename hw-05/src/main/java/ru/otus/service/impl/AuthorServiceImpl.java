package ru.otus.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.dao.AuthorRepository;
import ru.otus.domain.Author;
import ru.otus.service.AuthorService;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Override
    @Transactional
    public Author getOrCreateAuthorByName(String authorName) {
        return authorRepository.getAuthorByName(authorName).orElseGet(() -> createAuthor(authorName));
    }

    private Author createAuthor(String name) {
        var author = new Author(name);
        return authorRepository.saveAuthor(author);
    }
}
