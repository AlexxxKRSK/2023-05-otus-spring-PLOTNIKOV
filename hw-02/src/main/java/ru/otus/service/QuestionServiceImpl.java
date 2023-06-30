package ru.otus.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.dao.QuestionDao;
import ru.otus.domain.Answer;
import ru.otus.domain.Question;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class QuestionServiceImpl implements QuestionService {
    private final QuestionDao questionDao;

    private final IOService ioService;

    @Override
    public void printQuestion(Question question) {
        ioService.outputString(question.getValue());
    }

    @Override
    public String getUserAnswerForQuestion(Question question) {
        String answer = null;
        Map<Integer, String> ordinalAnswerMap = getOrdinalToQuestionMap(question);
        do {
            printQuestion(question);
            ordinalAnswerMap.forEach((k,v) -> ioService.outputString("\t" + k + " " + v));
            try {
                int answerOrdinal = ioService.readInt();
                if (ordinalAnswerMap.containsKey(answerOrdinal)) {
                    answer = ordinalAnswerMap.get(answerOrdinal);
                } else {
                    ioService.outputString("Wrong input. No answer with such number. Try again.");
                }
            } catch (NumberFormatException e) {
                ioService.outputString("Wrong input. Number expected. Try again.");
            }
        } while (Objects.equals(answer, null));
        return answer;
    }

    @Override
    public List<Question> getQuestions() {
        return questionDao.getQuestions();
    }

    private static Map<Integer, String> getOrdinalToQuestionMap(Question question) {
        var tempIndex = new int[]{1};
        return question.getAnswers().stream()
                .collect(
                        Collectors.toMap(
                                a -> tempIndex[0]++,
                                Answer::getValue)
                );
    }
}
