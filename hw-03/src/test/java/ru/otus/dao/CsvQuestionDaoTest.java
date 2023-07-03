package ru.otus.dao;

import org.junit.jupiter.api.Test;
import ru.otus.exception.FileNotPresentException;
import ru.otus.exception.ParsingException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CsvQuestionDaoTest {
    private static final int QUESTIONS_COUNT = 5;

    private static final String VALID_FILE = "/questions.csv";

    private static final String NON_EXISTS_FILE = "/no-such-file.csv";

    private static final String WRONG_FORMAT_FILE = "/wrong-format.csv";

    @Test
    public void validFileTest() {
        var questionDao = new CsvQuestionDao(VALID_FILE);
        var loadedQuestions = questionDao.getQuestions();
        assertEquals(QUESTIONS_COUNT, loadedQuestions.size());
    }

    @Test
    public void emptyFileTest() {
        var questionDao = new CsvQuestionDao(NON_EXISTS_FILE);
        var ex = assertThrows(FileNotPresentException.class, questionDao::getQuestions);
        assertEquals(NON_EXISTS_FILE, ex.getMessage());
    }

    @Test
    public void wrongFormatFileTest() {
        var questionDao = new CsvQuestionDao(WRONG_FORMAT_FILE);
        assertThrows(ParsingException.class, questionDao::getQuestions);
    }
}
