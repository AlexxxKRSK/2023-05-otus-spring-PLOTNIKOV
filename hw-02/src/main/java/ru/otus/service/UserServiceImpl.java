package ru.otus.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.domain.User;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final IOService ioService;
    @Override
    public User getUser() {
        var firstName = ioService.readStringWithPrompt("Enter user`s first name");
        var lastName = ioService.readStringWithPrompt("Enter user`s last name");
        return new User(firstName, lastName);
    }
}
