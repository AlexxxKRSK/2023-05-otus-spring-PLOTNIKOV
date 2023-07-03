package ru.otus;

import ru.otus.domain.Answer;
import ru.otus.domain.Question;

import java.util.Set;

public class DomainObjectSupplier {

    public static Question getQuestion() {
        Question question = new Question();
        question.setValue("Question!!");
        int ordinal = 1;
        Answer answer1 = new Answer(ordinal++, "Answer1", true);
        Answer answer2 = new Answer(ordinal++, "Answer2", false);
        Answer answer3 = new Answer(ordinal++, "Answer3", false);
        Answer answer4 = new Answer(ordinal++, "Answer4", false);
        Answer answer5 = new Answer(ordinal, "Answer5", false);

        question.setAnswers(Set.of(answer1, answer2, answer3, answer4, answer5));
        return question;
    }
}
