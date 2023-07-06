package ru.otus.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.provider.LocaleProvider;


@Service
@RequiredArgsConstructor
public class LocalizedMessageServiceImpl implements LocalizedMessageService {

    private final LocaleProvider localeProvider;

    private final MessageSource messageSource;

    @Override
    public String getMessageByCode(String msgCode) {
        var locale = localeProvider.getLocale();
        return messageSource.getMessage(msgCode, null, locale);
    }
}
