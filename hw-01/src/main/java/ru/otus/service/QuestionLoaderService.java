package ru.otus.service;

import ru.otus.domain.Question;

import java.util.List;

public interface QuestionLoaderService {

    List<Question> loadQuestions();
}
