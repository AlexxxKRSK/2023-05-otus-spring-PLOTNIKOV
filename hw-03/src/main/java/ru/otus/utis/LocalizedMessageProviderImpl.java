package ru.otus.utis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class LocalizedMessageProviderImpl implements LocalizedMessageProvider {

    private final Locale locale;

    private final MessageSource messageSource;

    public LocalizedMessageProviderImpl(@Value("${application.locale}") Locale locale, MessageSource messageSource) {
        this.locale = locale;
        this.messageSource = messageSource;
    }

    @Override
    public String getMessageByCode(String msgCode) {
        return messageSource.getMessage(msgCode, null, locale);
    }
}
