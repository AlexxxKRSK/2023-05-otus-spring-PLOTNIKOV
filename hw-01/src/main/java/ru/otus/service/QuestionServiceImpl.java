package ru.otus.service;

import lombok.RequiredArgsConstructor;
import ru.otus.dao.QuestionDao;
import ru.otus.domain.Question;

import java.util.List;

@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {
    private final QuestionDao questionDao;

    private final OutputService outputService;

    @Override
    public void printQuestions() {
        List<Question> questionList = questionDao.getQuestions();
        for (var question : questionList) {
            outputService.outputString(question.getValue());
            for (var answer : question.getAnswers()) {
                outputService.outputString("\t* " + answer.getValue());
            }
            outputService.outputString("");
        }
    }
}
