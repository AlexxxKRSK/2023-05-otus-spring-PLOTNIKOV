package ru.otus.domain.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import ru.otus.domain.QuizResult;
import ru.otus.service.LocalizedMessageService;

@RequiredArgsConstructor
public class ResultConverter implements Converter<QuizResult, String> {

    private final LocalizedMessageService localizedMessageService;

    @Override
    public String convert(QuizResult result) {
        var passedMsg = localizedMessageService.getMessageByCode("result.passed");
        var failedMsg = localizedMessageService.getMessageByCode("result.failed");
        var userScoreMsg = localizedMessageService.getMessageByCode("result.user-score");
        var minScoreMsg = localizedMessageService.getMessageByCode("result.min-score");
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
