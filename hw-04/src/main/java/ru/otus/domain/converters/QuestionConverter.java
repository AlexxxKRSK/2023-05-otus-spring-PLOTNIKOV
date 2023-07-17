package ru.otus.domain.converters;

import org.springframework.core.convert.converter.Converter;
import ru.otus.domain.Answer;
import ru.otus.domain.Question;

import java.util.Comparator;

public class QuestionConverter implements Converter<Question, String> {

    @Override
    public String convert(Question source) {
        var builder = new StringBuilder();
        builder.append(source.getValue())
                .append("\n");
        source.getAnswers().stream()
                .sorted(Comparator.comparing(Answer::getOrdinal))
                .forEach(answer ->
                        builder.append(answer.getOrdinal())
                                .append(" ")
                                .append(answer.getValue())
                                .append("\n")
                );
        return builder.toString().trim();
    }
}
