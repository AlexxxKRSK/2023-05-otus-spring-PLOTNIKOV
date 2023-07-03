package ru.otus.dao;

import org.junit.jupiter.api.Test;

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
        var ex = assertThrows(RuntimeException.class, questionDao::getQuestions);
        assertEquals(String.format("File %s not found in resources", NON_EXISTS_FILE), ex.getMessage());
    }

    @Test
    public void wrongFormatFileTest() {
        var questionDao = new CsvQuestionDao(WRONG_FORMAT_FILE);
        var ex = assertThrows(RuntimeException.class, questionDao::getQuestions);
        assertEquals("Error during parsing csv file!", ex.getMessage());
    }
}
