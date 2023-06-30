package ru.otus;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import ru.otus.service.QuizRunner;

@ComponentScan
public class Main {
    public static void main(String[] args) {
        var ctx = new AnnotationConfigApplicationContext(Main.class);
        QuizRunner quizRunner = ctx.getBean(QuizRunner.class);
        quizRunner.runQuiz();
    }
}