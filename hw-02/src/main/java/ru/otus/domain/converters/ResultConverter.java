package ru.otus.domain.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import ru.otus.domain.QuizResult;

@RequiredArgsConstructor
public class ResultConverter implements Converter<QuizResult, String> {

    @Override
    public String convert(QuizResult result) {
        var builder = new StringBuilder("\n");
        if (result.getAttemptScore() >= result.getPassScore()) {
            builder.append("Quiz passed!").append("\n");
        } else {
            builder.append("Sorry, you did`t pass a quiz.\n");
        }
        builder.append("Your score is ")
                .append(result.getAttemptScore())
                .append("%. ")
                .append("Minimum pass score is ")
                .append(result.getPassScore())
                .append("%\n");
        return builder.toString();
    }
}
