package ru.otus.domain.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import ru.otus.domain.QuizResult;
import ru.otus.utis.LocalizedMessageProvider;

@RequiredArgsConstructor
public class ResultConverter implements Converter<QuizResult, String> {

    private final LocalizedMessageProvider localizedMessageProvider;

    @Override
    public String convert(QuizResult result) {
        var passedMsg = localizedMessageProvider.getMessageByCode("result.passed");
        var failedMsg = localizedMessageProvider.getMessageByCode("result.failed");
        var userScoreMsg = localizedMessageProvider.getMessageByCode("result.user-score");
        var minScoreMsg = localizedMessageProvider.getMessageByCode("result.min-score");
        var builder = new StringBuilder("\n");
        if (result.getAttemptScore() >= result.getPassScore()) {
            builder.append(passedMsg).append("\n");
        } else {
            builder.append(failedMsg);
        }
        builder.append(userScoreMsg)
                .append(result.getAttemptScore())
                .append("%. ")
                .append(minScoreMsg)
                .append(result.getPassScore())
                .append("%\n");
        return builder.toString();
    }
}
