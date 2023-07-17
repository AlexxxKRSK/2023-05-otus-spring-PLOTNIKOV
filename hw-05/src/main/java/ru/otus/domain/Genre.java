package ru.otus.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Genre {

    private Long id;

    private String name;

    public Genre(String name) {
        this.name = name;
    }
}
