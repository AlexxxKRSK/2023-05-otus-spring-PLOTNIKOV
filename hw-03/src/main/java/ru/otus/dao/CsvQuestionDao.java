package ru.otus.dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import ru.otus.domain.Answer;
import ru.otus.domain.Question;
import ru.otus.exception.FileNotPresentException;
import ru.otus.exception.ParsingException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Arrays;
import java.util.Set;
import java.util.Objects;

@Repository
public class CsvQuestionDao implements QuestionDao {

    private final static String DELIMITER = ";";

    private final String questionsFilename;

    public CsvQuestionDao(
            @Value(
                    "#{locale.language.equals('en') " +
                            "? environment['app.config.questions-file-name'] " +
                            ": environment['app.config.questions-file-name-ru']}"
            ) String questionsFilename) {
        this.questionsFilename = questionsFilename;
    }

    @Override
    public List<Question> getQuestions() {
        var is = getClass().getResourceAsStream(questionsFilename);
        if (Objects.isNull(is)) {
            throw new FileNotPresentException(questionsFilename);
        }
        try (var reader = new BufferedReader(new InputStreamReader(is))) {
            List<String> csvLines = reader.lines().toList();
            List<Question> questionList = new ArrayList<>();
            for (int i = 1; i < csvLines.size(); i++) {
                Question question = getQuestionFromCsvLine(csvLines.get(i));
                questionList.add(question);
            }
            return questionList;
        } catch (IOException | IndexOutOfBoundsException e) {
            throw new ParsingException(e);
        }
    }

    private static Question getQuestionFromCsvLine(String csvLine) {
        String[] parsedLine = csvLine.split(DELIMITER);
        Question question = new Question();
        question.setValue(parsedLine[0]);
        Set<Answer> answerSet = new HashSet<>();
        question.setAnswers(answerSet);
        Answer correctAnswer = new Answer(parsedLine[1], true);
        answerSet.add(correctAnswer);
        answerSet.addAll(Arrays.stream(parsedLine).skip(2).map(s -> new Answer(s, false)).toList());
        setAnswersOrdinal(answerSet);
        return question;
    }

    private static void setAnswersOrdinal(Set<Answer> answerSet) {
        var ordinal = new int[]{1};
        answerSet.forEach(answer -> answer.setOrdinal(ordinal[0]++));
    }

}