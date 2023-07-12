package ru.otus.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.dao.QuestionDao;
import ru.otus.domain.Question;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.lenient;
import static ru.otus.DomainObjectSupplier.getQuestion;

@SpringBootTest
class QuestionServiceImplTest {

    @MockBean
    private QuestionDao questionDao;

    @Autowired
    private QuestionService questionService;

    @Test
    void getQuestionsTest() {
        Question question = getQuestion();
        List<Question> questionList = List.of(question);
        lenient().when(questionDao.getQuestions()).thenReturn(questionList);

        var serviceReturn = questionService.getQuestions();

        assertEquals(questionList.size(), serviceReturn.size());
        assertEquals(questionList.get(0), serviceReturn.get(0));
    }

}