package ru.otus.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.dao.QuestionDao;
import ru.otus.domain.Question;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.lenient;
import static ru.otus.DomainObjectSupplier.getQuestion;

@ExtendWith(MockitoExtension.class)
class QuestionServiceImplTest {

    @Mock
    private QuestionDao questionDao;

    @InjectMocks
    private QuestionServiceImpl questionService;

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