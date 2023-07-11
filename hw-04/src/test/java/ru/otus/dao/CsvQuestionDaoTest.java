package ru.otus.dao;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.exception.FileNotPresentException;
import ru.otus.exception.ParsingException;
import ru.otus.provider.QuestionFileNameProvider;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
@RequiredArgsConstructor
public class CsvQuestionDaoTest {
    private static final int QUESTIONS_COUNT = 5;

    private static final String VALID_FILE = "/questions.csv";

    private static final String NON_EXISTS_FILE = "/no-such-file.csv";

    private static final String WRONG_FORMAT_FILE = "/wrong-format.csv";

    @MockBean
    private QuestionFileNameProvider questionFileNameProvider;

    private final CsvQuestionDao questionDao;

    @Test
    public void validFileTest() {
        when(questionFileNameProvider.getQuestionsFileName()).thenReturn(VALID_FILE);
        var loadedQuestions = questionDao.getQuestions();
        assertEquals(QUESTIONS_COUNT, loadedQuestions.size());
    }

    @Test
    public void emptyFileTest() {
        when(questionFileNameProvider.getQuestionsFileName()).thenReturn(NON_EXISTS_FILE);
        var ex = assertThrows(FileNotPresentException.class, questionDao::getQuestions);
        assertEquals(NON_EXISTS_FILE, ex.getMessage());
    }

    @Test
    public void wrongFormatFileTest() {
        when(questionFileNameProvider.getQuestionsFileName()).thenReturn(WRONG_FORMAT_FILE);
        assertThrows(ParsingException.class, questionDao::getQuestions);
    }
}
