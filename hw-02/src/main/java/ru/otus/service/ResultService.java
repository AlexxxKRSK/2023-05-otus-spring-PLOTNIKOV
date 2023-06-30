package ru.otus.service;

import ru.otus.domain.Question;
import ru.otus.domain.QuizResult;
import ru.otus.domain.User;

import java.util.Map;

public interface ResultService {
    QuizResult getResultByUserAnswers(User user, Map<Question, String> resultMap);

    void printResult(QuizResult result);
}
