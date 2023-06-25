package ru.otus.domain;

import lombok.Data;

import java.util.Set;

@Data
public class Question {
    private String value;

    private Set<Answer> answers;
}
