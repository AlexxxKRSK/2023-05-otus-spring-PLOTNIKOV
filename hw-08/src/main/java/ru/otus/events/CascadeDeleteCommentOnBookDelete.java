package ru.otus.events;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.AfterDeleteEvent;
import org.springframework.stereotype.Component;
import ru.otus.domain.Book;
import ru.otus.repository.CommentRepository;

@Component
@RequiredArgsConstructor
public class CascadeDeleteCommentOnBookDelete extends AbstractMongoEventListener<Book> {

private final CommentRepository commentRepository;

    @Override
    public void onAfterDelete(AfterDeleteEvent<Book> event) {
        var bookId = event.getSource().get("_id").toString();
        super.onAfterDelete(event);
        commentRepository.deleteCommentsByBookId(bookId);
    }
}
