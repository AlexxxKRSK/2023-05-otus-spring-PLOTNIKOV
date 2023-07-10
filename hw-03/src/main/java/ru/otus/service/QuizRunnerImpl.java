package ru.otus.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.support.ConfigurableConversionService;
import org.springframework.stereotype.Component;
import ru.otus.domain.Question;

import java.util.HashMap;

@RequiredArgsConstructor
@Component
public class QuizRunnerImpl implements QuizRunner {

    private final QuestionService questionService;

    private final UserService userService;

    private final ResultService resultService;

    private final AnswerService answerService;

    private final IOService ioService;

    private final ConfigurableConversionService conversionService;

    @PostConstruct
    @Override
    public void runQuiz() {
        var user = userService.getUser();
        var questions = questionService.getQuestions();
        var questionAnswerMap = new HashMap<Question, String>();
        for (var question : questions) {
            var stringQuestion = conversionService.convert(question, String.class);
            ioService.outputString(stringQuestion);
            var userAnswer = answerService.getUserAnswerForQuestion(question);
            questionAnswerMap.put(question, userAnswer);
        }
        var result = resultService.getResultByUserAnswers(user, questionAnswerMap);
        var stringResult = conversionService.convert(result, String.class);
        ioService.outputString(stringResult);
    }
}
