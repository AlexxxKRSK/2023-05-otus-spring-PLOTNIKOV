package ru.otus.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.domain.User;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final IOService ioService;

    private final LocalizedMessageService localizedMessageService;

    @Override
    public User getUser() {
        var getFirstNameMsg = localizedMessageService.getMessageByCode("user.first-name");
        var getLastNameMsg = localizedMessageService.getMessageByCode("user.last-name");
        var firstName = ioService.readStringWithPrompt(getFirstNameMsg);
        var lastName = ioService.readStringWithPrompt(getLastNameMsg);
        return new User(firstName, lastName);
    }
}
