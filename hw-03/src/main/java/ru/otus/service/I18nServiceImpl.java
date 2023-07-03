package ru.otus.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@RequiredArgsConstructor
@Service
public class I18nServiceImpl implements I18nService {

    private final Locale locale;

    private final MessageSource messageSource;

    @Override
    public String getMessageByCode(String msgCode) {
        return messageSource.getMessage(msgCode, null, locale);
    }
}
