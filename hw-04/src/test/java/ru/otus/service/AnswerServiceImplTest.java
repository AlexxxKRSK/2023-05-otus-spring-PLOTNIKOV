package ru.otus.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.domain.Question;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static ru.otus.DomainObjectSupplier.getQuestion;

@SpringBootTest
class AnswerServiceImplTest {

    @MockBean
    private IOService ioService;

    @Autowired
    private AnswerService answerService;

    @Test
    void getUserAnswerForQuestionTest() {
        Question question = getQuestion();
        int userAnswerOrdinal = 2;
        var answerByOrdinal = question.getAnswers()
                        .stream().filter(answer -> answer.getOrdinal().equals(userAnswerOrdinal))
                .findFirst().orElseThrow();
        when(ioService.readInt()).thenReturn(userAnswerOrdinal);
        String userAnswer = answerService.getUserAnswerForQuestion(question);
        assertEquals(answerByOrdinal.getValue(), userAnswer);
    }

}