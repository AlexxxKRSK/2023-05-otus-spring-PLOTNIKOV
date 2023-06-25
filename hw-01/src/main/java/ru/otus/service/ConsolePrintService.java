package ru.otus.service;

import ru.otus.domain.Question;

import java.util.List;

public class ConsolePrintService implements PrintService {
    @Override
    public void printQuestions(List<Question> questionList) {
        for (var question : questionList) {
            System.out.println(question.getValue());
            for (var answer : question.getAnswers()) {
                System.out.println("\t* " + answer.getValue());
            }
            System.out.println();
        }
    }
}
