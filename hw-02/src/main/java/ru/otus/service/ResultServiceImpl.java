package ru.otus.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.domain.Answer;
import ru.otus.domain.Question;
import ru.otus.domain.QuizResult;
import ru.otus.domain.User;

import java.time.LocalDateTime;
import java.util.Map;

@Service
public class ResultServiceImpl implements ResultService {

    private final IOService ioService;

    private final Double passScore;

    public ResultServiceImpl(IOService ioService, @Value("${app.config.quiz-pass-percentage}") Double passScore) {
        this.ioService = ioService;
        this.passScore = passScore;
    }

    @Override
    public QuizResult getResultByUserAnswers(User user, Map<Question, String> resultMap) {
        var result = new QuizResult();
        result.setUser(user);
        result.setQuestionAnswerMap(resultMap);
        result.setAttemptDateTime(LocalDateTime.now());
        double score = calculateScoreByAnswers(resultMap);
        result.setAttemptScore(score);
        return result;
    }

    @Override
    public void printResult(QuizResult result) {
        var builder = new StringBuilder("\n");
        if (result.getAttemptScore() >= passScore) {
            builder.append("Quiz passed!").append("\n");
        } else {
            builder.append("Sorry, you did`t pass a quiz.\n");
        }
        builder.append("Your score is ")
                .append(result.getAttemptScore())
                .append("%. ")
                .append("Minimum pass score is ")
                .append(passScore)
                .append("%\n");
        ioService.outputString(builder.toString());
    }

    private Double calculateScoreByAnswers(Map<Question, String> resultMap) {
        double correctAnswersCount = 0;
        for (var entry : resultMap.entrySet()) {
            var correctAnswer = entry.getKey().getAnswers().stream()
                    .filter(Answer::getIsCorrect).findFirst().orElseThrow();
            if (correctAnswer.getValue().equals(entry.getValue())) {
                correctAnswersCount++;
            }
        }
        return correctAnswersCount / resultMap.size() * 100;
    }
}
