package ru.otus.provider;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class QuestionFileNameProviderImpl implements QuestionFileNameProvider {

    private static final String LANGUAGE_RU = "ru";

    private final LocaleProvider localeProvider;

    private final String fileNameRu;

    private final String fileNameEn;

    public QuestionFileNameProviderImpl(
            LocaleProvider localeProvider,
            @Value("${file.name-ru}") String fileNameRu,
            @Value("${file.name}") String fileNameEn) {
        this.localeProvider = localeProvider;
        this.fileNameRu = fileNameRu;
        this.fileNameEn = fileNameEn;
    }

    @Override
    public String getQuestionsFileName() {
        var locale = localeProvider.getLocale();
        return Objects.equals(locale.getLanguage(), LANGUAGE_RU) ? fileNameRu : fileNameEn;
    }
}
