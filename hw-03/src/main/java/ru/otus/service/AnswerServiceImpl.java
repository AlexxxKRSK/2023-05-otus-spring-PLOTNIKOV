package ru.otus.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.domain.Question;
import ru.otus.utis.LocalizedMessageProvider;

import java.util.Objects;

@RequiredArgsConstructor
@Service
public class AnswerServiceImpl implements AnswerService {

    private final IOService ioService;

    private final LocalizedMessageProvider localizedMessageProvider;

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
                    var noSuchNumberMsg = localizedMessageProvider.getMessageByCode("input.no-such-number");
                    ioService.outputString(noSuchNumberMsg);
                }
            } catch (NumberFormatException e) {
                var notANumberMsg = localizedMessageProvider.getMessageByCode("input.not-a-number");
                ioService.outputString(notANumberMsg);
            }
        } while (Objects.equals(answer, null));
        return answer;
    }
}
