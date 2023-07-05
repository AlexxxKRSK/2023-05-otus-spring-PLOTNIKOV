package ru.otus.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.dao.QuestionDao;
import ru.otus.domain.Question;
import ru.otus.exception.FileNotPresentException;
import ru.otus.exception.ParsingException;
import ru.otus.utis.LocalizedMessageProvider;

import java.util.List;

@RequiredArgsConstructor
@Service
public class QuestionServiceImpl implements QuestionService {
    private final QuestionDao questionDao;

    private final LocalizedMessageProvider localizedMessageProvider;

    @Override
    public List<Question> getQuestions() {
        try {
            return questionDao.getQuestions();
        } catch (FileNotPresentException e) {
            var fileNotFoundMsg = localizedMessageProvider.getMessageByCode("file.not-found");
            throw new RuntimeException(String.format(fileNotFoundMsg, e.getMessage()));
        } catch (ParsingException e) {
            var parsingErrorMSg = localizedMessageProvider.getMessageByCode("file.parsing-error");
            throw new RuntimeException(parsingErrorMSg, e);
        }
    }

}
