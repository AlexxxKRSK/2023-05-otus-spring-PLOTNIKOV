package ru.otus.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Answer {
    private Integer ordinal;

    private String value;

    private Boolean isCorrect;

    public Answer(String value, Boolean isCorrect) {
        this.value = value;
        this.isCorrect = isCorrect;
    }
}
