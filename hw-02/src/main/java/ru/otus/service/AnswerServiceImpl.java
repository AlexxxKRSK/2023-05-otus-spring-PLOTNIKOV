package ru.otus.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.domain.Question;

import java.util.Objects;

@RequiredArgsConstructor
@Service
public class AnswerServiceImpl implements AnswerService {

    private final IOService ioService;

    @Override
    public String getUserAnswerForQuestion(Question question) {
        String answer = null;
        do {
            try {
                int answerOrdinal = ioService.readInt();
                var answerOpt = question.getAnswers().stream().filter(a -> a.getOrdinal() == answerOrdinal).findFirst();
                if (answerOpt.isPresent()) {
                    answer = answerOpt.get().getValue();
                } else {
                    ioService.outputString("Wrong input. No answer with such number. Try again.");
                }
            } catch (NumberFormatException e) {
                ioService.outputString("Wrong input. Number expected. Try again.");
            }
        } while (Objects.equals(answer, null));
        return answer;
    }
}
