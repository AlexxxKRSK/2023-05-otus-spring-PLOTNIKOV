package ru.otus.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.otus.domain.Answer;
import ru.otus.domain.Question;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Arrays;
import java.util.Set;
import java.util.Objects;

@Data
@AllArgsConstructor
public class CsvQuestionLoaderService implements QuestionLoaderService {

    private final static String DELIMITER = ";";

    private final String questionsFilename;

    @Override
    public List<Question> loadQuestions() {
        var is = getClass().getResourceAsStream(questionsFilename);
        if (Objects.isNull(is)) {
            throw new RuntimeException(String.format("File %s not found in resources", questionsFilename));
        }
        List<String> csvLines = new BufferedReader(new InputStreamReader(is)).lines().toList();
        List<Question> questionList = new ArrayList<>();
        for (int i = 1; i < csvLines.size(); i++) {
            String[] parsedLine = csvLines.get(i).split(DELIMITER);
            Question question = new Question();
            question.setValue(parsedLine[0]);
            Set<Answer> answerSet = new HashSet<>();
            question.setAnswers(answerSet);
            Answer correctAnswer = new Answer(parsedLine[2], true);
            answerSet.add(correctAnswer);
            answerSet.addAll(Arrays.stream(parsedLine).skip(2).map(s -> new Answer(s, false)).toList());

            questionList.add(question);
        }

        return questionList;
    }

}
