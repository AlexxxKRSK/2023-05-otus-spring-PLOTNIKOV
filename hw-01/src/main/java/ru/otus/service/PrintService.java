package ru.otus.service;

import ru.otus.domain.Question;

import java.util.List;

public interface PrintService {

    void printQuestions(List<Question> questionList);
}
