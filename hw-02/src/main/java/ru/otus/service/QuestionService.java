package ru.otus.service;

import ru.otus.domain.Question;

import java.util.List;

public interface QuestionService {

    void printQuestion(Question question);

    String getUserAnswerForQuestion(Question question);

    List<Question> getQuestions();
}