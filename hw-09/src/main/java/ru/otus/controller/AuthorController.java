package ru.otus.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.otus.dto.AuthorDto;
import ru.otus.service.AuthorService;

@Controller
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping("/author/create")
    public String createAuthorView() {
        return "author/create";
    }

    @PostMapping("/author/create")
    public String createAuthor(AuthorDto dto) {
        authorService.getOrCreateAuthorByName(dto.getName());
        return "redirect:/create";
    }

}
