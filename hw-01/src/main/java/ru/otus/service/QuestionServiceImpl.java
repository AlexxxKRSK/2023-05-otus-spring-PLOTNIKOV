package ru.otus.service;

import lombok.RequiredArgsConstructor;
import ru.otus.dao.QuestionDao;
import ru.otus.domain.Question;

import java.util.List;

@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {
    private final QuestionDao questionDao;

    @Override
    public void printQuestions() {
        List<Question> questionList = questionDao.getQuestions();
        for (var question : questionList) {
            System.out.println(question.getValue());
            for (var answer : question.getAnswers()) {
                System.out.println("\t* " + answer.getValue());
            }
            System.out.println();
        }
    }
}
