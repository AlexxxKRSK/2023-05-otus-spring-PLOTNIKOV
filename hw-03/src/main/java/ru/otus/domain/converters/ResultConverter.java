package ru.otus.domain.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.otus.domain.QuizResult;
import ru.otus.service.I18nService;

@Component
@RequiredArgsConstructor
public class ResultConverter implements Converter<QuizResult, String> {

    private final I18nService i18nService;

    @Override
    public String convert(QuizResult result) {
        var passedMsg = i18nService.getMessageByCode("result.passed");
        var failedMsg = i18nService.getMessageByCode("result.failed");
        var userScoreMsg = i18nService.getMessageByCode("result.user-score");
        var minScoreMsg = i18nService.getMessageByCode("result.min-score");
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
