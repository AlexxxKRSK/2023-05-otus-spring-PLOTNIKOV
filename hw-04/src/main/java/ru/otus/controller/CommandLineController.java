package ru.otus.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.service.QuizRunner;

@ShellComponent
@RequiredArgsConstructor
public class CommandLineController {

    private final QuizRunner quizRunner;

    @ShellMethod(value = "Start quiz", key = {"run", "r"})
    public void startQuiz() {
        quizRunner.runQuiz();
    }
}
