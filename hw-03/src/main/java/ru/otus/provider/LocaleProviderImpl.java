package ru.otus.provider;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class LocaleProviderImpl implements LocaleProvider {

    private final Locale locale;

    public LocaleProviderImpl(@Value("${application.locale}") Locale locale) {
        this.locale = locale;
    }

    @Override
    public Locale getLocale() {
        return locale;
    }
}
