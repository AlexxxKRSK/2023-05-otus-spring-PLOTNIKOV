package ru.otus;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.domain.Question;
import ru.otus.service.PrintService;
import ru.otus.service.QuestionLoaderService;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        var ctx = new ClassPathXmlApplicationContext("spring-context.xml");
        QuestionLoaderService loaderService = ctx.getBean(QuestionLoaderService.class);
        PrintService printService = ctx.getBean(PrintService.class);
        List<Question> questions = loaderService.loadQuestions();
        printService.printQuestions(questions);
    }
}