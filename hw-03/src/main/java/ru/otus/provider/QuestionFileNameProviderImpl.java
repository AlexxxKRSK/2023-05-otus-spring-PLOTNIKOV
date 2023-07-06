package ru.otus.provider;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.config.QuestionsFileConfig;

@RequiredArgsConstructor
@Component
public class QuestionFileNameProviderImpl implements QuestionFileNameProvider {

    private static final String DEFAULT_LANGUAGE = "en";

    private final LocaleProvider localeProvider;

    private final QuestionsFileConfig questionsFileConfig;

    @Override
    public String getQuestionsFileName() {
        var localeLanguage = localeProvider.getLocale().getLanguage();
        var languageToUse = questionsFileConfig.getLanguageToName().containsKey(localeLanguage)
                ? localeLanguage
                : DEFAULT_LANGUAGE;
        return questionsFileConfig.getLanguageToName().get(languageToUse);
    }
}
