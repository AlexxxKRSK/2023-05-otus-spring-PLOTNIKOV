package ru.otus.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.domain.Question;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static ru.otus.DomainObjectSupplier.getQuestion;

@ExtendWith(MockitoExtension.class)
class AnswerServiceImplTest {

    @Mock
    private IOService ioService;

    @InjectMocks
    private AnswerServiceImpl answerService;

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