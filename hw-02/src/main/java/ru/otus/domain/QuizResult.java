package ru.otus.domain;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

@Data
public class QuizResult {
    private User user;

    private Map<Question, String> questionAnswerMap;

    private Double attemptScore;

    private LocalDateTime attemptDateTime;
}
