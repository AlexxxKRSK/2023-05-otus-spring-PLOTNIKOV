package ru.otus.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.dao.AuthorRepository;
import ru.otus.domain.Author;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final IOService ioService;

    private final AuthorRepository authorRepository;

    @Override
    public Author getAuthor() {
        var authorName = ioService.readStringWithPrompt("Enter author name");
        return authorRepository.getAuthorByName(authorName).orElse(createAuthor(authorName));
    }

    private Author createAuthor(String name) {
        var author = new Author(name);
        return authorRepository.saveAuthor(author);
    }
}
