package ru.otus.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.domain.mappers.AuthorMapper;
import ru.otus.dto.AuthorDto;
import ru.otus.repository.AuthorRepository;
import ru.otus.domain.Author;
import ru.otus.service.AuthorService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    private final AuthorMapper authorMapper;

    @Override
    @Transactional
    public Author getOrCreateAuthorByName(String authorName) {
        return authorRepository.getAuthorByName(authorName).orElseGet(() -> createAuthor(authorName));
    }

    @Override
    @Transactional(readOnly = true)
    public List<AuthorDto> getAllAuthors() {
        var authors = authorRepository.findAll();
        return authorMapper.toDto(authors);
    }

    private Author createAuthor(String name) {
        var author = new Author(name);
        return authorRepository.save(author);
    }
}
