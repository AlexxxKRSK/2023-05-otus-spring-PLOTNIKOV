package ru.otus.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.dao.QuestionDao;
import ru.otus.domain.Answer;
import ru.otus.domain.Question;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class QuestionServiceImplTest {

    @Mock
    QuestionDao questionDao;

    @Mock
    IOService ioService;

    @InjectMocks
    QuestionServiceImpl questionService;

    @Test
    void getQuestionsTest() {
        Question question = getQuestion();
        List<Question> questionList = List.of(question);
        lenient().when(questionDao.getQuestions()).thenReturn(questionList);

        var serviceReturn = questionService.getQuestions();

        assertEquals(questionList.size(), serviceReturn.size());
        assertEquals(questionList.get(0), serviceReturn.get(0));
    }

    @Test
    void getUserAnswerForQuestionTest() {
        Question question = getQuestion();
        when(ioService.readInt()).thenReturn(2);
        String userAnswer = questionService.getUserAnswerForQuestion(question);
        assertEquals(question.getAnswers().stream().toList().get(1).getValue(), userAnswer);
    }

    private static Question getQuestion() {
        Question question = new Question();
        question.setValue("Question!!");
        Answer answer1 = new Answer("Answer1", true);
        Answer answer2 = new Answer("Answer2", false);
        Answer answer3 = new Answer("Answer3", false);
        Answer answer4 = new Answer("Answer4", false);
        Answer answer5 = new Answer("Answer5", false);

        question.setAnswers(Set.of(answer1, answer2, answer3, answer4, answer5));
        return question;
    }

}