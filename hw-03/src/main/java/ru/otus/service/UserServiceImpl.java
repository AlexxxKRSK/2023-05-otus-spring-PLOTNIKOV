package ru.otus.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.domain.User;
import ru.otus.utis.LocalizedMessageProvider;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final IOService ioService;

    private final LocalizedMessageProvider localizedMessageProvider;

    @Override
    public User getUser() {
        var getFirstNameMsg = localizedMessageProvider.getMessageByCode("user.first-name");
        var getLastNameMsg = localizedMessageProvider.getMessageByCode("user.last-name");
        var firstName = ioService.readStringWithPrompt(getFirstNameMsg);
        var lastName = ioService.readStringWithPrompt(getLastNameMsg);
        return new User(firstName, lastName);
    }
}
