package ru.otus.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.dao.QuestionDao;
import ru.otus.domain.Question;
import ru.otus.exception.FileNotPresentException;
import ru.otus.exception.ParsingException;

import java.util.List;

@RequiredArgsConstructor
@Service
public class QuestionServiceImpl implements QuestionService {
    private final QuestionDao questionDao;

    private final LocalizedMessageService localizedMessageService;

    @Override
    public List<Question> getQuestions() {
        try {
            return questionDao.getQuestions();
        } catch (FileNotPresentException e) {
            var fileNotFoundMsg = localizedMessageService.getMessageByCode("file.not-found");
            throw new RuntimeException(String.format(fileNotFoundMsg, e.getMessage()));
        } catch (ParsingException e) {
            var parsingErrorMSg = localizedMessageService.getMessageByCode("file.parsing-error");
            throw new RuntimeException(parsingErrorMSg, e);
        }
    }

}
