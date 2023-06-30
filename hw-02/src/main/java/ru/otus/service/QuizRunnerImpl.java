package ru.otus.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.domain.Question;

import java.util.HashMap;

@RequiredArgsConstructor
@Component
public class QuizRunnerImpl implements QuizRunner {

    private final QuestionService questionService;

    private final UserService userService;

    private final ResultService resultService;

    @Override
    public void runQuiz() {
        var user = userService.getUser();
        var questions = questionService.getQuestions();
        var questionAnswerMap = new HashMap<Question, String>();
        for (var question : questions) {
            var userAnswer = questionService.getUserAnswerForQuestion(question);
            questionAnswerMap.put(question, userAnswer);
        }
        var result = resultService.getResultByUserAnswers(user, questionAnswerMap);
        resultService.printResult(result);
    }
}
