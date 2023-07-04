package ru.otus.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class I18nServiceImpl implements I18nService {

    private final Locale locale;

    private final MessageSource messageSource;

    public I18nServiceImpl(@Value("${application.locale}") Locale locale, MessageSource messageSource) {
        this.locale = locale;
        this.messageSource = messageSource;
    }

    @Override
    public String getMessageByCode(String msgCode) {
        return messageSource.getMessage(msgCode, null, locale);
    }
}
